package Foundation.Runnable.AI.StupidAI;

import Foundation.Date;
import Foundation.Elements.Ground;
import Foundation.Elements.Village;
import Foundation.Elements.WorkFieldElement;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.Actors.VillageActor;
import Foundation.Time;
import Utils.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class StupidVillageAI extends AI {

    private VillageActor villageActor;

    private Village village;

    private Random random;
    private Time time;
    private Date previousDate;

    private ArrayList<Index> freeFields;
    private People peopleForWork;
    private LinkedList<Person> rightPeople;
    private People resultPeople;

    public StupidVillageAI(VillageActor villageActor, Time time){
        this.time = time;
        this.villageActor = villageActor;
        this.village = villageActor.getVillage();
        this.random = village.getParent().getRandom();
        freeFields = new ArrayList<>();
        peopleForWork = new People();
        rightPeople = new LinkedList<>();
        resultPeople = new People();
        previousDate = new Date(time.getDate());
    }

    public void createRandomWork(){
        FieldMap fieldMap = village.getParent().getMap();
        freeFields.clear();
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
                villageActor.addFarm(index);
                return;
            }
            if (chosedField.getTree() != null){
                villageActor.addSawmill(index);
                return;
            }
        }
        if (chosedField.getGroundType() == Ground.GroundType.Rock){
            villageActor.addMine(index);
            return;
        }
        if (chosedField.getGroundType() == Ground.GroundType.Water){
            if (village.getAvailableWater().contains(index)){
                villageActor.addTrawler(index);
                return;
            }
        }
    }

    public void giveRandomWorkPeople(){
        // ArrayList<Index> villages = manor.getVillages();
        ArrayList<WorkFieldElement> workElements = village.getWorkElements();
        if (workElements.size() == 0) return;
        WorkFieldElement workElement = workElements.get(random.nextInt(workElements.size()));
        //Field field = manor.getParent().getMap().getFieldByIndex(villageIndex);
        //Village village = field.getVillage();
        peopleForWork.clear();
        People villagePeople = village.getSociety().getPeople();
        for(Person person: villagePeople.getPersonArray()){
            if (person.getWork() == null) peopleForWork.addPerson(person);
        }
        rightPeople.clear();
        for (Person person: peopleForWork.getPersonArray()){
            if (person.getKasta() == Person.Kasta.Low) rightPeople.add(person);
        }
        if (rightPeople.size() == 0) return;
        int randInt = random.nextInt(rightPeople.size());
        resultPeople.clear();
        for(int i = 0; i < randInt; i++){
            int randIndex = random.nextInt(rightPeople.size());
            Person person = rightPeople.get(randIndex);
            rightPeople.remove(person);
            resultPeople.addPerson(person);
        }
        villageActor.addWorkPeople(workElement, resultPeople);
    }

    @Override
    public void makeDecision() {
        if (village.getWorkElements().size() == 0){
            createRandomWork();
            return;
        }
        if (time.getDate().sameAs(previousDate)) return;
        previousDate.setDate(time.getDate());
        int rand = random.nextInt(100);
        if (rand < 50){
            createRandomWork();
            return;
        }
        giveRandomWorkPeople();
    }
}
