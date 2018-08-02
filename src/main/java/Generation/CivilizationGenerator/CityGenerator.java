package Generation.CivilizationGenerator;

import Foundation.Elements.City;
import Foundation.FieldObjects.BuildingObject.CityHouseObject;
import Foundation.FieldObjects.BuildingObject.LivingBuildingObject;
import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.FieldObjects.BuildingObject.PalaceObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.Person.People;
import Foundation.Person.Person;
import Generation.FieldObjectGenerator.CityObjectsGenerator;
import Foundation.Field;
import Generation.NameGenerator;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class CityGenerator{

    private NameGenerator nameGenerator;
    private CityObjectsGenerator cityObjectsGenerator;
    private Field field;

    private Random random;

    public CityGenerator(Random random){
        this.random = random;
        cityObjectsGenerator = new CityObjectsGenerator();
        nameGenerator = new NameGenerator(random);
    }

    public City generateCity(Field field) {
        this.field = field;
        return createCity();
    }

    private City createCity(){
        City newCity = new City(nameGenerator.generate(), field);
        field.setCity(newCity);
        setTerritory(newCity);
        setPopulation(newCity);
        setFieldObjects(newCity);
        return newCity;
    }

    private void setTerritory(City city){
        Index pos = field.getFieldMapPos();
        for(int i = -3; i < 4; i++){
            for(int j = -3; j < 4; j++){
                city.addTerritory(pos.add(new Index(i, j)));
            }
        }
    }

    private void setPopulation(City city){
        People people = new People();
        int population = random.nextInt(1000);
        for(int i = 0; i < population; i++){
            Person.Kasta kasta;// = Person.Kasta.Low;
            int kastaNum = random.nextInt(100);
            if (kastaNum < 70){
                kasta = Person.Kasta.Low;
            }
            else if (kastaNum < 90){
                kasta = Person.Kasta.Middle;
            }
            else{
                kasta = Person.Kasta.High;
            }
            Person newPerson = new Person(nameGenerator.generate(), null, kasta);
            people.addPerson(newPerson);
        }
        city.addPeople(people);
    }

    private void setFieldObjects(City city){
        /*FieldObjects fieldObjects = field.getFieldObjects();
        Random random = field.getRandom();
        int cellAmount = field.getCellAmount();
        Index pos = field.getFieldObjects().getPosForBuilding(new Index(8, 4));
        if (pos != null) {
            PalaceObject palaceObject = new PalaceObject(field, pos);
            fieldObjects.addBuilding(palaceObject);
        }
        int populace = city.getSociety().getAmount();
        ArrayList<Person> people = city.getSociety().getPeople().getPersonArray();
        int k = 0;
        while(true){
            k++;
            //x = random.nextInt(cellAmount);
            //y = random.nextInt(cellAmount);
            int sizeX = random.nextInt(3) + 2;
            int sizeY = random.nextInt(3) + 2;
            //Index pos = new Index(x, y);
            Index size = new Index(sizeX, sizeY);
            //OccupationPiece piece = field.getFieldObjects().getMinSpace(size);
            pos = field.getFieldObjects().getPosForBuilding(size);
            if (pos != null){
                LivingBuildingObject buildingObject = new CityHouseObject(field, pos, size);
                for(int i = 0; i < sizeX * sizeY; i++){
                    populace--;
                    if (populace < 0) break;
                    buildingObject.addPerson(people.get(populace));
                }
                fieldObjects.addBuilding(buildingObject);
            }
            if (populace <= 0) break;
            if (k > 100) break;
        }



        Index size = new Index(4, 4);
        pos = fieldObjects.getPosForBuilding(size);
        if (pos != null) fieldObjects.addBuilding(new MarketObject(field, pos));*/
        cityObjectsGenerator.generate(field);
    }
}
