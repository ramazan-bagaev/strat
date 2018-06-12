package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.FieldObjects.TransportObjects.TransportNetObject;
import Foundation.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import Foundation.Field;

import java.util.ArrayList;

public class OccupationPiece extends FieldObject {

    public Index pos;
    public Index pieceSize;

    private ArrayList<TransportNetObject> transportNetObjects;



    public OccupationPiece(Index pos, Index pieceSize, Field parent){
        super(parent, pos, pieceSize);
        this.pos = pos;
        this.pieceSize = pieceSize;
        transportNetObjects = new ArrayList<>();
        setShapes();
    }

    public OccupationPiece(FieldObject fieldObject){
        super(fieldObject.parent, new Index(fieldObject.cellPos), new Index(fieldObject.size));
        this.pos = cellPos;
        this.pieceSize = size;
        transportNetObjects = new ArrayList<>();
        setShapes();
    }

    public boolean tryUnite(OccupationPiece piece){
        Index.Direction direction = getSameSide(piece);
        if (direction == Index.Direction.None) return false;

        switch (direction){

            case Up:
                pos.y = piece.pos.y;
                pieceSize.y += piece.pieceSize.y;
                break;
            case Down:
                pieceSize.y += piece.pieceSize.y;
                break;
            case Right:
                pieceSize.x += piece.pieceSize.x;
                break;
            case Left:
                pos.x = piece.pos.x;
                pieceSize.x += piece.pieceSize.x;
                break;
        }
        return true;
    }

    public boolean contains(Index pos, Index size){
        return (this.pos.x <= pos.x && this.pos.y <= pos.y &&
                this.pos.x + this.pieceSize.x >= pos.x + size.x &&
                this.pos.y + this.pieceSize.y >= pos.y + size.y);

    }

    /*public boolean isNeighbour(FieldObject fieldObject){
        if (pos.x == fieldObject.cellPos.x + fieldObject.size.y) return true;
        if (pos.x + pieceSize.x == fieldObject.cellPos.x) return true;
        if (pos.y == fieldObject.cellPos.y + fieldObject.size.y) return true;
        if (pos.y + pieceSize.y == fieldObject.cellPos.y) return true;
        return false;
    }*/

    public Index.Direction getNeighbour(OccupationPiece piece){
        if (    pos.x == piece.pos.x + piece.pieceSize.x
                && piece.pos.y + piece.pieceSize.y > pos.y
                && piece.pos.y < pos.y + pieceSize.y) return Index.Direction.Left;

        if (pos.x + pieceSize.x == piece.pos.x
                && piece.pos.y + piece.pieceSize.y > pos.y
                && piece.pos.y < pos.y + pieceSize.y) return Index.Direction.Right;
        if (pos.y == piece.pos.y + piece.pieceSize.y
                && piece.pos.x + piece.pieceSize.x > pos.x
                && piece.pos.x < pos.x + pieceSize.x) return Index.Direction.Up;
        if (pos.y + pieceSize.y == piece.pos.y
                && piece.pos.x + piece.pieceSize.x > pos.x
                && piece.pos.x < pos.x + pieceSize.x) return Index.Direction.Down;
        return Index.Direction.None;
    }

    public Index.Direction getSameSide(OccupationPiece piece){
        if (pos.x == piece.pos.x + piece.pieceSize.x && pos.y == piece.pos.y && pieceSize.y == piece.pieceSize.y) return Index.Direction.Left;
        if (pos.x + pieceSize.x == piece.pos.x && pos.y == piece.pos.y && pieceSize.y == piece.pieceSize.y) return Index.Direction.Right;
        if (pos.y == piece.pos.y + piece.pieceSize.y && pos.x == piece.pos.x && pieceSize.x == piece.pieceSize.x) return Index.Direction.Up;
        if (pos.y + pieceSize.y == piece.pos.y && pos.x == piece.pos.x && pieceSize.x == piece.pieceSize.x) return Index.Direction.Down;
        return Index.Direction.None;
    }

    public void addTransportNetObject(TransportNetObject transportNetObject){
        transportNetObjects.add(transportNetObject);
    }

    public void removeTransportNetObject(TransportNetObject transportNetObject){
        transportNetObjects.remove(transportNetObject);
    }

    public ArrayList<TransportNetObject> getTransportNetObject(){
        return transportNetObjects;
    }

    public Index getFirstSpaceWithIncludedPos(Index spaceSize, Index includedPos){
        Index res = new Index(0, 0);

        for(int i = -spaceSize.y + 1; i <= spaceSize.y - 1; i++){
            res.y = i + includedPos.y;
            for(int j = -spaceSize.x + 1; j <= spaceSize.x - 1; j++){
                res.x = j + includedPos.x;
                if (contains(res, spaceSize)) return res;
            }
        }
        return null;
    }

    public Index getRandomSpaceWithIncludedPos(Index spaceSize, Index includedPos){
        Index res = new Index(0, 0);
        Index finalRes = null;

        for(int i = -spaceSize.y + 1; i <= spaceSize.y - 1; i++){
            res.y = i + includedPos.y;
            for(int j = -spaceSize.x + 1; j <= spaceSize.x - 1; j++){
                res.x = j + includedPos.x;
                if (contains(res, spaceSize)){
                    finalRes = res;
                    if (parent.getRandom().nextBoolean()) return res;
                }
            }
        }
        return finalRes;
    }

    @Override
    public void setShapes() {
        clearBasicShapes();
        cellPos = pos;
        size = pieceSize;
        double cellSize = getParent().getCellSize();
        double shift = 0.1;

        RectangleShape rectangleShape = new RectangleShape(new Coord(shift, shift),
                new Coord(cellSize* pieceSize.x - 2*shift, cellSize * pieceSize.y - 2*shift),
                new Color(Color.Type.Black), true, false);
        addBasicShape(rectangleShape);
    }

    @Override
    public Window getInfoWindow() {
        return null;
    }

}
