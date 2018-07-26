package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.NaturalObjects.ForestObject;
import Foundation.FieldObjects.OccupationPiece;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class ForestObjectGenerator {

    private Field field;
    private FieldObjects fieldObjects;
    private Random random;

    public void generate(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        generateForest(4);
    }

    private void generateForest(int number){
        for(int i = 0; i < number; i++){
            generateForestPart();
        }
    }


    private void generateForestPart(){
        ArrayList<OccupationPiece> pieces = fieldObjects.getFreeSpace(new Index(1, 1));
        if (pieces.size() == 0) return;
        OccupationPiece piece = pieces.get(pieces.size() - 1);
        Index newSize = new Index(random.nextInt(piece.pieceSize.x) + 1,
                random.nextInt(piece.pieceSize.y) + 1);
        Index newPos = new Index(random.nextInt( piece.pieceSize.x - newSize.x + 1) + piece.pos.x,
                random.nextInt(piece.pieceSize.y - newSize.y + 1) + piece.pos.y);
        fieldObjects.addFieldObject(new ForestObject(field, newPos, newSize));
    }

}
