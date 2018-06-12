package Foundation.Runnable.Actors;

import Foundation.Elements.City;
import Foundation.Person.Person;
import Foundation.Runnable.Country;
import Utils.Geometry.Index;

public class CountryActor extends Actor {

    private Country country;

    public CountryActor(Person actorPerson, Country country) {
        super(actorPerson);
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void addCityTerritory(Index pos, City city){
        city.addTerritory(pos);
    }


}
