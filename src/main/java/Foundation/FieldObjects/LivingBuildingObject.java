package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Recources.ProductStore;
import Foundation.Window;
import Utils.Coord;
import Utils.Index;
import Windows.FieldObjectWindow.LivingBuildingInfoWindow;

import java.util.ArrayList;

public abstract class LivingBuildingObject extends BuildingObject{

    protected int capacity;
    protected People people;


    protected ProductStore store;

    public LivingBuildingObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        capacity = size.x * size.y;
        people = new People();
        store = new ProductStore(this);
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

    public People getPeople() {
        return people;
    }

    public ProductStore getStore() {
        return store;
    }


    @Override
    public Window getInfoWindow(){
        return new LivingBuildingInfoWindow(this,
                this.getParent().getMap().getGameEngine().getGameWindowElement().getParent().getParent());
    }

    @Override
    public boolean isLivingBuilding(){
        return true;
    }
}
