package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.NaturalObjects.StoneObject;
import Foundation.FieldObjects.OccupationPiece;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class MountainRockGeneration{

    private Field field;
    private FieldObjects fieldObjects;
    private Random random;

    public MountainRockGeneration(){

    }

    public void generate(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        generateRocks(5);
    }

    private void generateRocks(int number){
        for(int i = 0; i < number; i++){
            generateOneRock();
        }
    }


    private void generateOneRock(){
        int cellAmount = field.getCellAmount();
        ArrayList<OccupationPiece> pieces = fieldObjects.getFreeSpace(new Index(1, 1));
        OccupationPiece piece = pieces.get(pieces.size() - 1);
        Index newSize = new Index(random.nextInt(piece.pieceSize.x/2 + 1) + piece.pieceSize.x/4,
                random.nextInt(piece.pieceSize.y/2 + 1) + piece.pieceSize.y/4);
        Index newPos = new Index(random.nextInt( piece.pieceSize.x - newSize.x + 1) + piece.pos.x,
                random.nextInt(piece.pieceSize.y - newSize.y + 1) + piece.pos.y);
        fieldObjects.addFieldObject(new StoneObject(field, newPos, newSize));
    }

}
