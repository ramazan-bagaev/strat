package Foundation;

import Foundation.City;
import Foundation.Element;

import java.util.Random;


public class Field {

    public Ground.GroundType getGroundType() {
        return groundElement.getGroundType();
    }


    public void run() {
        groundElement.run();
        ecosystem.run();
        if (army != null) army.run();
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
    private Element additionalElement;

    private FieldMap map;
    private Time time;

    private Random random;
    private boolean changed;


    public Field(Coord fieldMapPos, Coord globalPos, int size, Random random, FieldMap map, Time time){
        changed = false;
        this.fieldMapPos = fieldMapPos;
        this.time = time;
        setSize(size);
        setMap(map);
        setRandom(random);
        additionalElement = null;
        int randNum = random.nextInt(5);
        Ground.GroundType tempType = Ground.GroundType.Soil;
        if (randNum == 0) tempType = Ground.GroundType.Soil;
        if (randNum == 1) tempType = Ground.GroundType.Sand;
        if (randNum == 2) tempType = Ground.GroundType.Water;
        if (randNum == 3) tempType = Ground.GroundType.Mud;
        if (randNum == 4) tempType = Ground.GroundType.Rock;
        groundElement = new Ground(globalPos.x, globalPos.y, getSize(), tempType, time,this);
        if (getGroundType() != Ground.GroundType.Water) {
            int elType = random.nextInt(3);
            if (elType == 2) city = createCity(random, globalPos);
        }
        ecosystem = new Ecosystem(globalPos, new Coord(size, size), time, this);
    }




    private Tree createTree(Random random, Coord pos){
        if (getGroundType() == Ground.GroundType.Sand && random.nextInt(10) > 8){
            int typeNum = random.nextInt(10);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum < 1) type = Tree.SizeType.Big;
            if (typeNum < 4 && typeNum > 0) type = Tree.SizeType.Middle;
            if (typeNum > 3) type = Tree.SizeType.Small;
            return new Tree(pos.x, pos.y, getSize(), type, time, this);
        }
        if ( getGroundType() == Ground.GroundType.Soil && random.nextInt(10) > 3) {
            int typeNum = random.nextInt(3);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum == 0) type = Tree.SizeType.Big;
            if (typeNum == 1) type = Tree.SizeType.Middle;
            if (typeNum == 2) type = Tree.SizeType.Small;
            return new Tree(pos.x, pos.y, getSize(), type, time, this);
        }
        return null;
    }

    private Rock createRock(Random random, Coord pos){
        if (getGroundType() == Ground.GroundType.Rock) {
            if (random.nextInt(10) > 7) {
                int typeNum = random.nextInt(3);
                Rock.SizeType type = Rock.SizeType.Big;
                if (typeNum == 0) type = Rock.SizeType.Big;
                if (typeNum == 1) type = Rock.SizeType.Middle;
                if (typeNum == 2) type = Rock.SizeType.Small;
                return new Rock(pos.x, pos.y, getSize(), type, time, this);
            }
        }
        return null;
    }

    private City createCity(Random random, Coord pos){
        if (getGroundType() == Ground.GroundType.Rock) return null;
        if (random.nextInt(10) > 8) {
            int typeNum = random.nextInt(10);
            City.SizeType type = City.SizeType.Big;
            if (typeNum == 0) type = City.SizeType.Big;
            if (typeNum == 1 || typeNum == 2) type = City.SizeType.Middle;
            if (typeNum > 2) type = City.SizeType.Small;
            return new City(pos.x, pos.y, getSize(), type, map, time, this);
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
}
