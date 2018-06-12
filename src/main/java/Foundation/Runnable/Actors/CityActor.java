package Foundation.Runnable.Actors;

import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Person.People;
import Foundation.Person.Person;
import Utils.Geometry.Index;

public class CityActor extends Actor {

    private City city;

    public CityActor(Person actorPerson, City city) {
        super(actorPerson);
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void createManor(Index pos, Person lord){
        city.createManor(pos, lord);
    }

    public void addManorTerritory(Index pos, Manor manor){
        manor.addTerritory(pos);
    }

    public void addManorPeople(People people, Manor manor){
        for(Person person: people.getPersonArray()){
            city.removePerson(person);
        }
        manor.addPeople(people);
    }




}
