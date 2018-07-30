package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.PalaceObject;
import Foundation.FieldObjects.BuildingObject.WallObject;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.OccupationPiece;
import Foundation.FieldObjects.TransportObjects.PavementRoadCrossObject;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Generation.NameGenerator;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class CityObjectsGenerator {

    private Field field;
    private FieldObjects fieldObjects;
    private Random random;
    private Index fortressPos;
    private Index fortressSize;

    private NameGenerator nameGenerator;

    public void generate(Field field){
        init(field);
        generateWalls();
        addPalace();
    }

    private void init(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        this.nameGenerator = new NameGenerator(random);
    }

    private void generateWalls(){
        ArrayList<OccupationPiece> pieces = fieldObjects.getFreeSpace(new Index(40, 40));
        if (pieces.size() == 0) return;
        OccupationPiece piece = pieces.get(pieces.size() - 1);
        Index delta = new Index(random.nextInt(piece.pieceSize.x/4) + 1, random.nextInt(piece.pieceSize.y/4) + 1);
        fortressSize = piece.pieceSize.minus(delta.multiply(2));
        fortressPos = piece.pos.add(delta);

        makeTempWall(new Index(fortressPos), false, fortressSize.x);
        makeTempWall(fortressPos.add(new Index(0, 1)), true, fortressSize.y - 1);
        makeTempWall(fortressPos.add(new Index(fortressSize.x - 1, 1)), true, fortressSize.y - 1);
        makeTempWall(fortressPos.add(new Index(1, fortressSize.y - 1)), false, fortressSize.x - 2);
    }

    private void makeTempWall(Index pos, boolean vertical, int size){
        if (vertical) {
            Index gatePos = pos.add(new Index(0, random.nextInt(size - 4) + 2));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(1, gatePos.y - pos.y)));
            fieldObjects.addTransportNetElement(new PavementRoadCrossObject(field, gatePos, new Index(1, 1)));
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(0, 1)), new Index(1, size - (gatePos.y - pos.y + 1))));
        }
        else {
            Index gatePos = pos.add(new Index(random.nextInt(size - 4) + 2, 0));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(gatePos.x - pos.x, 1)));
            fieldObjects.addTransportNetElement(new PavementRoadCrossObject(field, gatePos, new Index(1, 1)));
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(1, 0)), new Index(size - (gatePos.x - pos.x + 1), 1)));
        }
    }

    private void makeWall(Index pos, boolean vertical, int size){
        if (vertical) {
            Index gatePos = pos.add(new Index(0, random.nextInt(size - 4) + 2));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(1, gatePos.y - pos.y)));
            fieldObjects.addGateObject(gatePos, vertical);
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(0, 1)), new Index(1, size - (gatePos.y - pos.y + 1))));
        }
        else {
            Index gatePos = pos.add(new Index(random.nextInt(size - 4) + 2, 0));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(gatePos.x - pos.x, 1)));
            fieldObjects.addGateObject(gatePos, vertical);
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(1, 0)), new Index(size - (gatePos.x - pos.x + 1), 1)));
        }
    }


    private void initTransportNetElement(){
        int cellAmount = field.getCellAmount();
        Index roadStart = new Index(random.nextInt(cellAmount), random.nextInt(cellAmount));
        Index roadEnd = new Index(random.nextInt(cellAmount), random.nextInt(cellAmount));
    }

    private void addPalace(){
        Index pos = fortressPos.add(new Index(random.nextInt(fortressSize.x - 8 - 4) + 2, random.nextInt(fortressSize.y - 4 - 4) + 2));
        PalaceObject palaceObject = new PalaceObject(field, pos);
        fieldObjects.addBuilding(palaceObject);
    }

    private Index getRandomPosFromPerimeter(Index pos, Index size, Index.Direction direction) {
        switch (direction) {
            case Up:
                return pos.add(new Index(random.nextInt(size.x - 4) + 2, 0));
            case Down:
                return pos.add(new Index(random.nextInt(size.x - 4) + 2, size.y - 1));
            case Right:
                return pos.add(new Index(size.x - 1, random.nextInt(size.y - 4) + 2));
            case Left:
                return pos.add(new Index(0, random.nextInt(size.y - 4) + 2));
        }
        return null;
    }
}
