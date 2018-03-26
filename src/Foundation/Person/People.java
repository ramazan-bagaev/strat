package Foundation.Person;

import Foundation.Broadcaster;
import Foundation.Field;
import Utils.Content;
import Utils.Subscription;

import java.util.ArrayList;

public class People extends Broadcaster{

    protected Content amountContent;

    private ArrayList<Person> people;

    public People(People people) {
        this.people = new ArrayList<>(people.getPersonArray());
        amountContent = new Content();
    }

    public People(){
        people = new ArrayList<>();
        amountContent = new Content();
    }

    public int getAmount(){
        return people.size();
    }

    public void addPerson(Person person){
        if (people.contains(person)) return;
        people.add(person);
        amountContent.changed();
    }

    public void addPeople(People people){
        for(Person person: people.getPersonArray()){
            if (this.people.contains(person)) return;
            this.people.add(person);
        }
        amountContent.changed();
    }

    public void removePerson(Person person){
        if (people.contains(person)){
            people.remove(person);
            amountContent.changed();
        }
    }

    public ArrayList<Person> getPersonArray(){
        return people;
    }


    public boolean contains(Person person){
        return people.contains(person);
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return String.valueOf(getAmount());
        }
        return Broadcaster.noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "amount":
                amountContent.subscribe(subscription);
                break;
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "amount":
                amountContent.unsubscribe(subscription);
                break;
        }
    }
}
