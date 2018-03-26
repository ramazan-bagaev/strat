package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Runnable.RunnableElement;

public abstract class HabitableElement extends RunnableElement {

    protected ResourceStore resourceStore;
    protected Society society;

    public HabitableElement(Type type, Time time, Field parent, FieldMap map) {
        super(type, time, parent, map);
        society = new Society(this);
        resourceStore = new ResourceStore();
        parent.getMap().getGameEngine().addRunEntity(this);
    }

    public void removePerson(Person person){
        society.removePerson(person);
    }

    public void addPeople(People people){
        this.society.addPeople(people);
    }


    public ResourceStore getResourceStore() {
        return resourceStore;
    }

    public Society getSociety() {
        return society;
    }

    @Override
    public void run(){
        int amount = society.getAmount();
        for(Resource resource: resourceStore.getResources()){
            if (resource.getType() == Resource.Type.Food){
                amount -= resource.consume(amount);
                if (amount == 0) return;
            }
        }
    }


}
