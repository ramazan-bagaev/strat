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
        people.add(person);
    }

    public void removePerson(Person person){
        people.remove(person);
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
}
