package Foundation.Runnable.AI.StupidAI;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.Actors.CityActor;
import Utils.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class StupidCityAI extends AI{

    private CityActor cityActor;
    private City city;
    private Random random;
    private Time time;

    private Territory freeFields;
    private Territory notFree;
    private LinkedList<Person> rightPeople;

    private Date previousDate;

    public StupidCityAI(CityActor cityActor, Time time){
        this.cityActor = cityActor;
        this.city = cityActor.getCity();
        this.random = city.getParent().getRandom();
        this.time = time;

        freeFields = new Territory();
        notFree = new Territory();
        rightPeople = new LinkedList<>();
        previousDate = new Date(time.getDate());
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
            cityActor.createManor(index, lord);
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
        cityActor.addManorTerritory(randIndex, manor);
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
        }
        cityActor.addManorPeople(peopleForManor, manor);
    }

    @Override
    public void makeDecision() {
        if (city.getManors().size() == 0){
            createRandomManor();
            return;
        }
        if (time.getDate().sameAs(previousDate)) return;
        if (time.getDate().weekDay != 1){
            return;
        }
        previousDate.setDate(time.getDate());
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
