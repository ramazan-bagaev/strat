package Foundation.Person;

import Utils.Broadcaster;
import Foundation.Elements.HabitableElement;
import Utils.Subscription;

import java.util.ArrayList;

public class Society implements Broadcaster {



    protected People people;
    protected HabitableElement habitat;

    public Society(HabitableElement habitat){
        people = new People();
        this.habitat = habitat;
    }

    public void removePerson(Person person){
        people.removePerson(person);
    }

    public void addPerson(Person person){
        person.setSociety(this);
        people.addPerson(person);
    }

    public void addPeople(People people){
        for(Person person: people.getPersonArray()){
            addPerson(person);
        }
    }

    public People getPeople(){
        return people;
    }

    public People getPeopleCopy(){
        return new People(people);
    }


    public Person getLord(){
        ArrayList<Person> lords = new ArrayList<>();
        for(Person person: people.getPersonArray()){
            if (person.getKasta() == Person.Kasta.High) lords.add(person);
        }
        if (lords.size() == 0) return null;
        int index = habitat.getParent().getRandom().nextInt(lords.size());
        return lords.get(index);
    }

    public Person getSteward(){
        ArrayList<Person> stewards = new ArrayList<>();
        for(Person person: people.getPersonArray()){
            if (person.getKasta() == Person.Kasta.Middle) stewards.add(person);
        }
        if (stewards.size() == 0) return null;
        int index = habitat.getParent().getRandom().nextInt(stewards.size());
        return stewards.get(index);
    }

    public int getAmount(){
        return people.getAmount();
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return people.getValue("amount");
        }
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        people.subscribe(key, subscription);
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {

    }
}
