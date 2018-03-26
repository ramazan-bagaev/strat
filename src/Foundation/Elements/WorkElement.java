package Foundation.Elements;

import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Time;
import Foundation.WorksP.Work;

public abstract class WorkElement extends Element{

    protected People people;
    protected Work work;

    private double distance;
    private int efficiency;

    public WorkElement(Type type, People people, Village village, Time time, Field parent, FieldMap map){
        super(type, time, parent, map);
        this.people = people;
        this.efficiency = 10;
        distance = (int)village.getParent().getFieldMapPos().distance(parent.getFieldMapPos());
    }

    public WorkElement(Type type, People people, Time time, Field parent, FieldMap map) {
        super(type, time, parent, map);
        this.people = people;
        this.efficiency = 10;
        distance = 0;
    }

    public void addPerson(Person person){
        people.addPerson(person);
        person.setWork(work);
    }

    public void removePerson(Person person){
        people.removePerson(person);
        person.setWork(null);
    }

    public void addPeople(People people){
        for(Person person: people.getPersonArray()) person.setWork(work);
        this.people.addPeople(people);
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

    public void removeRandomPeople(int number){
        int total = people.getAmount();
        if (number > total) number = total;
        if (number <= 0) return;
        for(int i = 0; i < number; i++){
            int randIndex = getParent().getRandom().nextInt(people.getAmount());
            Person person = people.getPersonArray().get(randIndex);
            people.removePerson(person);
            person.setWork(null);
        }
    }
}
