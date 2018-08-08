package Foundation.Person;

import Foundation.FieldObjects.BuildingObject.LivingBuildingObject;
import Foundation.Products.ProductStore;

public class HouseHold{

    private People people;
    private LivingBuildingObject house;
    private ProductStore productStore;


    public HouseHold(People people, LivingBuildingObject house){
        this.people = people;
        this.house = house;
        this.productStore = new ProductStore(house);
    }

    public void addPerson(Person person){
        people.addPerson(person);
    }

    public void removePerson(Person person){
        people.removePerson(person);
    }

    public void setHouse(LivingBuildingObject buildingObject){
        this.house = buildingObject;
        productStore.setStorePlace(house);
    }

    public People getPeople() {
        return people;
    }

    public ProductStore getProductStore() {
        return productStore;
    }

    public LivingBuildingObject getHouse() {
        return house;
    }
}
