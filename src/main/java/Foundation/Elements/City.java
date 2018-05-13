package Foundation.Elements;

import Foundation.*;
import Foundation.FieldObjects.CityHouseObject;
import Foundation.FieldObjects.LivingBuildingObject;
import Foundation.FieldObjects.MarketObject;
import Foundation.FieldObjects.PalaceObject;
import Foundation.Person.Person;
import Foundation.Recources.Resource;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.AI.StupidAI.StupidCityAI;
import Foundation.Runnable.Actors.CityActor;
import Foundation.Runnable.Country;
import Generation.NameGenerator;
import Images.CityImage;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;
import java.util.Random;

public class City extends HabitableFieldElement {

    private String name;

    private int id;

    private CityActor actor;

    private Armies armies;

    private Territory territory;

    private ArrayList<Manor> manors;

    private FieldMap map;

    private Country country;


    public City(String name, FieldMap map, Time time, Field parent){
        super(FieldElement.Type.City, time, parent, map);
        setMap(map);
        territory = new Territory();
        manors = new ArrayList<>();
        int radius = 3;
        Index index = getParent().getFieldMapPos();
        for(int i = -radius; i <= radius; i++){
            for(int j = -radius; j <= radius; j++){
                Index local = index.add(new Index(j, i));
                Field field = map.getFieldByIndex(local);
                if (field == null) continue;
                if (field.hasOwner()) continue;
                territory.add(local);
                field.setOwner(this);
            }
        }

        NameGenerator nameGenerator = new NameGenerator(parent.getRandom());
        int population = parent.getRandom().nextInt(1000);
        for(int i = 0; i < population; i++){
            Person.Kasta kast;// = Person.Kasta.Low;
            int kastNum = parent.getRandom().nextInt(100);
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

        Person king = new Person(nameGenerator.generate(), null, Person.Kasta.Royal);
        society.addPerson(king);

        actor = new CityActor(king, this);
        AI cityAI = new StupidCityAI(actor, getTime());
        actor.setAi(cityAI);
        parent.getMap().getGameEngine().addRunEntity(cityAI);

        int size = parent.getSize();
        setBasicShapes(new CityImage(new Coord(), new Coord(size, size), null).getBasicShapesRemoveAndShiftBack());
        Resource food = new Resource(Resource.Type.Food, "beginning supply", society.getAmount() * 80);
        resourceStore.addResource(food);

        armies = new Armies(this);
        this.name = name;

        Random random = parent.getRandom();
        ArrayList<Index.Direction> dir = new ArrayList<>();
        for (Index.Direction direction: Index.getAllDirections()) {
            if (random.nextBoolean()) dir.add(direction);
        }
        // Field field = map.getFieldByIndex(parent.getFieldMapPos().add(new Index(random.nextInt(4)-2, random.nextInt(4)-2)));
        Field field = parent;
        if (field != null) {
            Road road = new Road(time, field, map, dir);
            field.setRoad(road);
        }

        fillCity();

    }

    public void fillCity(){
        Field field = getParent();
        Random random = field.getRandom();
        int cellAmount = field.getCellAmount();
        int x = random.nextInt(cellAmount);
        int y = random.nextInt(cellAmount);
        PalaceObject palaceObject = new PalaceObject(getParent(), new Index(x, y));
        field.addFieldObject(palaceObject);

        int populace = society.getAmount();
        int k = 0;
        while(true){
            k++;
            x = random.nextInt(cellAmount);
            y = random.nextInt(cellAmount);
            int sizeX = random.nextInt(3) + 1;
            int sizeY = random.nextInt(3) + 1;
            Index pos = new Index(x, y);
            Index size = new Index(sizeX, sizeY);
            if (field.isFree(pos, size)) {
                populace -= sizeX * sizeY;
                field.addFieldObject(new CityHouseObject(field, pos, size));
            }
            if (populace <= 0) break;
            if (k > 100) break;
        }


        k = 0;
        while(true){
            x = random.nextInt(cellAmount);
            y = random.nextInt(cellAmount);
            Index pos = new Index(x, y);
            if (field.isFree(pos, new Index(4, 4))) {
                field.addFieldObject(new MarketObject(field, pos));
                break;
            }
            if (k > 100) break;
        }

    }

    @Override
    public void run() {
        super.run();
        for (Manor manor : manors) {
            ArrayList<Resource> resources = manor.getPartOfResource(0.3);
            for(Resource resource: resources){
                resourceStore.addResource(resource);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void addTerritory(Index pos){
        territory.add(pos);
        parent.getMap().getFieldByIndex(pos).setOwner(this);
        country.addTerritory(pos);
    }

    public void createManor(Index pos, Person lord){
        if (!territory.contains(pos)) return;
        Field field = map.getFieldByIndex(pos);
        if (field.getManor() != null) return;
        removePerson(lord);
        Manor manor = new Manor(time, field, map, this, lord);
        field.setManor(manor);
        manors.add(manor);
        map.getGameEngine().getGameWindowElement().setShapes();
        map.getGameEngine().addRunEntity(manor);
    }

    /*public void destroy() {
        for(Index pos: territory){
            Field field = map.getFieldByIndex(pos);
            field.setOwner(null);
        }
    }*/


    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Armies getArmies() {
        return armies;
    }

    public Territory getTerritory() {
        return territory;
    }

    public ArrayList<Manor> getManors() {
        return manors;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
