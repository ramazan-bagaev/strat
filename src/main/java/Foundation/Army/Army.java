package Foundation.Army;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Runnable.RunEntity;
import Utils.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Army implements RunEntity, Broadcaster, Combatant {

    public enum State{
        Attack, Defend, Moving, Regrouping, None
    }

    private State state;

    private Content movingProgressContent;
    private Content posContent;
    private Content pathContent;
    private Content battleContent;


    //private ArrayList<ArmySquad> armySquads;

    private static final Index ARMY_FORMATION_SIZE = new Index(10, 10);

    private ArmyFormation armyFormation;

    private Index fieldPos;

    private FieldMap fieldMap;

    private Time time;
    private PathFinder pathFinder;
    private Index destination;
    private LinkedList<Index> path;
    private Army target;

    private int movingProgress;

    private Random random = new Random();

    private ArrayList<Battle> battles;

    private Index.Direction nextDirection;

    private Army(Index fieldPos, FieldMap fieldMap, Time time){
        this.time = time;
        this.fieldPos = new Index(fieldPos);
        this.fieldMap = fieldMap;
        this.battles = new ArrayList<>();
        //this.armySquads = new ArrayList<>();
        //this.armyFormation = new ArmyFormation();
        movingProgressContent = new Content();
        posContent = new Content();
        pathContent = new Content();
        battleContent = new Content();
        state = State.None;
        pathFinder = new PathFinder(fieldMap);

    }

    public static Army ArmyWithPeople(People people, Index fieldPos, FieldMap fieldMap, Time time){
        People people1 = new People();
        People people2 = new People();
        Random random = new Random();
        for(Person person: people.getPersonArray()){
            if (random.nextBoolean()) {
                people1.addPerson(person);
                //people.removePerson(person);
            }
            else{
                people2.addPerson(person);
                //people.removePerson(person);
            }
        }
        ArmySquad armySquad = ArmySquad.RandomArmySquad(people1);
        Army result = new Army(fieldPos, fieldMap, time);
        int size = ARMY_FORMATION_SIZE.x;
        int size2 = ARMY_FORMATION_SIZE.x/2;
        result.armyFormation = new ArmyFormation(armySquad, new Index(random.nextInt(size), random.nextInt(size)),
                new Index(random.nextInt(size2) + 1, random.nextInt(size2) + 1), ARMY_FORMATION_SIZE);
        result.armyFormation.addUnit(ArmySquad.RandomArmySquad(people2), new Index(random.nextInt(size), random.nextInt(size)),
                new Index(random.nextInt(size2)+1, random.nextInt(size2) + 1));
        result.nextDirection = result.armyFormation.getGeneralDirection();
        Field field = fieldMap.getFieldByIndex(fieldPos);
        field.createAndAddArmy(result);
        return result;
    }

    public static Army ArmyWithSquad(ArmySquad armySquad, Index fieldPos, FieldMap fieldMap, Time time){
        Army result = new Army(fieldPos, fieldMap, time);
        result.armyFormation = new ArmyFormation(armySquad, ARMY_FORMATION_SIZE.multiply(0.5), new Index(2, 1), ARMY_FORMATION_SIZE);
        result.nextDirection = result.armyFormation.getGeneralDirection();
        Field field = fieldMap.getFieldByIndex(fieldPos);
        field.createAndAddArmy(result);
        return result;
    }

    public void action(Index pos){
        Field field = fieldMap.getFieldByIndex(pos);
        if (field.getCity() != null){
            return;
        }
        if (field.getArmyElement() != null){
            target = field.getArmyElement().getArmy();
            moveNear(pos);
            return;
        }
        move(pos);
    }

    public void move(Index destination){
        path = null;
        pathContent.changed();
        Field field = fieldMap.getFieldByIndex(destination);
        if (field == null) return;
        if (field.getGroundType() == Ground.GroundType.Water || field.getGroundType() == Ground.GroundType.Rock) return;
        this.destination = destination;
        setPath();
    }

    public void moveNear(Index destination){
        setNearPath(destination);
        pathContent.changed();
    }

    public void moveToNeighbor(Index localDestination){
        Field field = fieldMap.getFieldByIndex(localDestination);
        if (field.getArmyElement() != null){
            move(destination);
            return;
        }
        Field oldField = fieldMap.getFieldByIndex(fieldPos);
        oldField.removerArmy();
        fieldPos = new Index(localDestination);
        posContent.changed();
        field.createAndAddArmy(this);
        movingProgress = 0;
        if (destination.equals(fieldPos)){
            path = null;
            state = State.None;
            pathContent.changed();
        }
        else {

        }
        movingProgressContent.changed();
    }


    public FieldMap getFieldMap() {
        return fieldMap;
    }

    public Index getFieldPos() {
        return fieldPos;
    }

    public void setNextDirection(){
        if (path == null) return;
        nextDirection = fieldPos.whatDirection(path.getFirst());
    }

    public void setPath(){
        path = pathFinder.getPath(fieldPos, destination);
        setNextDirection();
        movingProgress = 0;
        state = State.Moving;
        movingProgressContent.changed();
        pathContent.changed();
    }

    public void setNearPath(Index destination){
        path = pathFinder.getNearPath(fieldPos, destination);
        setNextDirection();
        if (path == null){
            target = null;
            return;
        }
        if (path.size() == 0){
            path = null;
            state = State.None;
            return;
        }
        this.destination = path.getLast();
        movingProgress = 0;
        state = State.Moving;
        movingProgressContent.changed();
        pathContent.changed();
    }

    public int getMovingProgress() {
        return movingProgress;
    }

    public LinkedList<Index> getPath() {
        return path;
    }

    public State getState() {
        return state;
    }


    @Override
    public void run() {
        if (target != null){
            if (target.getFieldPos().isNeighbor(fieldPos)){
                state = State.Attack;
                Battle battle = new Battle(this, target);
                addBattle(battle);
                target.addBattle(battle);
                getFieldMap().getGameEngine().addRunEntity(battle);
                target = null;
                return;
            }
        }
        if (nextDirection != armyFormation.getGeneralDirection()){
            state = State.Regrouping;
            movingProgress+=1;
            movingProgressContent.changed();
            if (movingProgress < 100) return;
            movingProgress = 0;
            movingProgressContent.changed();
            armyFormation.turnTo(nextDirection);
        }
        if (path != null){
            movingProgress+=1;
            state = State.Moving;
            movingProgressContent.changed();
            if (movingProgress < 100) return;
            if (path.size() == 0) return;
            Index destination = path.getFirst();
            path.removeFirst();
            pathContent.changed();
            moveToNeighbor(destination);
            if (path == null) return;
            setNextDirection();
        }
    }

    @Override
    public String getValue(String key) {
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "movingProgress":
                movingProgressContent.subscribe(subscription);
                break;
            case "fieldPos":
                posContent.subscribe(subscription);
                break;
            case "path":
                pathContent.subscribe(subscription);
            case "battle":
                battleContent.subscribe(subscription);
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "movingProgress":
                movingProgressContent.unsubscribe(subscription);
                break;
            case "fieldPos":
                posContent.unsubscribe(subscription);
                break;
            case "path":
                pathContent.unsubscribe(subscription);
            case "battle":
                battleContent.unsubscribe(subscription);

        }
    }


    @Override
    public void addBattle(Battle battle){
        battles.add(battle);
        battleContent.changed();
    }

    @Override
    public void removeBattle(Battle battle){
        battles.remove(battle);
        battleContent.changed();
    }

    @Override
    public Field getCombatantField(){
        return fieldMap.getFieldByIndex(getFieldPos());
    }

    @Override
    public FrontLine getFrontLine(Index.Direction direction) {
        return armyFormation.getFrontLine(direction);
    }

    @Override
    public int getBasicDamage(Index.Direction direction) {
        return 0;
    }

    @Override
    public Index.Direction getDirectionTo(Combatant combatant) {
        Index combPos = combatant.getCombatantField().getFieldMapPos();
        return fieldPos.whatDirection(combPos);
    }

    @Override
    public ArrayList<Battle> getBattles(){
        return battles;
    }

    @Override
    public double getHealth(){
        return 0;
    }

    public ArmyFormation getArmyFormation() {
        return armyFormation;
    }
}