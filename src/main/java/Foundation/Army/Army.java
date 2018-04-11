package Foundation.Army;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Runnable.RunEntity;
import Utils.Coord;
import Utils.Index;
import Utils.PathFinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class Army implements RunEntity {

    private final int MAX_WIDTH = 5;

    public final double X_D;

    private final int MAX_LENGTH = 4;

    public final double Y_D;

    public enum State{
        Standing, Moving, Fighting
    }

    private ArrayList<ArmyAtom> armyList;

    private Index pos;
    private People people;

    private FieldMap fieldMap;

    private Time time;
    private PathFinder pathFinder;
    private Index destination;
    private LinkedList<Index> path;

    private State currentState;

    public Army(Index pos, People people, FieldMap fieldMap, Time time){
        this.time = time;
        this.pos = new Index(pos);
        this.people = people;
        this.fieldMap = fieldMap;
        this.armyList = new ArrayList<>();
        X_D = fieldMap.getFieldSize()/2 / MAX_WIDTH;
        Y_D = fieldMap.getFieldSize()/2 / MAX_LENGTH;
        initArmyList();
        currentState = State.Standing;
        pathFinder = new PathFinder(fieldMap);
        Field field = fieldMap.getFieldByIndex(pos);
        field.createAndAddArmy(this);
    }

    private void initArmyList(){
        ArrayList<Person> personArray = people.getPersonArray();
        int size = personArray.size();
        if (size > MAX_WIDTH * MAX_LENGTH) size = MAX_WIDTH * MAX_LENGTH;
        if (size == 0) return;
        int x = 0;
        int y = 0;
        Coord corner = new Coord(fieldMap.getFieldSize()/4, fieldMap.getFieldSize()/4);
        for(Person person: personArray){
            ArmyAtom armyAtom = new ArmyAtom(this, pos, corner.add(new Coord(x*X_D, y*Y_D)));
            Index index = new Index(x, y);
            armyAtom.setArmyPosIndex(index);
            armyList.add(armyAtom);
            x++;
            if (x == MAX_WIDTH){
                x = 0;
                y++;
            }
        }
    }

    public void action(Index pos){
        Field field = fieldMap.getFieldByIndex(pos);
        if (field.getCity() != null){
            return;
        }
        if (field.getArmyElement() != null){
            return;
        }
        move(pos);
    }

    public void move(Index destination){
        path = null;
        Field field = fieldMap.getFieldByIndex(destination);
        if (field == null) return;
        if (field.getGroundType() == Ground.GroundType.Water || field.getGroundType() == Ground.GroundType.Rock) return;
        this.destination = destination;
        setPath();

    }

    public void moveToNeighbor(Index destination){
        Field oldField = fieldMap.getFieldByIndex(pos);
        oldField.removerArmy();
        pos = new Index(destination);
        Field field = fieldMap.getFieldByIndex(pos);
        field.createAndAddArmy(this);
    }

    public People getPeople(){
        return people;
    }


    public FieldMap getFieldMap() {
        return fieldMap;
    }

    public Index getPos() {
        return pos;
    }

    public void setPath(){
        path = pathFinder.getPath(pos, destination);
    }

    public ArrayList<ArmyAtom> getArmyList() {
        return armyList;
    }

    @Override
    public void run() {
        if (path != null){
            if (path.size() == 0) return;
            Index destination = path.getFirst();
            path.removeFirst();
            moveToNeighbor(destination);
        }
    }
}
