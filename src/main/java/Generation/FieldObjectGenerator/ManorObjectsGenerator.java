package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.CityHouseObject;
import Foundation.FieldObjects.BuildingObject.LivingBuildingObject;
import Foundation.FieldObjects.BuildingObject.ManorObject;
import Foundation.FieldObjects.BuildingObject.WallObject;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.OccupationPiece;
import Foundation.FieldObjects.TransportObjects.PavementRoadCrossObject;
import Foundation.FieldObjects.TransportObjects.PrimingRoadCrossObject;
import Foundation.FieldObjects.TransportObjects.RoadType;
import Foundation.FieldObjects.TransportObjects.TransportNetObject;
import Foundation.Person.Person;
import Utils.FieldObjectPathFinder;
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

    private Index fortressPos;
    private Index fortressSize;

    private FieldObjectPathFinder fieldObjectPathFinder;
    private RoadFieldObjectsFromPath roadFieldObjectsFromPath;

    public void generate(Field field){
        this.field = field;
        this.fieldObjects = field.getFieldObjects();
        this.random = field.getRandom();
        this.roadFieldObjectsFromPath = new RoadFieldObjectsFromPath();
        generateWall();
        generateFirstRoads();
        generateManorObject();
        generateBuildings();
    }

    private void generateWall(){
        ArrayList<OccupationPiece> pieces = fieldObjects.getFreeSpace(new Index(20, 20));
        if (pieces.size() == 0) return;
        OccupationPiece piece = pieces.get(pieces.size() - 1);
        Index delta = new Index(random.nextInt(piece.pieceSize.x/8) + piece.pieceSize.x/4,
                random.nextInt(piece.pieceSize.y/8) + piece.pieceSize.y/4);
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

    private void generateFirstRoads(){
        Index singleSize = new Index(1, 1);
        RoadType roadType = new RoadType() {
            @Override
            public boolean isPavement() {
                return false;
            }

            @Override
            public boolean isPriming() {
                return true;
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
        for(TransportNetObject transportNetObject: netObjects){
            fieldObjects.addTransportNetElement(transportNetObject);
        }

    }

    private void generateManorObject(){
        Index size = new Index(6, 6);
        Index pos = fieldObjects.getPosForBuilding(size);
        ManorObject manorObject = new ManorObject(field, pos);
        field.addFieldObject(manorObject);
    }

    private void generateBuildings(){
        int populace = field.getManor().getSociety().getAmount();
        ArrayList<Person> people = field.getManor().getSociety().getPeople().getPersonArray();
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


}
