package Foundation;

import Generation.FieldMapGenerator;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    private Time time;

    private FieldMap map;
    private int fieldSize;
    final Random random = new Random();

    private ArrayList<RunEntity> runEntities;

    public GameEngine(GameWindowElement gameWindowElement){
        map = new FieldMap(10000, 100, gameWindowElement);
        time = new Time();
        runEntities = new ArrayList<>();
    }

    public GameEngine(int fieldSize, int fieldNumber, GameWindowElement gameWindowElement){
        runEntities = new ArrayList<>();
        FieldMapGenerator fieldMapGenerator = new FieldMapGenerator();
        map =  fieldMapGenerator.generate(fieldNumber, fieldSize, 1000, gameWindowElement);
        //map = new FieldMap();
        time = new Time();
        setFieldSize(fieldSize);
        //generateRandomField(fieldNumber);
    }


    public void run(){
        time.nextDay();
        for(RunEntity runEntity: runEntities){
            runEntity.run();
        }
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
        runEntities.add(runEntity);
    }

}
