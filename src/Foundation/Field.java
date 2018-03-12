package Foundation;

import Foundation.City;
import Foundation.Element;

import java.util.Random;


public class Field {

    public Ground.GroundType getGroundType() {
        return groundElement.getGroundType();
    }


    public void run() {
        ecosystem.run();
        //if (additionalElement != null) additionalElement.run();
        if (city != null) city.run();
    }

    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }

    public Ground getGround(){
        return groundElement;
    }

    //public Element getAdditionalElement(){
   //     return additionalElement;
   // }

   // public void setAdditionalElement(Element element){
   //     additionalElement = element;
   // }

    private Coord fieldMapPos;
    private int size;

    private Ground groundElement;
    private Ecosystem ecosystem;
    private City city;
    private Army army;
    private River river;
    private Tree tree;

    private FieldMap map;


    private Date timeToIntersect;

    private Random random;
    private boolean changed;

    public Field(Coord fieldMapPos, Coord globalPos, Random random, FieldMap map, Time time, Ground.GroundType type){
        changed = false;
        this.fieldMapPos = fieldMapPos;
        size = map.getFieldSize();
        setMap(map);
        setRandom(random);
        Ground.GroundType tempType = type;
        groundElement = new Ground(globalPos.x, globalPos.y, getSize(), tempType, time,this, map);
        if (getGroundType() != Ground.GroundType.Water) {
            int elType = random.nextInt(2);
            if (elType == 1) city = createCity(random, globalPos, time);
        }
        ecosystem = new Ecosystem(globalPos, new Coord(size, size), time, this, map);
        calculateTimeToIntersect();
    }

    public Field(Coord fieldMapPos, Coord globalPos, int size, Random random, FieldMap map, Time time){
        changed = false;
        this.fieldMapPos = fieldMapPos;
        setSize(size);
        setMap(map);
        setRandom(random);
        int randNum = random.nextInt(5);
        Ground.GroundType tempType = Ground.GroundType.Soil;
        if (randNum == 0) tempType = Ground.GroundType.Soil;
        if (randNum == 1) tempType = Ground.GroundType.Sand;
        if (randNum == 2) tempType = Ground.GroundType.Water;
        if (randNum == 3) tempType = Ground.GroundType.Mud;
        if (randNum == 4) tempType = Ground.GroundType.Rock;
        groundElement = new Ground(globalPos.x, globalPos.y, getSize(), tempType, time,this, map);
        if (getGroundType() != Ground.GroundType.Water) {
            int elType = random.nextInt(100);
            if (elType == 1) city = createCity(random, globalPos, time);
        }
        ecosystem = new Ecosystem(globalPos, new Coord(size, size), time, this, map);
        calculateTimeToIntersect();
    }

    public void calculateTimeToIntersect(){
        int days = 0;
        switch (getGroundType()){
            case Sand:
                days += 2;
                break;
            case Water:
                days += 5;
                break;
            case Soil:
                days += 1;
                break;
            case Mud:
                days += 3;
                break;
            case Rock:
                days += 4;
                break;
        }
        Climate climate = ecosystem.getClimate();
        int temperature = climate.getTemperature();
        if (temperature == Climate.LOW_TEMPERATURE) days += 2;
        if (temperature == Climate.HIGH_TEMPERATURE) days += 1;
        timeToIntersect = new Date(days);
    }



    private Rock createRock(Random random, Coord pos){
        if (getGroundType() == Ground.GroundType.Rock) {
            if (random.nextInt(10) > 7) {
                int typeNum = random.nextInt(3);
                Rock.SizeType type = Rock.SizeType.Big;
                if (typeNum == 0) type = Rock.SizeType.Big;
                if (typeNum == 1) type = Rock.SizeType.Middle;
                if (typeNum == 2) type = Rock.SizeType.Small;
              //  return new Rock(pos.x, pos.y, getSize(), type, time, this);
            }
        }
        return null;
    }

    private City createCity(Random random, Coord pos, Time time){
        if (getGroundType() == Ground.GroundType.Rock) return null;
        if (random.nextInt(10) > 8) {
            int typeNum = random.nextInt(10);
            City.SizeType type = City.SizeType.Big;
            if (typeNum == 0) type = City.SizeType.Big;
            if (typeNum == 1 || typeNum == 2) type = City.SizeType.Middle;
            if (typeNum > 2) type = City.SizeType.Small;
            String name = "city";
            return new City(pos, getSize(), name, type, map, time, this);
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Coord getFieldMapPos() {
        return fieldMapPos;
    }

    public void setFieldMapPos(Coord fieldMapPos) {
        this.fieldMapPos = fieldMapPos;
    }

    public Ecosystem getEcosystem() {
        return ecosystem;
    }

    public City getCity(){
        return city;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed){
        this.changed = changed;
    }

    public Army getArmy(){
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Date getTimeToIntersect() {
        return timeToIntersect;
    }

    public River getRiver() {
        return river;
    }

    public void setRiver(River river) {
        this.river = river;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
