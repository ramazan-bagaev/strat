package Foundation.Elements;

import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.ResourceStore;
import Foundation.Runnable.RunableElement;
import Foundation.Time;

public abstract class HabitableElement extends RunableElement {

    protected ResourceStore resourceStore;
    protected Society society;

    public HabitableElement(Type type, Time time, Field parent, FieldMap map) {
        super(type, time, parent, map);
        society = new Society(this);
        resourceStore = new ResourceStore();
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


}
