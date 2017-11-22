package Foundation;

import Foundation.BasicShape;

public class City extends Element {

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
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

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public enum SizeType{
        Big, Middle, Small
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int x;
    private int y;
    private int size;
    private SizeType sizeType;


    private ResourceStore resourceStore;
    private Production production;

    private int population;

    private FieldMap map;

    public City(int x, int y, int intSize, SizeType sizeType, FieldMap map, Field parent){
        super(Element.Type.City, parent);
        setX(x);
        setY(y);
        setSize(intSize);
        setSizeType(sizeType);
        setMap(map);
        Coord pos;
        Coord size;
        switch (sizeType){
            case Big: {
                setPopulation(10000); // magic constant, TODO: make something with these constants
                pos = new Coord(getX() + getSize()/8, getY() + getSize()/8);
                size = new Coord(getSize()/4, getSize()/4);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Green);
                pos = new Coord(getX() + 5 * getSize()/8, getY() + getSize()/8);
                size = new Coord(getSize()/4, getSize()/4);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.Red);
                pos = new Coord(getX() + getSize()/8, getY() + 5 * getSize()/8);
                size = new Coord(getSize()/4, getSize()/4);
                RectangleShape shape3 = new RectangleShape(pos, size, BasicShape.Color.Blue);
                pos = new Coord(getX() + 5 * getSize()/8, getY() + 5 * getSize()/8);
                size = new Coord(getSize()/4, getSize()/4);
                RectangleShape shape4 = new RectangleShape(pos, size, BasicShape.Color.Yellow);
                addShape(shape1);
                addShape(shape2);
                addShape(shape3);
                addShape(shape4);
                break;
            }
            case Middle: {
                setPopulation(1000);
                pos = new Coord(getX() + getSize()/8, getY() + 3 * getSize()/8);
                size = new Coord(getSize()/4, getSize()/4);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Green);
                pos = new Coord(getX() + 5 * getSize()/8, getY() + 3 * getSize()/8);
                size = new Coord(getSize()/4, getSize()/4);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.Red);
                addShape(shape1);
                addShape(shape2);
                break;
            }
            case Small:{
                setPopulation(100);
                pos = new Coord(getX() + 3 * getSize()/8, getY() + 3 * getSize()/8);
                size = new Coord(getSize()/4, getSize()/4);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Red);
                addShape(shape1);
                break;
            }
        }
        resourceStore = new ResourceStore();

        production = new Production();
        FoodConvertor food = new FoodConvertor(getParent(), this, getPopulation());
        production.addResourceConvertor(food);

    }

    public void run() {
        production.run();
    }

    @Override
    public String getValue(String key){
        String result = super.getValue(key);
        if (!Broadcaster.noResult.equals(result)) return result;
        switch (key){
            case "population":
                return String.valueOf(population);
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
}
