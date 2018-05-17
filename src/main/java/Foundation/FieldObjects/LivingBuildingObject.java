package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Person.People;
import Foundation.Person.Person;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

public abstract class LivingBuildingObject extends BuildingObject{

    protected int capacity;
    protected People people;

    public LivingBuildingObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        capacity = size.x * size.y;
        people = new People();
    }

    public void addPerson(Person person){
        people.addPerson(person);
    }


    public void removePerson(Person person){
        people.removePerson(person);
    }

    public int getCapacity(){
        return capacity;
    }
}
