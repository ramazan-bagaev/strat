package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.PalaceObject;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Generation.NameGenerator;
import Utils.Geometry.Index;

import java.util.Random;

public class CityObjectsGenerator {

    private Field field;
    private FieldObjects fieldObjects;
    private Random random;

    private NameGenerator nameGenerator;

    public void generate(Field field){
        init(field);
        generateDwellers();
    }

    private void init(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        this.nameGenerator = new NameGenerator(random);
    }

    private void generateDwellers(){
        Society society = field.getCity().getSociety();
        int population = random.nextInt(1000);
        for(int i = 0; i < population; i++){
            Person.Kasta kast;// = Person.Kasta.Low;
            int kastNum = random.nextInt(100);
            if (kastNum < 70){
                kast = Person.Kasta.Low;
            }
            else if (kastNum < 90){
                kast = Person.Kasta.Middle;
            }
            else{
                kast = Person.Kasta.High;
            }
            Person person = new Person(nameGenerator.generate(), null, kast);
            society.addPerson(person);
        }
    }

    private void initTransportNetElement(){
        int cellAmount = field.getCellAmount();
        Index roadStart = new Index(random.nextInt(cellAmount), random.nextInt(cellAmount));
        Index roadEnd = new Index(random.nextInt(cellAmount), random.nextInt(cellAmount));
    }

    private void addPalace(){
        Index pos = field.getFieldObjects().getPosForBuilding(new Index(8, 4));
        if (pos != null) {
            PalaceObject palaceObject = new PalaceObject(field, pos);
            fieldObjects.addBuilding(palaceObject);
        }
    }
}
