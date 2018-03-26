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

    private int efficiency;


    public WorkElement(Type type, People people, Time time, Field parent, FieldMap map) {
        super(type, time, parent, map);
        this.people = people;
        efficiency = 10;
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


    public People getPeople() {
        return people;
    }

    public int getEfficiency() {
        return efficiency;
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
