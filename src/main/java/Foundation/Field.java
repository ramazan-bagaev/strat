package Foundation;

import Foundation.Elements.*;
import Foundation.Runnable.Army;
import Utils.Index;

import java.util.Random;


public class Field {

    private Index fieldMapPos;
    private int size;

    private City owner;

    private Ground groundElement;
    private Ecosystem ecosystem;
    private City city;
    private ArmyElement armyElement;
    private River river;
    private Tree tree;
    private Manor manor;
    private Village village;
    private Farm farm;
    private Sawmill sawmill;
    private Trawler trawler;
    private Mine mine;


    private Time time;

    private FieldMap map;


    private Date timeToIntersect;

    private Random random;
    private boolean changed;

    public Field(Index fieldMapPos, Random random, FieldMap map, Time time, Ground.GroundType type){
        changed = false;
        this.fieldMapPos = fieldMapPos;
        size = map.getFieldSize();
        setMap(map);
        setRandom(random);
        Ground.GroundType tempType = type;
        groundElement = new Ground(tempType, time,this, map);
        ecosystem = new Ecosystem(time, this, map);
        calculateTimeToIntersect();
    }

    public Field(Index fieldMapPos, int size, Random random, FieldMap map, Time time){
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
        groundElement = new Ground(tempType, time,this, map);
        ecosystem = new Ecosystem(time, this, map);
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

    public Index getFieldMapPos() {
        return fieldMapPos;
    }

    public Ecosystem getEcosystem() {
        return ecosystem;
    }

    public City getCity(){
        return city;
    }

    public ArmyElement getArmyElement(){
        return armyElement;
    }

    public void setArmyElement(ArmyElement armyElement) {
        this.armyElement = armyElement;
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

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
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

    public void createAndAddArmy(Army army){
        ArmyElement armyElement = new ArmyElement(time, this, map, army);
        setArmyElement(armyElement);
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public void removerArmy(){
        armyElement = null;
        map.getGameEngine().getGameWindowElement().setShapes();
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public void setSawmill(Sawmill sawmill) {
        this.sawmill = sawmill;
    }

    public Sawmill getSawmill() {
        return sawmill;
    }

    public Trawler getTrawler() {
        return trawler;
    }

    public void setTrawler(Trawler trawler) {
        this.trawler = trawler;
    }

    public Mine getMine() {
        return mine;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }
}
