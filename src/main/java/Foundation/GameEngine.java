package Foundation;

import Foundation.Army.Battle;
import Foundation.FieldObjects.FieldObject;
import Foundation.Runnable.Country;
import Foundation.Runnable.RunEntity;
import Generation.CompleteGenerator;
import Generation.FieldMapGenerator;
import Generation.TerrainGenerator.TerrainGenerator;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameEngine {

    private Time time;
    private int incr;

    private ArrayList<Country> countries;

    private GameWindowElement gameWindowElement;
    private FieldMap map;
    private int fieldSize;
    final Random random = new Random();

    private ArrayList<RunEntity> runEntities;
    private ArrayList<RunEntity> newRunEntity;
    private ArrayList<RunEntity> onDeleteEntity;

    private ArrayList<Battle> battles;

    public GameEngine(GameWindowElement gameWindowElement){
        runEntities = new ArrayList<>();
        newRunEntity = new ArrayList<>();
        onDeleteEntity = new ArrayList<>();
        countries = new ArrayList<>();
        battles = new ArrayList<>();
        this.gameWindowElement = gameWindowElement;
        time = new Time();
        map = new FieldMap(100, 500, this);
        incr = 0;
    }

    public GameEngine(int fieldSize, int fieldNumber, GameWindowElement gameWindowElement){
        runEntities = new ArrayList<>();
        newRunEntity = new ArrayList<>();
        countries = new ArrayList<>();
        onDeleteEntity = new ArrayList<>();
        this.gameWindowElement = gameWindowElement;
        time = new Time();

        FieldMapGenerator generator = new CompleteGenerator();
        map = new FieldMap(2000, fieldSize, this);
        generator.generate(map, new Index(fieldNumber, fieldNumber));

        setFieldSize(fieldSize);
        runEntities.addAll(newRunEntity);
        incr = 0;
    }


    public void run(){
        incr++;
        if (incr >= 30){
            time.nextDay();
            incr = 0;
        }
        Iterator<RunEntity> iterator = runEntities.iterator();
        while (iterator.hasNext()){
            RunEntity runEntity = iterator.next();
            runEntity.run();
        }

        runEntities.removeAll(onDeleteEntity);
        onDeleteEntity.clear();


        runEntities.addAll(newRunEntity);
        newRunEntity.clear();
        gameWindowElement.getMainWindow().getGameWindowHelperElement().setShapes();
        //gameWindowElement.getMainWindow().getGameWindowHelperElement().renewState();
        //gameWindowElement.setShapes();
       // for(Field field: map.getValues()){
       //     field.run();
       // }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }

    public Field getFieldByPos(Coord pos) {
        return map.getFieldByPos(pos);
    }

    public FieldObject getFieldObjectByPos(Coord pos){
        return map.getFieldObjectByPos(pos);
    }

    public Time getTime() {
        return time;
    }

    public void addRunEntity(RunEntity runEntity){
        newRunEntity.add(runEntity);
    }

    public void removeRunEntity(RunEntity runEntity){
        onDeleteEntity.add(runEntity);
    }

    public GameWindowElement getGameWindowElement() {
        return gameWindowElement;
    }

    public void setCountries(ArrayList<Country> countries){
        this.countries = countries;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void addBattle(Battle battle){
        battles.add(battle);
        addRunEntity(battle);
    }

    public void removeBattle(Battle battle){
        battles.remove(battle);
        removeRunEntity(battle);
    }
}
