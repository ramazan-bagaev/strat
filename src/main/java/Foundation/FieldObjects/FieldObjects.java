package Foundation.FieldObjects;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.BuildingObject;
import Foundation.FieldObjects.BuildingObject.GateObject;
import Foundation.FieldObjects.TransportObjects.*;
import Utils.Boundary.RectangleBoundary;
import Utils.Geometry.Index;
import Utils.Geometry.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FieldObjects {

    private Field parent;
    private ArrayList<FieldObject> fieldObjects;
    private ArrayList<OccupationPiece> notOccupiedPieces;

    public FieldObjects(Field parent){
        this.parent = parent;
        fieldObjects = new ArrayList<>();
        notOccupiedPieces = new ArrayList<>();
        notOccupiedPieces.add(new OccupationPiece(new Index(0, 0), new Index(parent.getCellAmount(), parent.getCellAmount()), parent));
    }

    public ArrayList<OccupationPiece> getFreeSpace(Index size){
        ArrayList<OccupationPiece> result = new ArrayList<>();
        for(OccupationPiece piece: notOccupiedPieces){
            if (piece.pieceSize.x >= size.x && piece.pieceSize.y >= size.y) result.add(piece);
        }
        Comparator<OccupationPiece> sizeCmp = new Comparator<OccupationPiece>() {

            @Override
            public int compare(OccupationPiece p1, OccupationPiece p2) {
                return p1.size.x * p1.size.y - p2.size.x * p2.size.y;
            }
        };
        Collections.sort(result, sizeCmp);
        return result;
    }

    public Index getPosForBuilding(Index size){
        Index finalRes = null;
        ArrayList<OccupationPiece> freePieces = getFreeSpace(size);
        for(OccupationPiece piece: freePieces){
            ArrayList<TransportNetObject> netObjects = piece.getTransportNetObject();
            for(TransportNetObject netObject: netObjects){
                if (netObject.isEdge()){
                    if (((TransportNetEdgeObject)netObject).getLength() < 5) continue;
                }
                Index res = getNetObjectNeighborPosForBuilding(piece, size, netObject);
                if (res != null){
                    finalRes = res;
                    return res;
                    //if (parent.getRandom().nextInt(100) > 35) return finalRes;
                }
            }
        }
        return finalRes;
    }

    public OccupationPiece getMinSpace(Index size){
        int square = parent.getCellAmount() * parent.getCellAmount() + 1;
        OccupationPiece minPiece = null;
        for(OccupationPiece piece: notOccupiedPieces){
            if (piece.pieceSize.x >= size.x && piece.pieceSize.y >= size.y){
                int newSquare = piece.pieceSize.x * piece.pieceSize.y;
                if (newSquare < square){
                    minPiece = piece;
                    square = newSquare;
                }
            }
        }
        return minPiece;
    }

    public OccupationPiece getSpace(Index pos, Index size){
        for(OccupationPiece piece: notOccupiedPieces){
            if(piece.contains(pos, size)) return piece;
        }
        return null;
    }

    public OccupationPiece getSpace(Index pos){
        for(OccupationPiece piece: notOccupiedPieces){
            if (piece.contains(pos)) return piece;
        }
        return null;
    }

    public ArrayList<OccupationPiece> getIntersectedPieces(FieldObject fieldObject){
        ArrayList<OccupationPiece> pieces = new ArrayList<>();
        for(OccupationPiece piece: notOccupiedPieces){
            if (piece.isIntersects(fieldObject)) pieces.add(piece);
        }
        return pieces;
    }

    public void splitPiece(OccupationPiece piece, Index pos, Index size){
        notOccupiedPieces.remove(piece);
        int sizeX1 = pos.x - piece.pos.x;
        int sizeY1 = pos.y - piece.pos.y;
        int sizeX2 = piece.pos.x + piece.pieceSize.x - pos.x - size.x;
        int sizeY2 = piece.pos.y + piece.pieceSize.y - pos.y - size.y;
        if (sizeX1 != 0) addNotOccupiedPiece(new OccupationPiece(new Index(piece.pos.x, pos.y), new Index(sizeX1, size.y), parent));
        if (sizeY1 != 0) addNotOccupiedPiece(new OccupationPiece(new Index(pos.x, piece.pos.y), new Index(size.x, sizeY1), parent));
        if (sizeX1 != 0 && sizeY1 != 0) addNotOccupiedPiece(new OccupationPiece(new Index(piece.pos), new Index(sizeX1, sizeY1), parent));
        if (sizeX2 != 0) addNotOccupiedPiece(new OccupationPiece(new Index(pos.x + size.x, pos.y), new Index(sizeX2, size.y), parent));
        if (sizeY2 != 0) addNotOccupiedPiece(new OccupationPiece(new Index(pos.x, pos.y + size.y), new Index(size.x, sizeY2), parent));
        if (sizeX2 != 0 && sizeY2 != 0) addNotOccupiedPiece(new OccupationPiece(pos.add(size), new Index(sizeX2, sizeY2), parent));
        if (sizeX1 != 0 && sizeY2 != 0) addNotOccupiedPiece(new OccupationPiece(new Index(piece.pos.x, pos.y + size.y),
                new Index(sizeX1, sizeY2), parent));
        if (sizeX2 != 0 && sizeY1 != 0) addNotOccupiedPiece(new OccupationPiece(new Index(pos.x + size.x, piece.pos.y),
                new Index(sizeX2, sizeY1), parent));
    }

    public boolean isInBoundary(Index pos, Index size){
        int cellAmount = parent.getCellAmount();
        if (!(pos.x >= 0 && pos.x + size.x <= cellAmount && pos.y >= 0 && pos.y + size.y <= cellAmount)) return false;
        return true;
    }

    public boolean isFree(Index pos, Index size){
        if (!isInBoundary(pos, size)) return false;
       // System.out.println("in boundaries");
        for(FieldObject object: fieldObjects){
            if (object.isIntersects(pos, size)){
                //System.out.println("intersects object :(");
                //System.out.println("pos " + object.cellPos.x + ", " + object.cellPos.y);
                //System.out.println("size " + object.size.x + ", " + object.size.y);
                return false;
            }
        }
       // System.out.println("is free return true");
        return true;
    }

    public boolean isFree(FieldObject fieldObject){
        return isFree(fieldObject.cellPos, fieldObject.size);
    }

    public FieldObject getFieldObject(Index index){
        for(FieldObject fieldObject: fieldObjects){
            if (fieldObject.contains(index)) return fieldObject;
        }
        return null;
    }

    public void addFieldObject(FieldObject fieldObject){
        //System.out.println("add field object");
        if (!isFree(fieldObject)){
            return;
        }
        ArrayList<OccupationPiece> pieces = getIntersectedPieces(fieldObject);
        for(OccupationPiece piece: pieces){
            RectangleBoundary rect = piece.intersect(fieldObject);
            splitPiece(piece, rect.getPos(), rect.getSize());
        }
        fieldObjects.add(fieldObject);
    }

    public void addNotOccupiedPiece(OccupationPiece piece){
        for(OccupationPiece ocPiece: notOccupiedPieces){
           if (ocPiece.tryUnite(piece)){
               ocPiece.setShapes();
               return;
           }
        }
        for(FieldObject object: fieldObjects){
            if (object.isTransportNetObject()){
                TransportNetObject netObject = (TransportNetObject)object;
                piece.addTransportNetObject(netObject);
            }
        }
        notOccupiedPieces.add(piece);
    }

    public void addBuilding(BuildingObject building){
        if (!isFree(building)) return;
        addFieldObject(building);
        linkBuildingWithTransportNet(building);
        //addRoadAroundBuilding(building);
        if (parent.getRandom().nextBoolean()) prolongTransportSystem();

    }

    /*public void addRoadAroundBuilding(BuildingObject building){
        Index pos = building.cellPos;
        Index size = building.size;
        TransportNetEdgeObject road = new PavementRoadObject(parent, pos.add(new Index(-1, 0)), new Index(1, size.y), true);
        addTransportNetElement(road);
        PavementRoadCrossObject cross = new PavementRoadCrossObject(parent, pos.add(new Index(-1, -1)), new Index(1, 1));
        addTransportNetElement(cross);
        road = new PavementRoadObject(parent, pos.add(new Index(0, -1)), new Index(size.x, 1), false);
        addTransportNetElement(road);
        cross = new PavementRoadCrossObject(parent, pos.add(new Index(size.x, -1)), new Index(1, 1));
        addTransportNetElement(cross);
        road = new PavementRoadObject(parent, pos.add(new Index(size.x, 0)), new Index(1, size.y), true);
        addTransportNetElement(road);
        cross = new PavementRoadCrossObject(parent, pos.add(new Index(size.x, size.y)), new Index(1, 1));
        addTransportNetElement(cross);
        road = new PavementRoadObject(parent, pos.add(new Index(0, size.y)), new Index(size.x, 1), false);
        addTransportNetElement(road);
        cross = new PavementRoadCrossObject(parent, pos.add(new Index(-1, size.y)), new Index(1, 1));
        addTransportNetElement(cross);
    }*/

    public void linkBuildingWithTransportNet(BuildingObject buildingObject){
        boolean wasConnected = false;
        for(FieldObject object: fieldObjects){
            if (!object.isNeighbour(buildingObject)) continue;
            if (object.isTransportNetObject()) {
                TransportNetObject netObject = (TransportNetObject)object;
                buildingObject.addTransportNetObject(netObject);
                if (netObject.isNode()){
                    TransportNetNodeObject cross = (TransportNetNodeObject) netObject;
                    cross.addLinkedBuilding(buildingObject);
                    buildingObject.setTransportNetEntranceNode(cross);
                    wasConnected = true;
                }
            }
        }
        if (wasConnected) return;
        TransportNetEdgeObject lastRoad = null;
        for(TransportNetObject netObject: buildingObject.getTransportNetObjects()){
            if (netObject.isEdge()){
                TransportNetEdgeObject road = (TransportNetEdgeObject) netObject;
                int length = road.getLength();
                if (length >= 5){
                    Interval intersect = road.getSideIntersection(buildingObject, 2);
                    if (intersect == null) continue;
                    if (road.isVertical()){
                        intersect.shift(-road.cellPos.y);
                    }
                    else {
                        intersect.shift(-road.cellPos.x);
                    }
                    if (intersect.length() <= 0) continue;
                    lastRoad = road;
                    if (parent.getRandom().nextBoolean()){
                        int position = parent.getRandom().nextInt(intersect.length()) + intersect.first - 1;
                        TransportNetNodeObject cross = splitRoad(road, position);
                        buildingObject.setTransportNetEntranceNode(cross);
                        return;
                    }
                }
            }
        }
        if (lastRoad != null){
            Interval intersect = lastRoad.getSideIntersection(buildingObject, 2);
            if (lastRoad.isVertical()){
                intersect.shift(-lastRoad.cellPos.y);
                //intersect.firstPlate += 2;
            }
            else {
                intersect.shift(-lastRoad.cellPos.x);
                //intersect.secondPlate += 2;
            }
            if (intersect.length() > 0) {
                TransportNetNodeObject cross = splitRoad(lastRoad, parent.getRandom().nextInt(intersect.length()) + intersect.first);
                buildingObject.setTransportNetEntranceNode(cross);
                return;
            }
        }
    }

    public TransportNetNodeObject splitRoad(TransportNetEdgeObject road, int position){
        removeTransportNetObject(road);
        Index firstRoadSize = new Index(0, 0);
        Index crossPos = new Index(road.cellPos);
        Index crossSize = new Index(1, 1);
        Index secondRoadPos = new Index(0, 0);
        Index secondRoadSize = new Index(road.size);
        if (road.isVertical()){
            firstRoadSize.x = road.getCapacity();
            firstRoadSize.y = position;
            crossPos.y += position;
            crossSize.x = road.getCapacity();
            secondRoadPos.x = crossPos.x;
            secondRoadPos.y = crossPos.y + 1;
            secondRoadSize.y -= (position + 1);
        }
        else{
            firstRoadSize.y = road.getCapacity();
            firstRoadSize.x = position;
            crossPos.x += position;
            crossSize.y = road.getCapacity();
            secondRoadPos.x = crossPos.x + 1;
            secondRoadPos.y = crossPos.y;
            secondRoadSize.x -= (position + 1);
        }
        TransportNetEdgeObject newRoad = TransportNetEdgeObject.createRoadWithSameType(road,
                parent, new Index(road.cellPos), firstRoadSize, road.isVertical());
        addTransportNetElement(newRoad);

        TransportNetNodeObject newCross = TransportNetNodeObject.createRoadCrossWithSameType(road,
                parent, crossPos, crossSize);
        addTransportNetElement(newCross);

        newRoad = TransportNetEdgeObject.createRoadWithSameType(road, parent, secondRoadPos, secondRoadSize, road.isVertical());
        addTransportNetElement(newRoad);
        return newCross;
    }

    public boolean addTransportNetElement(TransportNetObject transportNetObject){
        //System.out.println("add transport element");
        if (!isFree(transportNetObject)){
            //System.out.println("failed add transport element");
            return false;
        }
        addFieldObject(transportNetObject);
        for(OccupationPiece piece: notOccupiedPieces){
            if(piece.isNeighbour(transportNetObject)){
                piece.addTransportNetObject(transportNetObject);
            }
        }
        for(FieldObject object: fieldObjects){
            if (!object.isNeighbour(transportNetObject)) continue;
            if (object.isBuilding()){
                BuildingObject building = (BuildingObject)object;
                building.addTransportNetObject(transportNetObject);
                if (transportNetObject.isNode()){
                    TransportNetNodeObject roadCrossObject = (TransportNetNodeObject)transportNetObject;
                    roadCrossObject.addLinkedBuilding(building);
                }
            }
            if (object.isTransportNetObject()){
                TransportNetObject netObject = (TransportNetObject)object;
                if (netObject.isNode() && transportNetObject.isEdge()){
                    TransportNetNodeObject cross = (TransportNetNodeObject)netObject;
                    TransportNetEdgeObject road = (TransportNetEdgeObject)transportNetObject;
                    Index.Direction side = cross.getSameSide(road);
                    if (side == Index.Direction.None) continue;
                    if (Index.isVertical(side) == road.isVertical()){
                        cross.addNetElement(road);
                        road.setNode(cross);
                    }
                }
                if (netObject.isEdge() && transportNetObject.isNode()){
                    TransportNetNodeObject cross = (TransportNetNodeObject)transportNetObject;
                    TransportNetEdgeObject road = (TransportNetEdgeObject)netObject;
                    Index.Direction side = cross.getSameSide(road);
                    if (side == Index.Direction.None) continue;
                    if (Index.isVertical(side) == road.isVertical()){
                        cross.addNetElement(road);
                        road.setNode(cross);
                    }
                }
                if (netObject.isNode() && transportNetObject.isNode()){
                    TransportNetNodeObject cross1 = (TransportNetNodeObject)transportNetObject;
                    TransportNetNodeObject cross2 = (TransportNetNodeObject)netObject;
                    cross1.addNetElement(cross2);
                    cross2.addNetElement(cross1);
                }
            }
        }
        return true;
    }

    public void prolongTransportSystem(){
        for(FieldObject object: fieldObjects){
            if (!object.isTransportNetObject()) continue;
            TransportNetObject netObject = (TransportNetObject)object;
            if (!netObject.isNode()) continue;
            //System.out.println("new node");
            TransportNetNodeObject cross = (TransportNetNodeObject) netObject;
            ArrayList<Index.Direction> directions = cross.getDirections();
            for(Index.Direction direction: Index.getAllDirectionsRandom(parent.getRandom())){
                if (!directions.contains(direction)){
                    //System.out.println("new direction");
                    if (prolongTransportNode(cross, direction)){
                       // System.out.println("prolong completed");
                        return;
                    }
                    //System.out.println("couldn't prolong");
                }
            }
        }

    }

    public boolean prolongTransportNode(TransportNetNodeObject cross, Index.Direction direction){
        int width;
        int height;
        //System.out.println("prolong node");
        //System.out.println("cross: pos=(" + cross.cellPos.x + "," + cross.cellPos.y + "); size=(" +
            //    cross.size.x + "," + cross.size.y + ")");
        Index spacePos = cross.cellPos.add(Index.getUnitIndex(direction));
        //System.out.println("space pos: " + spacePos.x + " " + spacePos.y);
        OccupationPiece piece = getSpace(spacePos);
        if (piece == null) return false;
        //System.out.println("piece not null; pos=(" + piece.cellPos.x + "," + piece.cellPos.y + "); size=(" +
          //     piece.size.x + "," + piece.size.y + ")");
        boolean success;
        switch (direction){

            case Up:
                width = cross.size.x;
                height = parent.getRandom().nextInt(piece.size.y)+1;
                success = addTransportNetElement(TransportNetEdgeObject.createRoadWithSameType(cross,
                        parent, cross.cellPos.add(new Index(0, -height)), new Index(width, height), true));
                if (!success) return false;
                addTransportNetElement(TransportNetNodeObject.createRoadCrossWithSameType(cross,
                        parent, cross.cellPos.add(new Index(0, -height - 1)), new Index(width, 1)));
                break;
            case Down:
                width = cross.size.x;
                height = parent.getRandom().nextInt(piece.size.y)+1;
                success = addTransportNetElement(TransportNetEdgeObject.createRoadWithSameType(cross,
                        parent, cross.cellPos.add(new Index(0, cross.size.y)), new Index(width, height), true));
                if (!success) return false;
                addTransportNetElement(TransportNetNodeObject.createRoadCrossWithSameType(cross,
                        parent, cross.cellPos.add(new Index(0, cross.size.y + height)), new Index(width, 1)));
                break;
            case Right:
                width = cross.size.y;
                height = parent.getRandom().nextInt(piece.size.x)+1;
                success = addTransportNetElement(TransportNetEdgeObject.createRoadWithSameType(cross,
                        parent, cross.cellPos.add(new Index(cross.size.x, 0)), new Index(height, width), false));
                if (!success) return false;
                addTransportNetElement(TransportNetNodeObject.createRoadCrossWithSameType(cross,
                        parent, cross.cellPos.add(new Index(cross.size.x + height, 0)), new Index(1, width)));
                break;
            case Left:
                width = cross.size.y;
                height = parent.getRandom().nextInt(piece.size.x)+1;
                success = addTransportNetElement(TransportNetEdgeObject.createRoadWithSameType(cross,
                        parent, cross.cellPos.add(new Index(-height, 0)), new Index(height, width), false));
                if (!success) return false;
                addTransportNetElement(TransportNetNodeObject.createRoadCrossWithSameType(cross,
                        parent, cross.cellPos.add(new Index(-height - 1, 0)), new Index(1, width)));
                break;
            case None:
                break;
        }
        return true;
    }

    public void removeTransportNetObject(TransportNetObject netObject){
        removeFieldObject(netObject);

        for(OccupationPiece piece: notOccupiedPieces){
            if (piece.isNeighbour(netObject)) piece.removeTransportNetObject(netObject);
        }

        for(FieldObject object: fieldObjects){
            if (!object.isNeighbour(netObject)) continue;
            if (object.isBuilding()){
                BuildingObject building = (BuildingObject)object;
                building.removeTransportNetObject(netObject);
            }
            if (object.isTransportNetObject()){
                TransportNetObject iterNetObject = (TransportNetObject)object;
                if (iterNetObject.isEdge() && netObject.isNode()){
                    TransportNetEdgeObject road = (TransportNetEdgeObject)iterNetObject;
                    TransportNetNodeObject cross = (TransportNetNodeObject)netObject;
                    road.removeNode(cross);
                    cross.removeNetElement(road);
                }
                if (iterNetObject.isNode() && netObject.isEdge()){
                    TransportNetEdgeObject road = (TransportNetEdgeObject)netObject;
                    TransportNetNodeObject cross = (TransportNetNodeObject)iterNetObject;
                    road.removeNode(cross);
                    cross.removeNetElement(road);
                }
            }
        }
    }

    public Index getNetObjectNeighborPosForBuilding(OccupationPiece piece, Index size, TransportNetObject netObject){
        Index finalRes = null;
        Index.Direction side = netObject.getSide(piece);
        Index iter = new Index(0, 0);
        int shift = 0;
        if (netObject.isEdge()) shift = 2;
        switch (side) {

            case Up:
                iter.y = netObject.cellPos.y - 1;
                for (int i = netObject.cellPos.x + shift; i < netObject.cellPos.x + netObject.size.x - shift; i++) {
                    if (i >= piece.pos.x + piece.pieceSize.x) break;
                    iter.x = i;
                    Index res = piece.getFirstSpaceWithIncludedPos(size, iter);
                    if (res != null) {
                        finalRes = res;
                        return res;
                        //if (parent.getRandom().nextInt(100 + i) > 35) return res;
                    }
                }
                break;
            case Down:
                iter.y = netObject.cellPos.y + netObject.size.y;
                for (int i = netObject.cellPos.x + shift; i < netObject.cellPos.x + netObject.size.x - shift; i++) {
                    if (i >= piece.pos.x + piece.pieceSize.x) break;
                    iter.x = i;
                    Index res = piece.getFirstSpaceWithIncludedPos(size, iter);
                    if (res != null) {
                        finalRes = res;
                        return res;
                        //if (parent.getRandom().nextInt(100 + i) > 35) return res;
                    }
                }
                break;
            case Right:
                iter.x = netObject.cellPos.x + netObject.size.x;
                for (int i = netObject.cellPos.y + shift; i < netObject.cellPos.y + netObject.size.y - shift; i++) {
                    if (i >= piece.pos.y + piece.pieceSize.y) break;
                    iter.y = i;
                    Index res = piece.getFirstSpaceWithIncludedPos(size, iter);
                    if (res != null) {
                        finalRes = res;
                        return res;
                        //if (parent.getRandom().nextInt(100 + i) > 35) return res;
                    }
                }
                break;
            case Left:
                iter.x = netObject.cellPos.x - 1;
                for (int i = netObject.cellPos.y + shift; i < netObject.cellPos.y + netObject.size.y - shift; i++) {
                    if (i >= piece.pos.y + piece.pieceSize.y) break;
                    iter.y = i;
                    Index res = piece.getFirstSpaceWithIncludedPos(size, iter);
                    if (res != null) {
                        finalRes = res;
                        return res;
                        //if (parent.getRandom().nextInt(100 + i) > 35) return res;
                    }
                }
                break;
            case None:
                break;
        }
        return finalRes;
    }

    public void removeFieldObject(FieldObject object){
        if (fieldObjects.contains(object)){
            fieldObjects.remove(object);
            addNotOccupiedPiece(new OccupationPiece(object));
        }
    }

    public void addGateObject(Index pos, boolean verticalWall){
        Index size = new Index(1, 1);
        if (!isFree(pos, size)) return;
        if (verticalWall){
            if (!isFree(pos.add(new Index(1, 0)), size)) return;
            if (!isFree(pos.add(new Index(-1, 0)), size)) return;
            PavementRoadCrossObject cross1 = new PavementRoadCrossObject(parent, pos.add(new Index(1, 0)), size);
            addTransportNetElement(cross1);
            PavementRoadCrossObject cross2 = new PavementRoadCrossObject(parent, pos.add(new Index(-1, 0)), size);
            addTransportNetElement(cross2);
            cross1.addNetElement(cross2);
            cross2.addNetElement(cross1);
        }
        else {
            if (!isFree(pos.add(new Index(0, 1)), size)) return;
            if (!isFree(pos.add(new Index(0, -1)), size)) return;
            PavementRoadCrossObject cross1 = new PavementRoadCrossObject(parent, pos.add(new Index(0, 1)), size);
            addTransportNetElement(cross1);
            PavementRoadCrossObject cross2 = new PavementRoadCrossObject(parent, pos.add(new Index(0, -1)), size);
            addTransportNetElement(cross2);
            cross1.addNetElement(cross2);
            cross2.addNetElement(cross1);
        }
        GateObject gateObject = new GateObject(parent, pos, size);
        addFieldObject(gateObject);
    }

    public ArrayList<FieldObject> getArray() {
        return fieldObjects;
    }

    public ArrayList<OccupationPiece> getTempArray() {
        return notOccupiedPieces;
    }
}
