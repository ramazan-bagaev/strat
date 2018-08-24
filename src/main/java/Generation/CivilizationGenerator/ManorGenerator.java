package Generation.CivilizationGenerator;

import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.Field;
import Foundation.Person.People;
import Foundation.Person.Person;
import Generation.FieldObjectGenerator.ManorObjectsGenerator;
import Generation.NameGenerator;
import Utils.Geometry.Index;

import java.util.Random;

public class ManorGenerator {

    private Field field;
    private Random random;

    private ManorObjectsGenerator manorObjectsGenerator;
    private NameGenerator nameGenerator;

    public ManorGenerator(Random random){
        this.random = random;
        this.nameGenerator = new NameGenerator(random);
        this.manorObjectsGenerator = new ManorObjectsGenerator();
    }

    public void generateManor(Field field){
        this.field = field;
        createManor();
    }

    private void createManor(){
        Manor manor = new Manor(field, field.getOwner());
        setPopulation(manor);
        setTerritory(manor);
        setFieldObjects();
    }

    private void setPopulation(Manor manor){
        People people = new People();
        int population = random.nextInt(100);
        for(int i = 0; i < population; i++){
            Person.Kasta kasta;// = Person.Kasta.Low;
            int kastaNum = random.nextInt(100);
            if (kastaNum < 80){
                kasta = Person.Kasta.Low;
            }
            else{
                kasta = Person.Kasta.Middle;
            }
            Person newPerson = new Person(nameGenerator.generate(), null, kasta);
            people.addPerson(newPerson);
        }
        Person lord = new Person(nameGenerator.generate(), null, Person.Kasta.High);
        manor.setLord(lord);
        people.addPerson(lord);
        manor.addPeople(people);
    }

    private void setTerritory(Manor manor){
        Index pos = manor.getParent().getFieldMapPos();
        for(Index.Direction direction: Index.getAllDirections()){
            if (random.nextInt(4) > 0) continue;
            Index newPos = pos.add(Index.getUnitIndex(direction));
            if (field.getMap().getFieldByIndex(newPos) == null) continue;
            manor.addTerritory(newPos);
        }
    }

    private void setFieldObjects(){
        manorObjectsGenerator.generate(field);
    }
}
