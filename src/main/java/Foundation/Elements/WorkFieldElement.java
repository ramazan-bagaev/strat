package Foundation.Elements;

import Foundation.Field;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Works.Work;

public abstract class WorkFieldElement extends FieldElement {

    protected People people;
    protected Work work;

    private double distance;
    private int efficiency;

    public WorkFieldElement(Type type, People people, Village village, Field parent){
        super(type, parent);
        this.people = people;
        this.efficiency = 10;
        distance = (int)village.getParent().getFieldMapPos().distance(parent.getFieldMapPos());
    }

    public WorkFieldElement(Type type, People people, Field parent) {
        super(type, parent);
        this.people = people;
        this.efficiency = 10;
        distance = 0;
    }


    private double distanceFine(){
        if (distance <= 3) return 1;
        if (distance <  6) return 0.5;
        return 0;
    }


    public People getPeople() {
        return people;
    }

    public int getEfficiency() {
        return (int) (efficiency * distanceFine());
    }

    public Work getWork(){
        return work;
    }
}
