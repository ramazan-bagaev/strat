package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.WallObject;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.OccupationPiece;
import Foundation.FieldObjects.TransportObjects.PavementRoadCrossObject;
import Foundation.FieldObjects.TransportObjects.PrimingRoadCrossObject;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class ManorObjectsGenerator {

    private Field field;
    private FieldObjects fieldObjects;

    private Random random;

    private Index upGate;
    private Index leftGate;
    private Index rightGate;
    private Index downGate;

    public void generate(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        generateWall();
    }

    private void generateWall(){
        ArrayList<OccupationPiece> pieces = fieldObjects.getFreeSpace(new Index(20, 20));
        if (pieces.size() == 0) return;
        OccupationPiece piece = pieces.get(pieces.size() - 1);
        Index delta = new Index(random.nextInt(piece.pieceSize.x/8) + piece.pieceSize.x/4,
                random.nextInt(piece.pieceSize.y/8) + piece.pieceSize.y/4);
        Index fortressSize = piece.pieceSize.minus(delta.multiply(2));
        Index fortressPos = piece.pos.add(delta);

        upGate = makeTempWall(new Index(fortressPos), false, fortressSize.x);
        leftGate = makeTempWall(fortressPos.add(new Index(0, 1)), true, fortressSize.y - 1);
        rightGate = makeTempWall(fortressPos.add(new Index(fortressSize.x - 1, 1)), true, fortressSize.y - 1);
        downGate = makeTempWall(fortressPos.add(new Index(1, fortressSize.y - 1)), false, fortressSize.x - 2);
    }

    private Index makeTempWall(Index pos, boolean vertical, int size){
        if (vertical) {
            Index gatePos = pos.add(new Index(0, random.nextInt(size - 4) + 2));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(1, gatePos.y - pos.y)));
            fieldObjects.addTransportNetElement(new PrimingRoadCrossObject(field, gatePos, new Index(1, 1)));
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(0, 1)), new Index(1, size - (gatePos.y - pos.y + 1))));
            return gatePos;
        }
        else {
            Index gatePos = pos.add(new Index(random.nextInt(size - 4) + 2, 0));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(gatePos.x - pos.x, 1)));
            fieldObjects.addTransportNetElement(new PrimingRoadCrossObject(field, gatePos, new Index(1, 1)));
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(1, 0)), new Index(size - (gatePos.x - pos.x + 1), 1)));
            return gatePos;
        }
    }
}
