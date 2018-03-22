package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Runnable.RunableElement;
import Generation.NameGenerator;
import Images.CityImage;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;

public class City extends RunableElement {

    private String name;

    private int id;

    private ResourceStore resourceStore;
    private People people;
    private Works works;
    private Armies armies;

    private ArrayList<Index> territory;

    private ArrayList<Manor> manors;

    private FieldMap map;

    public City(String name, FieldMap map, Time time, Field parent){
        super(Element.Type.City, time, parent, map);
        setMap(map);
        territory = new ArrayList<>();
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

        people = new People(parent);

        NameGenerator nameGenerator = new NameGenerator(parent.getRandom());
        int population = parent.getRandom().nextInt(100);
        for(int i = 0; i < population; i++){
            Person person = new Person(nameGenerator.generate(), parent);
            people.addPerson(person);
        }

        int size = parent.getSize();
        setBasicShapes(new CityImage(new Coord(), new Coord(size, size), null).getBasicShapesRemoveAndShiftBack());
        resourceStore = new ResourceStore();
        Resource food = new Resource(Resource.Type.Food, "beginning supply", people.getAmount() * 80);
        resourceStore.addResource(food);

        works = new Works();

        armies = new Armies(this);
        this.name = name;
    }

    @Override
    public void run() {
        resourceStore.run();
        works.run();
    }

    @Override
    public String getValue(String key){
        String result = super.getValue(key);
        if (!Broadcaster.noResult.equals(result)) return result;
        switch (key){
            case "population":
                return String.valueOf(people.getAmount());
        }
        return Broadcaster.noResult;
    }

    public String getName() {
        return name;
    }

    public void addTerritory(Index pos){
        territory.add(pos);
    }

    public void createManor(Index pos){
        if (!territory.contains(pos)) return;
        Field field = map.getFieldByIndex(pos);
        if (field.getManor() != null) return;
        Manor manor = new Manor(time, field, map, this);
        field.setManor(manor);
        manors.add(manor);
        map.getGameEngine().getGameWindowElement().setShapes();
        map.getGameEngine().addRunEntity(manor);
    }

    public void destroy() {
        for(Index pos: territory){
            Field field = map.getFieldByIndex(pos);
            field.setOwner(null);
        }
    }



    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }

    public ResourceStore getResourceStore() {
        return resourceStore;
    }

    public void setResourceStore(ResourceStore resourceStore) {
        this.resourceStore = resourceStore;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Works getWorks() {
        return works;
    }

    public void setWorks(Works works) {
        this.works = works;
    }

    public Armies getArmies() {
        return armies;
    }

    public ArrayList<Index> getTerritory() {
        return territory;
    }

    public ArrayList<Manor> getManors() {
        return manors;
    }

    public People getPeople() {
        return people;
    }
}
