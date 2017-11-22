package Foundation;

import java.util.Random;

public class GameEngine {

    private FieldMap map;
    private int fieldSize;
    final Random random = new Random();

    public GameEngine(){
        map = new FieldMap();
    }

    public GameEngine(int fieldSize, int fieldNumber){
        map =  new FieldMap();
        setFieldSize(fieldSize);
        generateRandomField(fieldNumber);
    }


    public void run(){
        for(Field field: map.getValues()){
            field.run();
        }
    }

    public void generateRandomField(int fieldNumber){
        for(int i = 0; i < fieldNumber; i++)
            for(int j = 0; j < fieldNumber; j++){
                int x = i * fieldSize;
                int y = j * fieldSize;
                Field newField = new Field(x, y, fieldSize, random, map);
                map.addField(new Coord(x, y), newField);
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

    public Field getField(int i, int j) {
        return map.getField(new Coord(i, j));
    }
}
