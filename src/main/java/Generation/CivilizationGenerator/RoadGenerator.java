package Generation.CivilizationGenerator;

import Foundation.Elements.Ground;
import Foundation.Elements.Road;
import Foundation.FieldMap;
import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.TransportObjects.RoadType;
import Foundation.FieldObjects.TransportObjects.TransportNetNodeObject;
import Foundation.FieldObjects.TransportObjects.TransportNetObject;
import Foundation.TransportInfrostructure.TransportNetElement;
import Foundation.TransportInfrostructure.TransportNetNode;
import Generation.FieldObjectGenerator.RoadFieldObjectsFromPath;
import Utils.FieldObjectPathFinder;
import Utils.Geometry.Index;
import Utils.PathFinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class RoadGenerator{

    private FieldMap fieldMap;
    private PathFinder pathFinder;
    private FieldObjectPathFinder fieldObjectPathFinder;
    private RoadFieldObjectsFromPath roadFieldObjectsFromPath;

    private Random random;

    private Field startField;
    private Field endField;

    private TransportNetNodeObject rimCross;

    public RoadGenerator(FieldMap fieldMap){
        this.fieldMap = fieldMap;
        this.pathFinder = new PathFinder(fieldMap) {
            @Override
            public int getMapRepresentationForField(Field field) {
                if (field.getGroundType() == Ground.GroundType.Water) return 1;
                return 0;
            }
        };
        this.roadFieldObjectsFromPath = new RoadFieldObjectsFromPath();
        this.random = fieldMap.getRandom();
    }

    public void generateRoad(Field startField, Index start, Field endField, Index end){
        if (startField == null || endField == null) return;
        this.startField = startField;
        this.endField = endField;
        LinkedList<Index> path = getMacroPath();
        if (path == null) return;
        Index localStart = start;
        Index localEnd;
        for(int i = 0; i < path.size() - 1; i++){
            localEnd = getLocalEnd(path.get(i).whatDirection(path.get(i + 1)));
            putRoadTo(path.get(i), localStart, localEnd);
            localStart = getLocalStart(localEnd, path.get(i).whatDirection(path.get(i + 1)));
        }
        putRoadTo(path.getLast(), localStart, end);
    }

    private void putRoadTo(Index pos, Index localStart, Index localEnd){
        int cellAmount = startField.getCellAmount();
        Field field = fieldMap.getFieldByIndex(pos);
        ArrayList<Index.Direction> directions = new ArrayList<>();
        if (localStart.x == 0 || localEnd.x == 0) directions.add(Index.Direction.Left);
        if (localStart.x == cellAmount - 1 || localEnd.x == cellAmount - 1) directions.add(Index.Direction.Right);
        if (localStart.y == 0 || localEnd.y == 0) directions.add(Index.Direction.Up);
        if (localStart.y == cellAmount - 1 || localEnd.y == cellAmount - 1) directions.add(Index.Direction.Down);
        field.setRoad(new Road(field, directions));
        generateRoadFieldObject(field, localStart, localEnd);
    }

    private void generateRoadFieldObject(Field field, Index localStart, Index localEnd){
        fieldObjectPathFinder = new FieldObjectPathFinder(field) {
            @Override
            public boolean isFinish(Index pos) {
                return pos.equals(localEnd);
            }
        };
        ArrayList<Index> path = fieldObjectPathFinder.getPath(localStart);
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
        ArrayList<TransportNetObject> transportNetObjects = roadFieldObjectsFromPath.getTransportNetObject(path, field, roadType);
        if (transportNetObjects == null) return;
        FieldObjects fieldObjects = field.getFieldObjects();
        for(TransportNetObject netObject: transportNetObjects){
            fieldObjects.addTransportNetElement(netObject);
        }
        if (rimCross != null){
            TransportNetObject element = transportNetObjects.get(transportNetObjects.size() - 1);
            rimCross.addNetElement(element);
            if (element.isNode()){
                TransportNetNodeObject rimNode = (TransportNetNodeObject)element;
                rimNode.addNetElement(rimCross);
            }
        }
        TransportNetElement rimElement = transportNetObjects.get(0);
        if (rimElement.isNode()){
            rimCross = (TransportNetNodeObject)rimElement;
        }
    }

    private Index getLocalEnd(Index.Direction direction){
        int cellAmount = startField.getCellAmount();
        switch (direction){

            case Up:
                return new Index(random.nextInt(cellAmount), 0);
            case Down:
                return new Index(random.nextInt(cellAmount), cellAmount - 1);
            case Right:
                return new Index(cellAmount - 1, random.nextInt(cellAmount));
            case Left:
                return new Index(0, random.nextInt(cellAmount));
        }
        return null;
    }

    private Index getLocalStart(Index localEnd, Index.Direction dir){
        int cellAmount = startField.getCellAmount();
        switch (dir){
            case Up:
                return new Index(localEnd.x, cellAmount - 1);
            case Down:
                return new Index(localEnd.x, 0);
            case Right:
                return new Index(0, localEnd.y);
            case Left:
                return new Index(cellAmount - 1, localEnd.y);
        }
        return null;
    }


    private LinkedList<Index> getMacroPath(){
        return pathFinder.getPath(startField.getFieldMapPos(), endField.getFieldMapPos());
    }

}
