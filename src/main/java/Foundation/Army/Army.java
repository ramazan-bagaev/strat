package Foundation.Army;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Runnable.RunEntity;
import Utils.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class Army implements RunEntity, Broadcaster {




    public enum State{
        Standing, Moving, Fighting
    }

    private Content movingProgressContent;
    private Content posContent;
    private Content pathContent;


    private Index pos;
    private People people;

    private FieldMap fieldMap;

    private Time time;
    private PathFinder pathFinder;
    private Index destination;
    private LinkedList<Index> path;
    private Army target;

    private int movingProgress;

    private State currentState;

    public Army(Index pos, People people, FieldMap fieldMap, Time time){
        this.time = time;
        this.pos = new Index(pos);
        this.people = people;
        this.fieldMap = fieldMap;
        movingProgressContent = new Content();
        posContent = new Content();
        pathContent = new Content();
        currentState = State.Standing;
        pathFinder = new PathFinder(fieldMap);
        Field field = fieldMap.getFieldByIndex(pos);
        field.createAndAddArmy(this);
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
        if (path == null){
            target = null;
            return;
        }
        pathContent.changed();
    }

    public void moveToNeighbor(Index localDestination){
        Field field = fieldMap.getFieldByIndex(localDestination);
        if (field.getArmyElement() != null){
            move(destination);
            return;
        }
        Field oldField = fieldMap.getFieldByIndex(pos);
        oldField.removerArmy();
        pos = new Index(localDestination);
        posContent.changed();
        field.createAndAddArmy(this);
        movingProgressContent.changed();
        movingProgress = 0;
        if (destination.equals(pos)){
            path = null;
            pathContent.changed();
        }
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
        movingProgress = 0;
        movingProgressContent.changed();
        pathContent.changed();
    }

    public void setNearPath(Index destination){
        path = pathFinder.getNearPath(pos, destination);
        if (path == null){
            target = null;
            return;
        }
        this.destination = path.getLast();
        movingProgress = 0;
        movingProgressContent.changed();
        pathContent.changed();
    }

    public int getMovingProgress() {
        return movingProgress;
    }

    public LinkedList<Index> getPath() {
        return path;
    }

    @Override
    public void run() {
        if (target != null){
            if (target.getPos().isNeighbor(pos)){

            }
        }
        if (path != null){
            movingProgress+=1;
            movingProgressContent.changed();
            if (movingProgress < 100) return;
            if (path.size() == 0) return;
            Index destination = path.getFirst();
            path.removeFirst();
            pathContent.changed();
            moveToNeighbor(destination);
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
            case "pos":
                posContent.subscribe(subscription);
                break;
            case "path":
                pathContent.subscribe(subscription);
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "movingProgress":
                movingProgressContent.unsubscribe(subscription);
                break;
            case "pos":
                posContent.unsubscribe(subscription);
                break;
            case "path":
                pathContent.unsubscribe(subscription);
        }
    }
}
