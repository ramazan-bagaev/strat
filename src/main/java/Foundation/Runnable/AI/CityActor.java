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
import Utils.TimeMeasurer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class CityActor extends Actor {

    private City city;
    private Random random;

    TimeMeasurer timeMeasurer;

    Territory freeFields;
    Territory notFree;
    LinkedList<Person> rightPeople;

    public CityActor(Person actorPerson, City city, Time time) {
        super(actorPerson, time);
        this.city = city;
        this.random = city.getParent().getRandom();

        freeFields = new Territory();
        notFree = new Territory();
        rightPeople = new LinkedList<>();
        timeMeasurer = new TimeMeasurer();
    }

    public void createRandomManor(){
        Society society = city.getSociety();
        FieldMap fieldMap = city.getMap();
        freeFields.clear();
        notFree.clear();
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
        freeFields.clear();
        notFree.clear();
        for (Manor man: manors){
            notFree.add(man.getTerritory());
        }
        for(Index index: territory.getTerritory()){
            for (Index.Direction direction: Index.getAllDirections()){
                Index newIndex = index.add(new Index(direction));
                if (!cityTerritory.contains(newIndex)) continue;
                if (notFree.contains(newIndex)) continue;
                if (city.getMap().getFieldByIndex(newIndex).getCity() != null) continue;
                freeFields.add(newIndex);
            }
        }
        if (freeFields.size() == 0) return;
        Index randIndex = freeFields.getTerritory().get(random.nextInt(freeFields.size()));
        manor.addTerritory(randIndex);
    }

    public void giveRandomManorPeople(){
        ArrayList<Manor> manors = city.getManors();
        if (manors.size() == 0) return;
        Manor manor = manors.get(random.nextInt(manors.size()));
        Society society = city.getSociety();
        ArrayList<Person> peo = society.getPeople().getPersonArray();
        rightPeople.clear();
        for (Person person: peo){
            if (person.getKasta() != Person.Kasta.High && person.getKasta() != Person.Kasta.Royal) rightPeople.add(person);
        }
        if (rightPeople.size() == 0) return;
        int randInt = random.nextInt(rightPeople.size());
        People peopleForManor = new People();
        for(int i = 0; i < randInt; i++){
            int randIndex = random.nextInt(rightPeople.size());
            Person person = rightPeople.get(randIndex);
            rightPeople.remove(person);
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
        if (time.getDate().weekDay != 1){
            return;
        }
        int rand = random.nextInt(100);
        if (rand <= 45){
            giveRandomManorTerritory();
            return;
        }
        if (rand <= 90) {
            giveRandomManorPeople();
            return;
        }
        createRandomManor();
    }
}
