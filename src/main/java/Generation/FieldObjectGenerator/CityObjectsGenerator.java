package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.CityHouseObject;
import Foundation.FieldObjects.BuildingObject.LivingBuildingObject;
import Foundation.FieldObjects.BuildingObject.PalaceObject;
import Foundation.FieldObjects.BuildingObject.WallObject;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.OccupationPiece;
import Foundation.FieldObjects.TransportObjects.PavementRoadCrossObject;
import Foundation.FieldObjects.TransportObjects.RoadType;
import Foundation.FieldObjects.TransportObjects.TransportNetNodeObject;
import Foundation.FieldObjects.TransportObjects.TransportNetObject;
import Foundation.Person.Person;
import Foundation.Person.Society;
import Generation.NameGenerator;
import Utils.FieldObjectPathFinder;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class CityObjectsGenerator {

    private Field field;
    private FieldObjects fieldObjects;
    private Random random;
    private Index fortressPos;
    private Index fortressSize;

    private Index upGate;
    private Index downGate;
    private Index leftGate;
    private Index rightGate;

    private NameGenerator nameGenerator;
    private FieldObjectPathFinder fieldObjectPathFinder;
    private RoadFieldObjectsFromPath roadFieldObjectsFromPath;

    public void generate(Field field){
        init(field);
        generateWalls();
        generateFirstRoads();
        addPalace();
        addBuildings();
    }

    private void init(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        this.nameGenerator = new NameGenerator(random);
        roadFieldObjectsFromPath = new RoadFieldObjectsFromPath();
    }

    private void generateWalls(){
        ArrayList<OccupationPiece> pieces = fieldObjects.getFreeSpace(new Index(40, 40));
        if (pieces.size() == 0) return;
        OccupationPiece piece = pieces.get(pieces.size() - 1);
        Index delta = new Index(random.nextInt(piece.pieceSize.x/4) + 1, random.nextInt(piece.pieceSize.y/4) + 1);
        fortressSize = piece.pieceSize.minus(delta.multiply(2));
        fortressPos = piece.pos.add(delta);

        upGate = makeTempWall(new Index(fortressPos), false, fortressSize.x);
        leftGate = makeTempWall(fortressPos.add(new Index(0, 1)), true, fortressSize.y - 1);
        rightGate = makeTempWall(fortressPos.add(new Index(fortressSize.x - 1, 1)), true, fortressSize.y - 1);
        downGate = makeTempWall(fortressPos.add(new Index(1, fortressSize.y - 1)), false, fortressSize.x - 2);

        field.addEntryPoints(upGate);
        field.addEntryPoints(leftGate);
        field.addEntryPoints(rightGate);
        field.addEntryPoints(downGate);
    }

    private Index makeTempWall(Index pos, boolean vertical, int size){
        if (vertical) {
            Index gatePos = pos.add(new Index(0, random.nextInt(size - 4) + 2));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(1, gatePos.y - pos.y)));
            fieldObjects.addTransportNetElement(new PavementRoadCrossObject(field, gatePos, new Index(1, 1)));
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(0, 1)), new Index(1, size - (gatePos.y - pos.y + 1))));
            return gatePos;
        }
        else {
            Index gatePos = pos.add(new Index(random.nextInt(size - 4) + 2, 0));
            fieldObjects.addFieldObject(new WallObject(field, pos, new Index(gatePos.x - pos.x, 1)));
            fieldObjects.addTransportNetElement(new PavementRoadCrossObject(field, gatePos, new Index(1, 1)));
            fieldObjects.addFieldObject(new WallObject(field, gatePos.add(new Index(1, 0)), new Index(size - (gatePos.x - pos.x + 1), 1)));
            return gatePos;
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

    private void generateFirstRoads(){
        Index singleSize = new Index(1, 1);
        RoadType roadType = new RoadType() {
            @Override
            public boolean isPavement() {
                return true;
            }

            @Override
            public boolean isPriming() {
                return false;
            }
        };

        fieldObjectPathFinder = new FieldObjectPathFinder(field) {
            @Override
            public boolean isFree(Index index) {
                if (!fieldObjects.isInBoundary(index, singleSize)) return false;
                FieldObject fieldObject = fieldObjects.getFieldObject(index);
                if (fieldObject == null) return true;
                if (fieldObject.isTransportNetObject()) return true;
                return false;
            }

            @Override
            public boolean isFinish(Index pos) {
                return pos.equals(leftGate);
            }

            @Override
            public int getDistance(Index pos) {
                FieldObject fieldObject = fieldObjects.getFieldObject(pos);
                if (fieldObject == null) return 2;
                if (fieldObject.isTransportNetObject()) return 1;
                else return 4;
            }
        };
        ArrayList<Index> path = fieldObjectPathFinder.getPath(rightGate);
        ArrayList<TransportNetObject> netObjects = roadFieldObjectsFromPath.getTransportNetObject(path, field, roadType);
        if (netObjects == null) return;
        for(TransportNetObject transportNetObject: netObjects){
            fieldObjects.addTransportNetElement(transportNetObject);
        }

        fieldObjectPathFinder = new FieldObjectPathFinder(field) {
            @Override
            public boolean isFree(Index index) {
                if (!fieldObjects.isInBoundary(index, singleSize)) return false;
                FieldObject fieldObject = fieldObjects.getFieldObject(index);
                if (fieldObject == null) return true;
                if (fieldObject.isTransportNetObject()) return true;
                return false;
            }

            @Override
            public boolean isFinish(Index pos) {
                return pos.equals(downGate);
            }

            @Override
            public int getDistance(Index pos) {
                FieldObject fieldObject = fieldObjects.getFieldObject(pos);
                if (fieldObject == null) return 2;
                if (fieldObject.isTransportNetObject()) return 1;
                else return 4;
            }
        };

        path = fieldObjectPathFinder.getPath(upGate);
        netObjects = roadFieldObjectsFromPath.getTransportNetObject(path, field, roadType);
        if (netObjects == null) return;
        for(TransportNetObject transportNetObject: netObjects){
            fieldObjects.addTransportNetElement(transportNetObject);
        }

    }


    private void addPalace(){
        Index size = new Index(8, 4);
        Index pos = fieldObjects.getPosForBuilding(size);
        if (pos == null) return;
        PalaceObject palaceObject = new PalaceObject(field, pos);
        fieldObjects.addBuilding(palaceObject);
    }

    private void addBuildings(){
        int populace = field.getCity().getSociety().getAmount();
        ArrayList<Person> people = field.getCity().getSociety().getPeople().getPersonArray();
        int k = 0;
        while(true){
            k++;
            //x = random.nextInt(cellAmount);
            //y = random.nextInt(cellAmount);
            int sizeX = random.nextInt(3) + 2;
            int sizeY = random.nextInt(3) + 2;
            //Index pos = new Index(x, y);
            Index size = new Index(sizeX, sizeY);
            //OccupationPiece piece = field.getFieldObjects().getMinSpace(size);
            Index pos = field.getFieldObjects().getPosForBuilding(size);
            if (pos != null){
                LivingBuildingObject buildingObject = new CityHouseObject(field, pos, size);
                for(int i = 0; i < sizeX * sizeY; i++){
                    populace--;
                    if (populace < 0) break;
                    buildingObject.addPerson(people.get(populace));
                }
                fieldObjects.addBuilding(buildingObject);
            }
            if (populace <= 0) break;
            if (k > 100) break;
        }
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
