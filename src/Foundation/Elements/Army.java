package Foundation.Elements;

import Foundation.*;
import Images.ArmyImage;

import java.util.LinkedList;

public class Army extends Element {

    protected People people;

    private boolean isMoving;
    private Date endIntersect;

    private Field destination;
    private LinkedList<Field> path;

    public Army(People people, Time time, Field field, FieldMap map){
        super(Type.Army, time, field, map);
        this.people = people;
        isMoving = false;
        setShapes();
    }

    public void setShapes(){
        Coord fieldPos = new Coord(getParent().getFieldMapPos());
        fieldPos.x *= getParent().getSize();
        fieldPos.y *= getParent().getSize();
        setBasicShapes(new ArmyImage(fieldPos, new Coord(getParent().getSize(), getParent().getSize()), null).getBasicShapes());
    }

    public void action(Field field){
        //if (field.getCity() != null) t;
        //if (field.getArmy() != null) r;
        move(field);
    }

    public void move(Field field){

       // calculatePath();

        if (field.getFieldMapPos().isNeighbor(getParent().getFieldMapPos())){
            instantMove(field);
        }


    }

    public void calculatePath(Field field){
        path.clear();

    }

    public void instantMove(Field field){
        isMoving = true;
       // localDestination = field;
        endIntersect = getTime().getDate().add(getParent().getTimeToIntersect());
    }

    public People getPeople() {
        return people;
    }


}
