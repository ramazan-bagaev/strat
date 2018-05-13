package Foundation.Runnable.Actors;

import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Society;
import Foundation.Person.Person;
import Foundation.Territory;
import Foundation.Time;
import Utils.Index;
import Utils.TimeMeasurer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ManorActor extends Actor {

    private Manor manor;


    public ManorActor(Person actorPerson, Manor manor) {
        super(actorPerson);
        this.manor = manor;
    }

    public Manor getManor() {
        return manor;
    }

    public void createVillage(Index pos, Person steward){
        manor.createVillage(pos, steward);
    }

    /*public void addVillagePeople(People people, Village village){
        for(Person person: people.getPersonArray()){
            manor.removePerson(person);
        }
        village.addPeople(people);
    }*/


}
