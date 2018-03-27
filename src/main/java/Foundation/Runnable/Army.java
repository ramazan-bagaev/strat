package Foundation.Runnable;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Person.Society;
import Utils.Index;
import Utils.PathFinder;

import java.util.LinkedList;

public class Army implements RunEntity {

    public enum State{
        Standing, Moving, Fighting
    }

    private Index pos;
    private Society society;
    private FieldMap fieldMap;

    private Time time;
    private PathFinder pathFinder;
    private Index destination;
    private LinkedList<Index> path;

    private State currentState;

    public Army(Index pos, Society society, FieldMap fieldMap, Time time){
        this.time = time;
        this.pos = new Index(pos);
        this.society = society;
        this.fieldMap = fieldMap;
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
            return;
        }
        move(pos);
    }

    public void move(Index destination){
        path = null;
        Field field = fieldMap.getFieldByIndex(destination);
        if (field == null) return;
        if (field.getGroundType() == Ground.GroundType.Water || field.getGroundType() == Ground.GroundType.Rock) return;
        if (pos.isNeighbor(destination)){
            currentState = State.Moving;
            moveToNeighbor(destination);
            return;
        }
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

    public Society getSociety() {
        return society;
    }

    public Index getPos() {
        return pos;
    }

    public void setPath(){
        path = pathFinder.getPath(pos, destination);
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
