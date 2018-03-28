package Foundation.Runnable.AI;

import Foundation.Elements.City;
import Foundation.FieldMap;
import Foundation.Person.Person;
import Foundation.Runnable.Country;
import Foundation.Territory;
import Foundation.Time;
import Utils.Index;
import Utils.TimeMeasurer;

import java.util.ArrayList;
import java.util.Random;

public class CountryActor extends Actor {

    private Country country;
    private Random random = new Random();
    Territory countriesTerritory;
    TimeMeasurer timeMeasurer;

    public CountryActor(Person actorPerson, Country country, Time time) {
        super(actorPerson, time);
        this.country = country;
        countriesTerritory = new Territory();
        timeMeasurer = new TimeMeasurer();
    }

    private void giveRandomCityTerritory(){
        ArrayList<City> cities = country.getCities();
        City city = cities.get(random.nextInt(cities.size()));
        ArrayList<Country> countries = city.getMap().getGameEngine().getCountries();
        FieldMap fieldMap = city.getMap();
        Territory cityTerritory = city.getTerritory();
        countriesTerritory.clear();
        for(Country country: countries){
            countriesTerritory.add(country.getTerritory());
        }
        ArrayList<Index> available = new ArrayList<>();
        for(Index index: cityTerritory.getTerritory()){
            for(Index.Direction direction: Index.getAllDirections()){
                Index newIndex = index.add(new Index(direction));
                if (countriesTerritory.contains(newIndex)) continue;
                if (fieldMap.getFieldByIndex(newIndex) == null) continue;
                available.add(newIndex);
            }
        }
        Index index = available.get(random.nextInt(available.size()));
        city.addTerritory(index);
    }

    @Override
    public void makeDecision() {
        timeMeasurer.start("country run");
        int rand = random.nextInt(100);
        if (rand <= 90){
            giveRandomCityTerritory();
            timeMeasurer.stop(1);
            return;
        }
    }
}
