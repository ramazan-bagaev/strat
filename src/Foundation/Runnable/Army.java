package Foundation.Runnable;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Person.People;
import Utils.PathFinder;

import java.util.LinkedList;

public class Army implements RunEntity {

    public enum State{
        Standing, Moving, Fighting
    }

    private Coord pos;
    private People people;
    private FieldMap fieldMap;

    private Time time;
    private PathFinder pathFinder;
    private Coord destination;
    private LinkedList<Coord> path;

    private State currentState;

    public Army(Coord pos, People people, FieldMap fieldMap, Time time){
        this.time = time;
        this.pos = new Coord(pos);
        this.people = people;
        this.fieldMap = fieldMap;
        currentState = State.Standing;
        pathFinder = new PathFinder(fieldMap);
        Field field = fieldMap.getFieldByIndex(pos);
        field.createAndAddArmy(this);
    }

    public void action(Coord pos){
        Field field = fieldMap.getFieldByIndex(pos);
        if (field.getCity() != null){
            return;
        }
        if (field.getArmyElement() != null){
            return;
        }
        move(pos);
    }

    public void move(Coord destination){
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

    public void moveToNeighbor(Coord destination){
        Field oldField = fieldMap.getFieldByIndex(pos);
        oldField.removerArmy();
        pos = new Coord(destination);
        Field field = fieldMap.getFieldByIndex(pos);
        field.createAndAddArmy(this);
    }

    public People getPeople() {
        return people;
    }

    public Coord getPos() {
        return pos;
    }

    public void setPath(){
        path = pathFinder.getPath(pos, destination);
    }

    @Override
    public void run() {
        if (path != null){
            if (path.size() == 0) return;
            Coord destination = path.getFirst();
            path.removeFirst();
            moveToNeighbor(destination);
        }
    }
}
