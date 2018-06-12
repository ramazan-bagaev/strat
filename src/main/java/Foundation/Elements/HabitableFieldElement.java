package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Recources.Resource;
import Foundation.Recources.ResourceStore;
import Foundation.Runnable.RunnableFieldElement;

public abstract class HabitableFieldElement extends RunnableFieldElement {

    protected ResourceStore resourceStore;
    protected Society society;

    public HabitableFieldElement(Type type, Field parent) {
        super(type, parent);
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
