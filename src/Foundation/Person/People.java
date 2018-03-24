package Foundation.Person;

import Foundation.Broadcaster;
import Foundation.Field;

import java.util.ArrayList;

public class People extends Broadcaster {

    private ArrayList<Person> people;
    private Field field;

    public People(Field field){
        this.field = field;
        people = new ArrayList<>();
    }

    public int getAmount(){
        return people.size();
    }

    public void addPerson(Person person){
        if (people.contains(person)) return;
        people.add(person);
    }

    public void addPeople(ArrayList<Person> people){
        for(Person person: people){
            person.setField(field);
            addPerson(person);
        }
    }

    public void removePerson(Person person){
        if (people.contains(person)){
            people.remove(person);
            person.setField(null);
        }
    }

    public ArrayList<Person> getPersonArray(){
        return people;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return String.valueOf(getAmount());
        }
        return Broadcaster.noResult;
    }

    public Field getField() {
        return field;
    }

    public Person getLord(){
        ArrayList<Person> lords = new ArrayList<>();
        for(Person person: people){
            if (person.getKasta() == Person.Kasta.High) lords.add(person);
        }
        if (lords.size() == 0) return null;
        int index = getField().getRandom().nextInt(lords.size());
        return lords.get(index);
    }
}
