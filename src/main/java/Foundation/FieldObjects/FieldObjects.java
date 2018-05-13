package Foundation.FieldObjects;

import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;
import java.util.Arrays;

public class FieldObjects {

    private Field parent;
    private ArrayList<FieldObject> fieldObjects;
    private ArrayList<OccupationPiece> notOccupiedPieces;

    public FieldObjects(Field parent){
        this.parent = parent;
        fieldObjects = new ArrayList<>();
        notOccupiedPieces = new ArrayList<>();
        notOccupiedPieces.add(new OccupationPiece(new Index(0, 0), new Index(parent.getCellAmount(), parent.getCellAmount())));
    }

    public ArrayList<OccupationPiece> getFreeSpace(Index size){
        ArrayList<OccupationPiece> result = new ArrayList<>();
        for(OccupationPiece piece: notOccupiedPieces){
            if (piece.size.x >= size.x && piece.size.y >= size.y) result.add(piece);
        }
        return result;
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
        int sizeX2 = piece.pos.x + piece.size.x - pos.x - size.x;
        int sizeY2 = piece.pos.y + piece.size.y - pos.y - size.y;
        if (sizeX1 != 0) notOccupiedPieces.add(new OccupationPiece(new Index(0, pos.y), new Index(sizeX1, size.y)));
        if (sizeY1 != 0) notOccupiedPieces.add(new OccupationPiece(new Index(pos.x, 0), new Index(size.x, sizeY1)));
        if (sizeX1 != 0 && sizeY1 != 0) notOccupiedPieces.add(new OccupationPiece(new Index(piece.pos), new Index(sizeX1, sizeY1)));
        if (sizeX2 != 0) notOccupiedPieces.add(new OccupationPiece(new Index(pos.x + size.x, pos.y), new Index(sizeX2, size.y)));
        if (sizeY2 != 0) notOccupiedPieces.add(new OccupationPiece(new Index(pos.x, pos.y + size.y), new Index(size.x, sizeY2)));
        if (sizeX2 != 0 && sizeY2 != 0) notOccupiedPieces.add(new OccupationPiece(pos.add(size), new Index(sizeX2, sizeY2)));
        if (sizeX1 != 0 && sizeY2 != 0) notOccupiedPieces.add(new OccupationPiece(new Index(0, pos.y + size.y), new Index(sizeX1, sizeY2)));
        if (sizeX2 != 0 && sizeY1 != 0) notOccupiedPieces.add(new OccupationPiece(new Index(pos.x + size.x, 0), new Index(sizeX2, sizeY1)));
    }

    public boolean isFree(Index pos, Index size){
        /*int cellAmount = parent.getCellAmount();
        if (!(pos.x >= 0 && pos.x + size.x < cellAmount && pos.y >= 0 && pos.x + size.y < cellAmount)) return false;
        for(FieldObject object: fieldObjects){
            if (object.isIntersects(pos, size)) return false;
        }
        return true;*/
        OccupationPiece piece = getSpace(pos, size);
        return (piece != null);
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

    public ArrayList<FieldObject> getArray() {
        return fieldObjects;
    }
}
