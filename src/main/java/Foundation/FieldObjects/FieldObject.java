package Foundation.FieldObjects;

import Foundation.BasicShapes.BasicShape;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

/// various object in the Field (house, road)

public abstract class FieldObject implements FieldObjectType{

    protected Field parent;

    protected Index cellPos;
    protected Index size;

    private ArrayList<BasicShape> basicShapes;


    public FieldObject(Field parent, Index cellPos, Index size){
        this.parent = parent;
        this.cellPos = cellPos;
        this.size = size;
        basicShapes = new ArrayList<>();
    }

    public Field getParent() {
        return parent;
    }

    public ArrayList<BasicShape> getBasicShapes(){
        return basicShapes;
    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes){
        this.basicShapes = basicShapes;
        Coord shift = getShift();
        for(BasicShape basicShape: basicShapes){
            basicShape.shift(shift);
        }
    }

    public void addBasicShape(BasicShape basicShape){
        basicShape.shift(getShift());
        basicShapes.add(basicShape);
    }

    public void clearBasicShapes(){
        basicShapes.clear();
    }

    public Coord getShift(){
        double size = parent.getCellSize();
        return parent.getShift().add(new Coord(cellPos.x * size, cellPos.y * size));
    }

    public boolean isIntersects(FieldObject other){
        Index a1 = cellPos;
        Index a2 = cellPos.add(size).minus(new Index(1, 1));
        Index b1 = other.cellPos;
        Index b2 = other.cellPos.add(other.size).minus(new Index(1, 1));
        if (a1.x > b2.x) return false;
        if (a2.x < b1.x) return false;
        if (a1.y > b2.y) return false;
        if (a2.y < b1.y) return false;
        return true;
    }

    public boolean isIntersects(Index pos, Index size){
        Index a1 = this.cellPos;
        Index a2 = this.cellPos.add(this.size).minus(new Index(1, 1));
        Index b1 = pos;
        Index b2 = pos.add(size).minus(new Index(1, 1));
        if (a1.x > b2.x) return false;
        if (a2.x < b1.x) return false;
        if (a1.y > b2.y) return false;
        if (a2.y < b1.y) return false;
        return true;
    }

    public boolean isNeighbour(FieldObject fieldObject){
        if (cellPos.x == fieldObject.cellPos.x + fieldObject.size.y) return true;
        if (cellPos.x + size.x == fieldObject.cellPos.x) return true;
        if (cellPos.y == fieldObject.cellPos.y + fieldObject.size.y) return true;
        if (cellPos.y + size.y == fieldObject.cellPos.y) return true;
        return false;
    }

    public boolean contains(Index index){
        return  (cellPos.x <= index.x && cellPos.x + size.x < index.x &&
                cellPos.y <= index.y && cellPos.y + size.y < index.y);
    }

    public Index.Direction getSide(FieldObject fieldObject){
        if (cellPos.x == fieldObject.cellPos.x + fieldObject.size.y) return Index.Direction.Left;
        if (cellPos.x + size.x == fieldObject.cellPos.x) return Index.Direction.Right;
        if (cellPos.y == fieldObject.cellPos.y + fieldObject.size.y) return Index.Direction.Up;
        if (cellPos.y + size.y == fieldObject.cellPos.y) return Index.Direction.Down;
        return Index.Direction.None;
    }

    public Index.Direction getSameSide(FieldObject fieldObject){
        if (cellPos.x == fieldObject.cellPos.x + fieldObject.size.y
                && cellPos.y == fieldObject.cellPos.y && size.y == fieldObject.size.y) return Index.Direction.Left;
        if (cellPos.x + size.x == fieldObject.cellPos.x
                && cellPos.y == fieldObject.cellPos.y && size.y == fieldObject.size.y) return Index.Direction.Right;
        if (cellPos.y == fieldObject.cellPos.y + fieldObject.size.y
                && cellPos.x == fieldObject.cellPos.x && size.x == fieldObject.size.x) return Index.Direction.Up;
        if (cellPos.y + size.y == fieldObject.cellPos.y
                && cellPos.x == fieldObject.cellPos.x && size.x == fieldObject.size.x) return Index.Direction.Down;
        return Index.Direction.None;
    }

    public abstract void setShapes();

    @Override
    public boolean isTransportNetObject(){
        return false;
    }

    @Override
    public boolean isBuilding(){
        return false;
    }


}
