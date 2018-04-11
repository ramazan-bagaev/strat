package Foundation.Runnable.AI.StupidAI;

import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.Actors.ManorActor;
import Foundation.Territory;
import Foundation.Time;
import Utils.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class StupidManorAI extends AI{

    private ManorActor manorActor;
    private Manor manor;
    private Random random;
    private Time time;

    private LinkedList<Person> rightPeople;

    public StupidManorAI(ManorActor manorActor, Time time){
        this.manorActor = manorActor;
        this.manor = manorActor.getManor();
        random = manor.getParent().getRandom();
        rightPeople = new LinkedList<>();
        this.time = time;
    }


    public void createRandomVillage(){
        Society society = manor.getSociety();
        FieldMap fieldMap = manor.getParent().getMap();
        Territory freeFields = new Territory();
        for(Index index: manor.getTerritory().getTerritory()){
            Field field = fieldMap.getFieldByIndex(index);
            if (field.getGroundType() != Ground.GroundType.Soil) continue;
            if (field.getVillage() != null) continue;
            if (field.getManor() != null) continue;
            if (field.getTree() != null) continue;
            if (field.getFarm() != null) continue;
            freeFields.add(index);
        }
        if (freeFields.size() == 0) return;
        int ranInd = random.nextInt(freeFields.size());
        Index index = freeFields.getTerritory().get(ranInd);
        Person steward = society.getSteward();
        if (steward != null){
            manorActor.createVillage(index, steward);
            return;
        }
    }

    public void giveRandomVillagePeople(){
        ArrayList<Index> villages = manor.getVillages();
        if (villages.size() == 0) return;
        Index villageIndex = villages.get(random.nextInt(villages.size()));
        Field field = manor.getParent().getMap().getFieldByIndex(villageIndex);
        Village village = field.getVillage();
        Society society = manor.getSociety();
        ArrayList<Person> peo = society.getPeople().getPersonArray();
        rightPeople.clear();
        for (Person person: peo){
            if (person.getKasta() == Person.Kasta.Low) rightPeople.add(person);
        }
        if (rightPeople.size() == 0) return;
        int randInt = random.nextInt(rightPeople.size());
        People villagePeople = new People();
        for(int i = 0; i < randInt; i++){
            int randIndex = random.nextInt(rightPeople.size());
            Person person = rightPeople.get(randIndex);
            rightPeople.remove(person);
            villagePeople.addPerson(person);
        }
        manorActor.addVillagePeople(villagePeople, village);
    }

    @Override
    public void makeDecision() {
        if (manor.getVillages().size() == 0){
            createRandomVillage();
            return;
        }
        if (time.getDate().weekDay != 1){
            return;
        }
        int rand = random.nextInt(100);
        if (rand < 90){
            giveRandomVillagePeople();
            return;
        }
        createRandomVillage();
    }
}