import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    private ArrayList<Field> fields;
    private int fieldSize;
    final Random random = new Random();

    public GameEngine(){
        fields = new ArrayList<Field>();
    }

    public GameEngine(int fieldSize, int fieldNumber){
        fields =  new ArrayList<Field>();
        setFieldSize(fieldSize);
        for(int i = 0; i < fieldNumber; i++)
            for(int j = 0; j < fieldNumber; j++){
                int x = i * fieldSize;
                int y = j * fieldSize;
                Field newField = new Field(x, y, fieldSize, random);
                newField.fillWithRandomTrees();
                addField(newField);
            }
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void addField(Field field){
        fields.add(field);
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }
}
