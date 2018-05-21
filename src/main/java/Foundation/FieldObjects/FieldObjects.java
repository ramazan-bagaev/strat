package Foundation.FieldObjects;

import Foundation.Field;
import Utils.Index;
import Utils.Interval;

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
                    if (((RoadObject)netObject).getLength() < 5) continue;
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

    public boolean isFree(Index pos, Index size){
        int cellAmount = parent.getCellAmount();
        if (!(pos.x >= 0 && pos.x + size.x <= cellAmount && pos.y >= 0 && pos.y + size.y <= cellAmount)) return false;
        for(FieldObject object: fieldObjects){
            if (object.isIntersects(pos, size)) return false;
        }
        return true;
    }

    public boolean isFree(FieldObject fieldObject){
        return isFree(fieldObject.cellPos, fieldObject.size);
    }

    public void addFieldObject(FieldObject fieldObject){
        OccupationPiece piece = getSpace(fieldObject.cellPos, fieldObject.size);
        if (piece == null) return;
        splitPiece(piece, fieldObject.cellPos, fieldObject.size);
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

    }

    public void addRoadAroundBuilding(BuildingObject building){
        Index pos = building.cellPos;
        Index size = building.size;
        RoadObject road = new RoadObject(parent, pos.add(new Index(-1, 0)), new Index(1, size.y), true);
        addTransportNetElement(road);
        CrossRoadObject cross = new CrossRoadObject(parent, pos.add(new Index(-1, -1)), new Index(1, 1));
        addTransportNetElement(cross);
        road = new RoadObject(parent, pos.add(new Index(0, -1)), new Index(size.x, 1), false);
        addTransportNetElement(road);
        cross = new CrossRoadObject(parent, pos.add(new Index(size.x, -1)), new Index(1, 1));
        addTransportNetElement(cross);
        road = new RoadObject(parent, pos.add(new Index(size.x, 0)), new Index(1, size.y), true);
        addTransportNetElement(road);
        cross = new CrossRoadObject(parent, pos.add(new Index(size.x, size.y)), new Index(1, 1));
        addTransportNetElement(cross);
        road = new RoadObject(parent, pos.add(new Index(0, size.y)), new Index(size.x, 1), false);
        addTransportNetElement(road);
        cross = new CrossRoadObject(parent, pos.add(new Index(-1, size.y)), new Index(1, 1));
        addTransportNetElement(cross);
    }

    public void linkBuildingWithTransportNet(BuildingObject buildingObject){
        boolean wasConnected = false;
        for(FieldObject object: fieldObjects){
            if (!object.isNeighbour(buildingObject)) continue;
            if (object.isTransportNetObject()) {
                TransportNetObject netObject = (TransportNetObject)object;
                buildingObject.addTransportNetObject(netObject);
                if (netObject.isNode()){
                    CrossRoadObject cross = (CrossRoadObject)netObject;
                    cross.addLinkedBuilding(buildingObject);
                    wasConnected = true;
                }
            }
        }
        if (wasConnected) return;
        RoadObject lastRoad = null;
        for(TransportNetObject netObject: buildingObject.getTransportNetObjects()){
            if (netObject.isEdge()){
                RoadObject road = (RoadObject)netObject;
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
                        int position = parent.getRandom().nextInt(intersect.length()) + intersect.first;
                        splitRoad(road, position);
                        return;
                    }
                }
            }
        }
        if (lastRoad != null){
            Interval intersect = lastRoad.getSideIntersection(buildingObject, 2);
            if (lastRoad.isVertical()){
                intersect.shift(-lastRoad.cellPos.y);
                intersect.first += 2;
            }
            else {
                intersect.shift(-lastRoad.cellPos.x);
                intersect.second += 2;
            }
            if (intersect.length() > 0) {
                splitRoad(lastRoad, parent.getRandom().nextInt(intersect.length()) + intersect.first);
                return;
            }
        }
        System.out.println("fail!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void splitRoad(RoadObject road, int position){
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
        RoadObject newRoad = new RoadObject(parent, new Index(road.cellPos), firstRoadSize, road.isVertical());
        addTransportNetElement(newRoad);
        CrossRoadObject newCross = new CrossRoadObject(parent, crossPos, crossSize);
        addTransportNetElement(newCross);
        newRoad = new RoadObject(parent, secondRoadPos, secondRoadSize, road.isVertical());
        addTransportNetElement(newRoad);
    }

    public void addTransportNetElement(TransportNetObject transportNetObject){
        if (!isFree(transportNetObject)) return;
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
                    CrossRoadObject crossRoadObject = (CrossRoadObject)transportNetObject;
                    crossRoadObject.addLinkedBuilding(building);
                }
            }
            if (object.isTransportNetObject()){
                TransportNetObject netObject = (TransportNetObject)object;
                if (netObject.isNode() && transportNetObject.isEdge()){
                    CrossRoadObject cross = (CrossRoadObject)netObject;
                    RoadObject road = (RoadObject)transportNetObject;
                    Index.Direction side = cross.getSameSide(road);
                    if (side == Index.Direction.None) continue;
                    if (Index.isVertical(side) == road.isVertical()){
                        cross.addEdge(road);
                        road.setNode(cross);
                    }
                }
                if (netObject.isEdge() && transportNetObject.isNode()){
                    CrossRoadObject cross = (CrossRoadObject)transportNetObject;
                    RoadObject road = (RoadObject)netObject;
                    Index.Direction side = cross.getSameSide(road);
                    if (side == Index.Direction.None) continue;
                    if (Index.isVertical(side) == road.isVertical()){
                        cross.addEdge(road);
                        road.setNode(cross);
                    }
                }
            }
        }
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
                    RoadObject road = (RoadObject)iterNetObject;
                    CrossRoadObject cross = (CrossRoadObject)netObject;
                    road.removeNode(cross);
                    cross.removeEdge(road);
                }
                if (iterNetObject.isNode() && netObject.isEdge()){
                    RoadObject road = (RoadObject)netObject;
                    CrossRoadObject cross = (CrossRoadObject)iterNetObject;
                    road.removeNode(cross);
                    cross.removeEdge(road);
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

    public ArrayList<FieldObject> getArray() {
        return fieldObjects;
    }

    public ArrayList<OccupationPiece> getTempArray() {
        return notOccupiedPieces;
    }
}
