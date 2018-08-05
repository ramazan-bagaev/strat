package Generation.CivilizationGenerator;

import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.Field;
import Foundation.Person.People;
import Foundation.Person.Person;
import Generation.FieldObjectGenerator.VillageObjectsGenerator;
import Generation.NameGenerator;

import java.util.Random;

public class VillageGenerator {

    private Field field;
    private Manor manor;

    private Random random;
    private NameGenerator nameGenerator;
    private VillageObjectsGenerator villageObjectsGenerator;

    public VillageGenerator(Random random){
        this.random = random;
        this.nameGenerator = new NameGenerator(random);
        this.villageObjectsGenerator = new VillageObjectsGenerator();
    }

    public void generateVillage(Field field, Manor manor){
        this.field = field;
        this.manor = manor;
        createVillage();
    }

    private void createVillage(){
        Village village = new Village(field, manor);
        setPopulation(village);
        setFieldObjects();
    }

    private void setPopulation(Village village){
        People people = new People();
        int population = random.nextInt(100);
        for(int i = 0; i < population; i++){
            Person.Kasta kasta = Person.Kasta.Low;
            Person newPerson = new Person(nameGenerator.generate(), null, kasta);
            people.addPerson(newPerson);
        }
        Person steward = new Person(nameGenerator.generate(), null, Person.Kasta.Middle);
        village.setSteward(steward);
        people.addPerson(steward);
        village.addPeople(people);
    }

    private void setFieldObjects(){
        villageObjectsGenerator.generate(field);
    }
}
