package Foundation.Runnable;

import Foundation.Elements.City;
import Foundation.Person.Person;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.AI.StupidAI.StupidCountryAI;
import Foundation.Runnable.Actors.CountryActor;
import Foundation.Territory;
import Utils.Broadcaster;
import Utils.Geometry.Index;
import Utils.Subscription;

import java.util.ArrayList;

public class Country implements RunEntity, Broadcaster {

    private String name;


    private ArrayList<City> cities;
    private City capital;

    private Territory territory;

    private Person king;

    private CountryActor actor;

    public Country(String name, Person king, City capital){
        this.name = name;
        cities = new ArrayList<>();
        //subscriptions = new ArrayList<>();
        territory = new Territory();
        this.king = king;
        actor = new CountryActor(king, this);
        AI countryAI = new StupidCountryAI(actor, capital.getTime());
        actor.setAi(countryAI);
        this.capital = capital;
        addCity(capital);
        capital.getParent().getMap().getGameEngine().addRunEntity(this);
        capital.getParent().getMap().getGameEngine().addRunEntity(countryAI);
    }

    public void addCity(City city){
        if (cities.contains(city)) return;
        city.setCountry(this);
        cities.add(city);
        territory.add(city.getTerritory());
    }

    public void addTerritory(Index index){
        territory.add(index);
    }

    public Territory getTerritory() {
        return territory;
    }


    public ArrayList<City> getCities() {
        return cities;
    }

    @Override
    public void run() {
    }

    public City getCapital() {
        return capital;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "name":
                return name;
        }
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {

    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
    }

    public Person getKing() {
        return king;
    }
}
