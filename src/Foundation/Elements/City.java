package Foundation.Elements;

import Foundation.*;
import Foundation.Person.Society;
import Foundation.Person.Person;
import Foundation.Runnable.AI.CityActor;
import Foundation.Runnable.RunableElement;
import Generation.NameGenerator;
import Images.CityImage;
import Utils.Index;
import Utils.Coord;
import Utils.Subscription;

import java.util.ArrayList;

public class City extends HabitableElement {

    private String name;

    private int id;

    private CityActor actor;

    private Armies armies;

    private Territory territory;

    private ArrayList<Manor> manors;

    private FieldMap map;

    public City(String name, FieldMap map, Time time, Field parent){
        super(Element.Type.City, time, parent, map);
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
        int population = parent.getRandom().nextInt(100);
        for(int i = 0; i < population; i++){
            Person.Kasta kast = Person.Kasta.Low;
            int kastNum = parent.getRandom().nextInt(3);
            switch (kastNum){
                case 0:
                    kast = Person.Kasta.Low;
                    break;
                case 1:
                    kast = Person.Kasta.Middle;
                    break;
                case 2:
                    kast = Person.Kasta.High;
                    break;
            }
            Person person = new Person(nameGenerator.generate(), null, kast);
            society.addPerson(person);
        }

        Person king = new Person(nameGenerator.generate(), null, Person.Kasta.Royal);
        society.addPerson(king);
        actor = new CityActor(king, this, time);
        parent.getMap().getGameEngine().addRunEntity(actor);
        int size = parent.getSize();
        setBasicShapes(new CityImage(new Coord(), new Coord(size, size), null).getBasicShapesRemoveAndShiftBack());
        Resource food = new Resource(Resource.Type.Food, "beginning supply", society.getAmount() * 80);
        resourceStore.addResource(food);

        armies = new Armies(this);
        this.name = name;
    }

    @Override
    public void run() {
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
}
