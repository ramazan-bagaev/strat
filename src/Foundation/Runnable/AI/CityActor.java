package Foundation.Runnable.AI;

import Foundation.Elements.City;
import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Time;
import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;

public class CityActor extends Actor {

    private City city;

    public CityActor(Person actorPerson, City city, Time time) {
        super(actorPerson, time);
        this.city = city;
    }

    public void createRandomManor(){
        People people = city.getPeople();
        FieldMap fieldMap = city.getMap();
        ArrayList<Index> freeFields = new ArrayList<>();
        ArrayList<Index> notFree = new ArrayList<>();
        for (Manor manor: city.getManors()){
            notFree.addAll(manor.getTerritory());
        }
        for(Index index: city.getTerritory()){
            if (notFree.contains(index)) continue;
            Field field = fieldMap.getFieldByIndex(index);
            if (field.getGroundType() != Ground.GroundType.Soil && field.getGroundType() != Ground.GroundType.Sand) continue;
            if (field.getVillage() != null) continue;
            if (field.getCity() != null) continue;
            freeFields.add(index);
            Person lord = people.getLord();
            if (lord != null){
                city.createManor(index, lord);
                return;
            }
        }
        if (freeFields.size() == 0) return;
        Index index = freeFields.get(city.getParent().getRandom().nextInt(freeFields.size()));
        Person lord = people.getLord();
        if (lord != null){
            city.createManor(index, lord);
            return;
        }

    }

    public void giveRandomManorTerritory(){
        People people = city.getPeople();
        FieldMap fieldMap = city.getMap();
    }

    @Override
    public void makeDecision() {
        createRandomManor();

    }
}
