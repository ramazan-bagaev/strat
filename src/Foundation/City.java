package Foundation;

import Images.CityImage;

public class City extends Element {

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

    public enum SizeType{
        Big, Middle, Small
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    private FieldMap map;

    public City(Coord pos, int intSize, String name, SizeType sizeType, FieldMap map, Time time, Field parent){
        super(Element.Type.City, time, parent, map);
        this.pos = new Coord(pos);
        setSize(intSize);
        setSizeType(sizeType);
        setMap(map);
        setBasicShapes(new CityImage(pos, new Coord(intSize, intSize), sizeType, null).getBasicShapes());
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
}
