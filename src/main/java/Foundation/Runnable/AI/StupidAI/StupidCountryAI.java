package Foundation.Runnable.AI.StupidAI;

import Foundation.Elements.City;
import Foundation.FieldMap;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.Actors.CountryActor;
import Foundation.Runnable.Country;
import Foundation.Territory;
import Utils.Index;

import java.util.ArrayList;
import java.util.Random;

public class StupidCountryAI extends AI {

    private CountryActor countryActor;
    private Country country;

    private Random random;

    private Territory countriesTerritory;

    public StupidCountryAI(CountryActor countryActor){
        this.countryActor = countryActor;
        this.country = countryActor.getCountry();
        countriesTerritory = new Territory();
        random = new Random();
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
        countryActor.addCityTerritory(index, city);
    }

    @Override
    public void makeDecision() {
        int rand = random.nextInt(100);
        if (rand <= 90){
            giveRandomCityTerritory();
            return;
        }
    }
}