package Foundation.Elements;

import Foundation.*;
import Foundation.FieldObjects.BuildingObject.ManorObject;
import Foundation.Person.Person;
import Foundation.Recources.Resource;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.AI.StupidAI.StupidManorAI;
import Foundation.Runnable.Actors.ManorActor;
import Generation.NameGenerator;
import Images.ManorImage;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;

import java.util.ArrayList;
import java.util.Random;

public class Manor extends HabitableFieldElement {


    private ArrayList<Index> villages;

    private Person lord;
    private Territory territory;
    private City city;

    private ManorActor actor;

    public static Manor constructManorWithRandomPeople(Field parent, City city){
        Manor res = new Manor(parent, city);
        res.addRandomPeople();
        res.fillField();
        return res;
    }

    public Manor(Field parent, City city) {
        super(Type.Manor, parent);
        this.city = city;
        territory = new Territory();
        territory.add(parent.getFieldMapPos());
        villages = new ArrayList<>();
        setBasicShapes(new ManorImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack());
    }

    private Manor(Field parent, City city, Person lord) {
        super(Type.Manor, parent);
        this.city = city;
        this.lord = lord;

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
        villages.add(point);
        map.getGameEngine().getGameWindowElement().setShapes();
        map.getGameEngine().getGameWindowElement().getGameEngine().addRunEntity(village);
    }


    @Override
    public void run() {
        super.run();
        for(Index pos: villages){
            Village village = map.getFieldByIndex(pos).getVillage();
            ArrayList<Resource> resources = village.getPartOfResource(0.9);
            for(Resource resource: resources) resourceStore.addResource(resource);
        }
    }

    public Person getLord() {
        return lord;
    }

    public void setLord(Person lord) {
        this.lord = lord;
        society.addPerson(lord);
        if (actor != null) {
            AI ai = actor.getAi();
            parent.getMap().getGameEngine().removeRunEntity(ai);
        }
        this.actor = new ManorActor(lord, this);
        AI manorAI = new StupidManorAI(actor, time);
        actor.setAi(manorAI);
        parent.getMap().getGameEngine().addRunEntity(manorAI);
    }

    public ArrayList<Resource> getPartOfResource(double part){
        if (part > 1) part = 1;
        if (part < 0) part = 0;
        ArrayList<Resource> result = new ArrayList<>();
        for(Resource resource: resourceStore.getResources()){
            Resource share = resource.getResource((int) (resource.getAmount()*part));
            result.add(share);
        }
        return result;
    }


    public ArrayList<Index> getVillages() {
        return villages;
    }
}
