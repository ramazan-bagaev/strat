package Foundation.FieldObjects;

import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<OccupationPiece> freePieces = getFreeSpace(size);
        for(OccupationPiece piece: freePieces){
            ArrayList<TransportNetObject> netObjects = piece.getTransportNetObject();
            for(TransportNetObject netObject: netObjects){
                if (netObject.isNode()){
                    CrossRoadObject cross = (CrossRoadObject)netObject;
                    Index.Direction side = cross.getSide(piece);
                    Index iter = new Index(0, 0);
                    switch (side){

                        case Up:
                            iter.y = cross.cellPos.y - 1;
                            for(int i = cross.cellPos.x; i < cross.cellPos.x + cross.size.x; i++){
                                iter.x = i;
                                Index res = piece.getSpaceWithIncludedPos(size, iter);
                                if (res != null) {
                                    return res;
                                }
                            }
                            break;
                        case Down:
                            iter.y = cross.cellPos.y + cross.size.y;
                            for(int i = cross.cellPos.x; i < cross.cellPos.x + cross.size.x; i++){
                                iter.x = i;
                                Index res = piece.getSpaceWithIncludedPos(size, iter);
                                if (res != null){
                                    return res;
                                }
                            }
                            break;
                        case Right:
                            iter.x = cross.cellPos.x + cross.size.x;
                            for(int i = cross.cellPos.y; i < cross.cellPos.y + cross.size.y; i++){
                                iter.y = i;
                                Index res = piece.getSpaceWithIncludedPos(size, iter);
                                if (res != null){
                                    return res;
                                }
                            }
                            break;
                        case Left:
                            iter.x = cross.cellPos.x - 1;
                            for(int i = cross.cellPos.y; i < cross.cellPos.y + cross.size.y; i++){
                                iter.y = i;
                                Index res = piece.getSpaceWithIncludedPos(size, iter);
                                if (res != null){
                                    return res;
                                }
                            }
                            break;
                        case None:
                            break;
                    }
                }
            }
        }
        return null;
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
        if (!(pos.x >= 0 && pos.x + size.x < cellAmount && pos.y >= 0 && pos.x + size.y < cellAmount)) return false;
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
        notOccupiedPieces.add(piece);
    }

    public void addBuilding(BuildingObject building){
        if (!isFree(building)) return;
        addFieldObject(building);
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
                building.addTranportNetObject(transportNetObject);
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
                    if (Index.isVertical(side) == road.isVertical()){
                        cross.addEdge(road);
                        road.setNode(cross);
                    }
                }
                if (netObject.isEdge() && transportNetObject.isNode()){
                    CrossRoadObject cross = (CrossRoadObject)transportNetObject;
                    RoadObject road = (RoadObject)netObject;
                    Index.Direction side = cross.getSameSide(road);
                    if (Index.isVertical(side) == road.isVertical()){
                        cross.addEdge(road);
                        road.setNode(cross);
                    }
                }
            }
        }
    }

    public ArrayList<FieldObject> getArray() {
        return fieldObjects;
    }

    public ArrayList<OccupationPiece> getTempArray() {
        return notOccupiedPieces;
    }
}
