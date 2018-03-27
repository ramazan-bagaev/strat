package Foundation.Runnable.AI;

import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Person.Society;
import Foundation.Person.Person;
import Foundation.Territory;
import Foundation.Time;
import Utils.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ManorActor extends Actor{

    private Manor manor;

    private Random random;

    public ManorActor(Person actorPerson, Manor manor, Time time) {
        super(actorPerson, time);
        this.manor = manor;
        this.random = manor.getParent().getRandom();
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
            manor.createVillage(index, steward);
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
        LinkedList<Person> rigthPeople = new LinkedList<>();
        for (Person person: peo){
            if (person.getKasta() == Person.Kasta.Low) rigthPeople.add(person);
        }
        if (rigthPeople.size() == 0) return;
        int randInt = random.nextInt(rigthPeople.size());
        People villagePeople = new People();
        for(int i = 0; i < randInt; i++){
            int randIndex = random.nextInt(rigthPeople.size());
            Person person = rigthPeople.get(randIndex);
            rigthPeople.remove(person);
            villagePeople.addPerson(person);
            manor.removePerson(person);
        }
        village.addPeople(villagePeople);
    }

    @Override
    public void makeDecision() {
        if (manor.getVillages().size() == 0){
            createRandomVillage();
            return;
        }
        if (time.getDate().weekDay != 1) return;
        int rand = random.nextInt(100);
        if (rand < 90){
            giveRandomVillagePeople();
            return;
        }
        createRandomVillage();
    }
}
