package Foundation.FieldObjects.BuildingObject;

import Foundation.Field;
import Foundation.Person.HouseHold;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Products.ProductStore;
import Windows.Window;
import Utils.Geometry.Index;
import Windows.FieldObjectWindow.LivingBuildingInfoWindow;

public abstract class LivingBuildingObject extends BuildingObject{

    protected int capacity;
    protected HouseHold houseHold;

    public LivingBuildingObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        capacity = size.x * size.y;
        houseHold = new HouseHold(new People(), this);
    }

    public void addPerson(Person person){
        houseHold.addPerson(person);
    }


    public void removePerson(Person person){
        houseHold.removePerson(person);
    }

    public int getCapacity(){
        return capacity;
    }

    public People getPeople() {
        return houseHold.getPeople();
    }

    public ProductStore getStore() {
        return houseHold.getProductStore();
    }

    public HouseHold getHouseHold() {
        return houseHold;
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
