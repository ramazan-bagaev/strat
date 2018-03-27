package Foundation;

import Foundation.Runnable.Country;
import Foundation.Runnable.RunEntity;
import Generation.FieldMapGenerator;
import Utils.Coord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameEngine {

    private Time time;

    private ArrayList<Country> countries;

    private GameWindowElement gameWindowElement;
    private FieldMap map;
    private int fieldSize;
    final Random random = new Random();

    private ArrayList<RunEntity> runEntities;
    private ArrayList<RunEntity> newRunEntity;

    public GameEngine(GameWindowElement gameWindowElement){
        runEntities = new ArrayList<>();
        newRunEntity = new ArrayList<>();
        countries = new ArrayList<>();
        this.gameWindowElement = gameWindowElement;
        map = new FieldMap(100, 500, this);
        time = new Time();
    }

    public GameEngine(int fieldSize, int fieldNumber, GameWindowElement gameWindowElement){
        runEntities = new ArrayList<>();
        newRunEntity = new ArrayList<>();
        countries = new ArrayList<>();
        this.gameWindowElement = gameWindowElement;
        FieldMapGenerator fieldMapGenerator = new FieldMapGenerator(random);
        map =  fieldMapGenerator.generate(fieldNumber, fieldSize, 2000, this);
        //map = new FieldMap();
        time = new Time();
        setFieldSize(fieldSize);
        runEntities.addAll(newRunEntity);
        //generateRandomField(fieldNumber);
    }


    public void run(){
        time.nextDay();
        runEntities.addAll(newRunEntity);
        newRunEntity.clear();
        Iterator<RunEntity> iterator = runEntities.iterator();
        while (iterator.hasNext()){
            RunEntity runEntity = iterator.next();
            runEntity.run();
        }
        runEntities.addAll(newRunEntity);
        newRunEntity.clear();
        //gameWindowElement.getMainWindow().getGameWindowHelperElement().setShapes();
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

    public Time getTime() {
        return time;
    }

    public void addRunEntity(RunEntity runEntity){
        newRunEntity.add(runEntity);
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
}
