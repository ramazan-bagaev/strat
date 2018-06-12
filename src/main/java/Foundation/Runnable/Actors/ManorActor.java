package Foundation.Runnable.Actors;

import Foundation.Elements.Manor;
import Foundation.Person.Person;
import Utils.Geometry.Index;

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
