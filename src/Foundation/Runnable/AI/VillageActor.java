package Foundation.Runnable.AI;

import Foundation.Elements.Ground;
import Foundation.Elements.Village;
import Foundation.Elements.WorkElement;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Time;
import Utils.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class VillageActor extends Actor {

    private Village village;

    private Random random;

    public VillageActor(Person actorPerson, Village village, Time time) {
        super(actorPerson, time);
        this.village = village;
        this.random = village.getParent().getRandom();
    }

    public void createRandomWork(){
        FieldMap fieldMap = village.getParent().getMap();
        ArrayList<Index> freeFields = new ArrayList<>();
        for(Index index: village.getManor().getTerritory().getTerritory()){
            Field field = fieldMap.getFieldByIndex(index);
            if (field.getGroundType() == Ground.GroundType.Mud) continue;
            if (field.getVillage() != null) continue;
            if (field.getManor() != null) continue;
            if (field.getTrawler() != null) continue;
            if (field.getFarm() != null) continue;
            if (field.getMine() != null) continue;
            if (field.getSawmill() != null) continue;
            freeFields.add(index);
        }
        if (freeFields.size() == 0) return;
        int ranInd = random.nextInt(freeFields.size());
        Index index = freeFields.get(ranInd);
        Field chosedField = fieldMap.getFieldByIndex(index);
        if (chosedField.getGroundType() == Ground.GroundType.Soil){
            if (chosedField.getTree() == null){
                village.createFarm(index);
                return;
            }
            if (chosedField.getTree() != null){
                village.createSawmill(index);
                return;
            }
        }
        if (chosedField.getGroundType() == Ground.GroundType.Rock){
            village.createMine(index);
            return;
        }
        if (chosedField.getGroundType() == Ground.GroundType.Water){
            if (village.getAvailableWater().contains(index)){
                village.createTrawler(index);
                return;
            }
        }
    }

    public void giveRandomWorkPeople(){
       // ArrayList<Index> villages = manor.getVillages();
        ArrayList<WorkElement> workElements = village.getWorkElements();
        if (workElements.size() == 0) return;
        WorkElement workElement = workElements.get(random.nextInt(workElements.size()));
        //Field field = manor.getParent().getMap().getFieldByIndex(villageIndex);
        //Village village = field.getVillage();
        People peopleForWork = new People();
        People villagePeople = village.getSociety().getPeople();
        for(Person person: villagePeople.getPersonArray()){
            if (person.getWork() == null) peopleForWork.addPerson(person);
        }
        LinkedList<Person> rigthPeople = new LinkedList<>();
        for (Person person: peopleForWork.getPersonArray()){
            if (person.getKasta() == Person.Kasta.Low) rigthPeople.add(person);
        }
        if (rigthPeople.size() == 0) return;
        int randInt = random.nextInt(rigthPeople.size());
        People resultPeople = new People();
        for(int i = 0; i < randInt; i++){
            int randIndex = random.nextInt(rigthPeople.size());
            Person person = rigthPeople.get(randIndex);
            rigthPeople.remove(person);
            resultPeople.addPerson(person);
            person.setWork(workElement.getWork());
        }
        workElement.addPeople(resultPeople);
    }

    @Override
    public void makeDecision() {
        if (village.getWorkElements().size() == 0){
            createRandomWork();
            return;
        }
        int rand = random.nextInt(100);
        if (rand < 50){
            createRandomWork();
            return;
        }
        //giveRandomWorkPeople();
    }
}
