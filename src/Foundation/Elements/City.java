package Foundation.Elements;

import Foundation.*;
import Images.CityImage;

import java.util.ArrayList;

public class City extends Element {

    public enum SizeType{
        Big, Middle, Small
    }

    private Coord pos;
    private int size;
    private SizeType sizeType;
    private String name;

    private int id;

    private ResourceStore resourceStore;
    private Population population;
    private Works works;
    private Armies armies;

    private ArrayList<Coord> territory;

    public ArrayList<Manor> getManors() {
        return manors;
    }

    private ArrayList<Manor> manors;

    private FieldMap map;

    public City(String name, SizeType sizeType, FieldMap map, Time time, Field parent){
        super(Element.Type.City, time, parent, map);
        this.pos = new Coord(parent.getFieldMapPos());
        setSize(map.getFieldSize());
        pos.x = pos.x * size;
        pos.y = pos.y * size;
        setSizeType(sizeType);
        setMap(map);
        territory = new ArrayList<>();
        manors = new ArrayList<>();
        int radius = 3;
        Coord index = getParent().getFieldMapPos();
        for(int i = -radius; i <= radius; i++){
            for(int j = -radius; j <= radius; j++){
                Coord local = index.add(new Coord(j, i));
                Field field = map.getFieldByIndex(local);
                if (field == null) continue;
                if (field.hasOwner()) continue;
                territory.add(local);
                field.setOwner(this);
            }
        }
        setBasicShapes(new CityImage(pos, new Coord(size, size), sizeType, null).getBasicShapes());
        switch (sizeType){
            case Big: {
                setPopulation(new Population(this,10000)); // magic constant, eee TODO: make something with these constants
            }
            case Middle: {
                setPopulation(new Population(this, 1000));
            }
            case Small:{
                setPopulation(new Population(this, 400));
            }
        }
        resourceStore = new ResourceStore();
        Resource food = new Resource(Resource.Type.Food, "beginning supply", population.overAllAmount() * 80);
        resourceStore.addResource(food);

        works = new Works();

        armies = new Armies(this);
        this.name = name;
    }

    public void run() {
        resourceStore.run();
        population.run();
        works.run();
    }

    @Override
    public String getValue(String key){
        String result = super.getValue(key);
        if (!Broadcaster.noResult.equals(result)) return result;
        switch (key){
            case "population":
                return String.valueOf(population.overAllAmount());
            case "sizeType":
                switch (sizeType){
                    case Big:
                        return "big";
                    case Middle:
                        return "middle";
                    case Small:
                        return "small";
                }
        }
        return Broadcaster.noResult;
    }

    public Coord getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public void addTerritory(Coord pos){
        territory.add(pos);
    }

    public void createManor(Coord pos){
        if (!territory.contains(pos)) return;
        Field field = map.getFieldByIndex(pos);
        if (field.getManor() != null) return;
        Manor manor = new Manor(time, field, map, this);
        field.setManor(manor);
        manors.add(manor);
        map.getGameWindowElement().setShapes();
    }

    public void destroy() {
        for(Coord pos: territory){
            Field field = map.getFieldByIndex(pos);
            field.setOwner(null);
        }
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
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

    public ArrayList<Coord> getTerritory() {
        return territory;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
