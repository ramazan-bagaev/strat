package Foundation.Runnable.AI;

import Foundation.Elements.City;
import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Society;
import Foundation.Person.Person;
import Foundation.Territory;
import Foundation.Time;
import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class CityActor extends Actor {

    private City city;
    private Random random;

    public CityActor(Person actorPerson, City city, Time time) {
        super(actorPerson, time);
        this.city = city;
        this.random = city.getParent().getRandom();
    }

    public void createRandomManor(){
        Society society = city.getSociety();
        FieldMap fieldMap = city.getMap();
        Territory freeFields = new Territory();
        Territory notFree = new Territory();
        for (Manor manor: city.getManors()){
            notFree.add(manor.getTerritory());
        }
        for(Index index: city.getTerritory().getTerritory()){
            if (notFree.contains(index)) continue;
            Field field = fieldMap.getFieldByIndex(index);
            if (field.getGroundType() != Ground.GroundType.Soil && field.getGroundType() != Ground.GroundType.Sand) continue;
            if (field.getVillage() != null) continue;
            if (field.getCity() != null) continue;
            if (field.getTree() != null) continue;
            freeFields.add(index);
        }
        if (freeFields.size() == 0) return;
        int ranInd = random.nextInt(freeFields.size());
        Index index = freeFields.getTerritory().get(ranInd);
        Person lord = society.getLord();
        if (lord != null){
            city.createManor(index, lord);
            return;
        }

    }

    public void giveRandomManorTerritory(){
        ArrayList<Manor> manors = city.getManors();
        if (manors.size() == 0) return;
        Territory cityTerritory = city.getTerritory();
        Manor manor = manors.get(random.nextInt(manors.size()));
        Territory territory = manor.getTerritory();
        Territory availableTerritory = new Territory();
        Territory notAvailableTerritory = new Territory();
        for (Manor man: manors){
            notAvailableTerritory.add(man.getTerritory());
        }
        for(Index index: territory.getTerritory()){
            for (Index.Direction direction: Index.getAllDirections()){
                Index newIndex = index.add(new Index(direction));
                if (!cityTerritory.contains(newIndex)) continue;
                if (notAvailableTerritory.contains(newIndex)) continue;
                if (city.getMap().getFieldByIndex(newIndex).getCity() != null) continue;
                availableTerritory.add(newIndex);
            }
        }
        if (availableTerritory.size() == 0) return;
        Index randIndex = availableTerritory.getTerritory().get(random.nextInt(availableTerritory.size()));
        manor.addTerritory(randIndex);
    }

    public void giveRandomManorPeople(){
        ArrayList<Manor> manors = city.getManors();
        if (manors.size() == 0) return;
        Manor manor = manors.get(random.nextInt(manors.size()));
        Society society = city.getSociety();
        ArrayList<Person> peo = society.getPeople().getPersonArray();
        LinkedList<Person> rigthPeople = new LinkedList<>();
        for (Person person: peo){
            if (person.getKasta() != Person.Kasta.High && person.getKasta() != Person.Kasta.Royal) rigthPeople.add(person);
        }
        if (rigthPeople.size() == 0) return;
        int randInt = random.nextInt(rigthPeople.size());
        People peopleForManor = new People();
        for(int i = 0; i < randInt; i++){
            int randIndex = random.nextInt(rigthPeople.size());
            Person person = rigthPeople.get(randIndex);
            rigthPeople.remove(person);
            peopleForManor.addPerson(person);
            city.removePerson(person);
        }
        manor.addPeople(peopleForManor);
    }

    @Override
    public void makeDecision() {
        if (city.getManors().size() == 0){
            createRandomManor();
            return;
        }
        if (time.getDate().weekDay != 1) return;
        int rand = random.nextInt(100);
        if (rand <= 45){
            giveRandomManorTerritory();
            return;
        }
        if (rand <= 95) {
            giveRandomManorPeople();
            return;
        }
        createRandomManor();
    }
}
