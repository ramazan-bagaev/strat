package Foundation;

import Foundation.Cell.CellMap;
import Foundation.Elements.*;
import Foundation.Army.Army;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;


public class Field {

    private Index fieldMapPos;
    private int size;
    private int cellAmount;
    private double cellSize;
    private int formationUnitSize;

    private City owner;

    private ArrayList<DynamicDrawable> dynamicDrawables;

    private Ground groundElement;
    private Ecosystem ecosystem;
    private City city;
    private ArmyFieldElement armyElement;
    private River river;
    private Tree tree;
    private Manor manor;
    private Village village;
    private Farm farm;
    private Sawmill sawmill;
    private Trawler trawler;
    private Mine mine;
    private Road road;

    private FieldObjects fieldObjects;
    private CellMap cellMap;

    private Time time;

    private FieldMap map;

    private ArrayList<Index> entryPoints;

    private Date timeToIntersect;
    private int height;

    private Random random;

    public Field(Index fieldMapPos, Random random, FieldMap map, Time time){
        this.fieldMapPos = fieldMapPos;
        cellMap = new CellMap(this);
        cellAmount = 100;
        formationUnitSize = 10; // TODO: get rid of constants
        height = 0;
        fieldObjects = new FieldObjects(this);
        size = map.getFieldSize();
        cellSize = (double)(size) / (double)(cellAmount);
        this.map = map;
        this.random = random;
        this.time = time;
        dynamicDrawables = new ArrayList<>();
        entryPoints = new ArrayList<>();
    }

    public Field(Index fieldMapPos, Random random, FieldMap map, Time time, Ground.GroundType type){
        this.fieldMapPos = fieldMapPos;
        cellMap = new CellMap(this);
        cellAmount = 100;
        height = 0;
        formationUnitSize = 10; // TODO: get rid of constants
        fieldObjects = new FieldObjects(this);
        size = map.getFieldSize();
        cellSize = (double)(size) / (double)(cellAmount);
        this.time = time;
        setMap(map);
        setRandom(random);
        dynamicDrawables = new ArrayList<>();
        Ground.GroundType tempType = type;
        groundElement = new Ground(tempType,this);
        ecosystem = new Ecosystem(this);
        entryPoints = new ArrayList<>();
        calculateTimeToIntersect();
    }

    /*public Field(Index fieldMapPos, int pieceSize, Random random, FieldMap map, Time time){
        changed = false;
        this.fieldMapPos = fieldMapPos;
        setSize(pieceSize);
        setMap(map);
        setRandom(random);
        this.time = time;
        dynamicDrawables = new ArrayList<>();
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
    }*/

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

    public ArmyFieldElement getArmyElement(){
        return armyElement;
    }

    public void setArmyElement(ArmyFieldElement armyElement) {
        if (this.armyElement != null) this.armyElement.clear();
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

    public void setGround(Ground ground){
        this.groundElement = ground;
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
        ArmyFieldElement armyElement = new ArmyFieldElement(this, army);
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

    public void setRoad(Road road){
        this.road = road;
    }

    public Road getRoad(){
        return road;
    }

    public ArrayList<DynamicDrawable> getDynamicDrawables() {
        return dynamicDrawables;
    }

    public void removeDynamicDrawable(DynamicDrawable dynamicDrawable){
        dynamicDrawables.remove(dynamicDrawable);
    }

    public void addDynamicDrawable(DynamicDrawable dynamicDrawable){
        dynamicDrawables.add(dynamicDrawable);
    }

    public void drawDynamicDrawable(OpenGLBinder openGLBinder){
        for(DynamicDrawable dynamicDrawable: dynamicDrawables) dynamicDrawable.draw(openGLBinder);
    }

    public Coord getShift(){
        return new Coord(fieldMapPos.x * size, fieldMapPos.y * size);
    }

    public CellMap getCellMap() {
        return cellMap;
    }

    public int getCellAmount() {
        return cellAmount;
    }

    public FieldObjects getFieldObjects() {
        return fieldObjects;
    }

    public int getFormationUnitSize() {
        return formationUnitSize;
    }

    public void setFormationUnitSize(int formationUnitSize) {
        this.formationUnitSize = formationUnitSize;
    }

    public double getCellSize() {
        return cellSize;
    }

    public void addFieldObject(FieldObject fieldObject){
        fieldObjects.addFieldObject(fieldObject);
    }

    public boolean isFree(Index pos, Index size){
        return fieldObjects.isFree(pos, size);
    }

    public boolean isFree(FieldObject fieldObject){
        return fieldObjects.isFree(fieldObject);
    }

    public FieldObject getFieldObject(Index index){
        return fieldObjects.getFieldObject(index);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        if (groundElement != null) groundElement.setShapes();
    }

    public void addEntryPoints(Index point){
        entryPoints.add(point);
    }

    public Index getFirstEntryPoint(){
        if (entryPoints.size() == 0) return null;
        return entryPoints.get(0);
    }
}
