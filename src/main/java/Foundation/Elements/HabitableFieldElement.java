package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Products.ProductStore;
import Foundation.Runnable.RunnableFieldElement;

public abstract class HabitableFieldElement extends RunnableFieldElement {

    protected Society society;

    public HabitableFieldElement(Type type, Field parent) {
        super(type, parent);
        society = new Society(this);
        parent.getMap().getGameEngine().addRunEntity(this);
    }

    public void removePerson(Person person){
        society.removePerson(person);
    }

    public void addPeople(People people){
        this.society.addPeople(people);
    }


    public Society getSociety() {
        return society;
    }



}
