package Foundation.Elements;

import Foundation.*;
import Foundation.FieldObjects.BuildingObject.ManorObject;
import Foundation.Person.Person;
import Foundation.Runnable.AI.AI;
import Generation.NameGenerator;
import Images.ManorImage;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;

import java.util.ArrayList;
import java.util.Random;

public class Manor extends HabitableFieldElement {


    private ArrayList<Village> villages;

    private Person lord;
    private Territory territory;
    private City city;


    public static Manor constructManorWithRandomPeople(Field parent, City city){
        Manor res = new Manor(parent, city);
        res.addRandomPeople();
        res.fillField();
        return res;
    }

    public Manor(Field parent, City city) {
        super(Type.Manor, parent);
        this.city = city;
        parent.setManor(this);
        city.addManor(this);
        territory = new Territory();
        territory.add(parent.getFieldMapPos());
        villages = new ArrayList<>();
        setBasicShapes(new ManorImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack());
    }

    private Manor(Field parent, City city, Person lord) {
        super(Type.Manor, parent);
        this.city = city;
        this.lord = lord;
        parent.setManor(this);
        city.addManor(this);
        society.addPerson(lord);
        territory = new Territory();
        territory.add(parent.getFieldMapPos());
        villages = new ArrayList<>();
        setBasicShapes(new ManorImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack());
    }

    public void addRandomPeople(){
        NameGenerator nameGenerator = new NameGenerator(parent.getRandom());
        lord = new Person(nameGenerator.generate(), null, Person.Kasta.High);
        society.addPerson(lord);
        Random random = parent.getRandom();
        int amount = random.nextInt(100);
        for(int i = 0; i < amount; i++){
            Person person = new Person(nameGenerator.generate(), null, Person.Kasta.Low);
            society.addPerson(person);
        }
    }

    public void fillField(){
        int cellAmount = parent.getCellAmount();
        ManorObject manorObject = new ManorObject(parent, new Index(cellAmount/2 -3, cellAmount/2 - 3));
        parent.addFieldObject(manorObject);
    }

    public void addTerritory(Index pos) {
        territory.add(pos);
    }

    public Territory getTerritory() {
        return territory;
    }

    public City getCity() {
        return city;
    }

    public void createVillage(Index point, Person steward){
        if (!territory.contains(point)) return;
        Field field = map.getFieldByIndex(point);
        if (field.getVillage() != null) return; // TODO: check if there are other construction like fishing village, sawmill or mine
       // society.removePerson(steward);
        //Village village = Village.constructEmptyVillageWithSteward(time, field, map, steward, this);//new Village(time, field, map, steward, this);
        Village village = Village.constructVillageWithRandomPeople(field, this);
        field.setVillage(village);
        villages.add(village);
        map.getGameEngine().getGameWindowElement().setShapes();
        map.getGameEngine().getGameWindowElement().getGameEngine().addRunEntity(village);
    }


    public Person getLord() {
        return lord;
    }

    public void setLord(Person lord) {
        this.lord = lord;
        society.addPerson(lord);
    }


    public ArrayList<Village> getVillages() {
        return villages;
    }

    public void addVillage(Village village){
        villages.add(village);
    }
}
