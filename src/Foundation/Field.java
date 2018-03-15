package Foundation;

import Foundation.Elements.*;

import java.util.Random;


public class Field {

    private Coord fieldMapPos;
    private int size;

    private City owner;

    private Ground groundElement;
    private Ecosystem ecosystem;
    private City city;
    private Army army;
    private River river;
    private Tree tree;
    private Manor manor;
    private Farm farm;

    private Time time;

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
        ecosystem = new Ecosystem(globalPos, new Coord(size, size), time, this, map);
        calculateTimeToIntersect();
    }

    public Field(Coord fieldMapPos, Coord globalPos, int size, Random random, FieldMap map, Time time){
        changed = false;
        this.fieldMapPos = fieldMapPos;
        setSize(size);
        setMap(map);
        setRandom(random);
        this.time = time;
        int randNum = random.nextInt(5);
        Ground.GroundType tempType = Ground.GroundType.Soil;
        if (randNum == 0) tempType = Ground.GroundType.Soil;
        if (randNum == 1) tempType = Ground.GroundType.Sand;
        if (randNum == 2) tempType = Ground.GroundType.Water;
        if (randNum == 3) tempType = Ground.GroundType.Mud;
        if (randNum == 4) tempType = Ground.GroundType.Rock;
        groundElement = new Ground(globalPos.x, globalPos.y, getSize(), tempType, time,this, map);
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

    public Time getTime() {
        return time;
    }

    public Manor getManor() {
        return manor;
    }

    public void setManor(Manor manor) {
        this.manor = manor;
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

    public Ground.GroundType getGroundType() {
        return groundElement.getGroundType();
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public City getOwner() {
        return owner;
    }

    public void setOwner(City owner) {
        this.owner = owner;
    }

    public boolean hasOwner(){
        if (owner == null) return false;
        return true;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
