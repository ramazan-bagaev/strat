package Foundation;

import Generation.FieldMapGenerator;

import java.util.Random;

public class GameEngine {

    private Time time;

    private FieldMap map;
    private int fieldSize;
    final Random random = new Random();

    public GameEngine(GameWindowElement gameWindowElement){
        map = new FieldMap(10000, 100, gameWindowElement);
        time = new Time();
    }

    public GameEngine(int fieldSize, int fieldNumber, GameWindowElement gameWindowElement){
        FieldMapGenerator fieldMapGenerator = new FieldMapGenerator();
        map =  fieldMapGenerator.generate(fieldNumber, fieldSize, 1000, gameWindowElement);
        //map = new FieldMap();
        time = new Time();
        setFieldSize(fieldSize);
        //generateRandomField(fieldNumber);
    }


    public void run(){
        time.nextDay();
       // for(Field field: map.getValues()){
       //     field.run();
       // }
    }

    public void generateRandomField(int fieldNumber){
        for(int i = 0; i < fieldNumber; i++)
            for(int j = 0; j < fieldNumber; j++){
                int x = i * fieldSize;
                int y = j * fieldSize;
                Field newField = new Field(new Coord(i, j), new Coord(x, y), fieldSize, random, map, time);
                map.addField(new Coord(i, j), newField);
            }
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

}
