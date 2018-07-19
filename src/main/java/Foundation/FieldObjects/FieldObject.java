package Foundation.FieldObjects;

import Foundation.BasicShapes.BasicShape;
import Foundation.Field;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Foundation.Window;
import Utils.Boundary.RectangleBoundary;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import Utils.Geometry.Interval;

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

    public Index getCellPos(){
        return cellPos;
    }

    public Index getSize(){
        return size;
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

    public RectangleBoundary intersect(FieldObject fieldObject){
        Index a1 = this.cellPos;
        Index a2 = this.cellPos.add(this.size);
        Index b1 = fieldObject.getCellPos();
        Index b2 = fieldObject.getCellPos().add(fieldObject.getSize());
        Index pos1 = new Index(Math.max(a1.x, b1.x), Math.max(a1.y, b1.y));
        Index pos2 = new Index(Math.min(a2.x, b2.x), Math.min(a2.y, b2.y));
        RectangleBoundary rect = new RectangleBoundary(pos1, pos2.minus(pos1));
        return rect;
    }

    public boolean isNeighbour(FieldObject fieldObject){
        if (cellPos.x == fieldObject.cellPos.x + fieldObject.size.x
                && fieldObject.cellPos.y + fieldObject.size.y > cellPos.y
                && fieldObject.cellPos.y < cellPos.y + size.y) return true;
        if (cellPos.x + size.x == fieldObject.cellPos.x
                && fieldObject.cellPos.y + fieldObject.size.y > cellPos.y
                && fieldObject.cellPos.y < cellPos.y + size.y) return true;
        if (cellPos.y == fieldObject.cellPos.y + fieldObject.size.y
                && fieldObject.cellPos.x + fieldObject.size.x > cellPos.x
                && fieldObject.cellPos.x < cellPos.x + size.x) return true;
        if (cellPos.y + size.y == fieldObject.cellPos.y
                && fieldObject.cellPos.x + fieldObject.size.x > cellPos.x
                && fieldObject.cellPos.x < cellPos.x + size.x) return true;
        return false;
    }

    public boolean contains(Index index){
        return  (cellPos.x <= index.x && cellPos.x + size.x > index.x &&
                cellPos.y <= index.y && cellPos.y + size.y > index.y);
    }

    public Index.Direction getSide(FieldObject fieldObject){
        if (cellPos.x == fieldObject.cellPos.x + fieldObject.size.x
                && fieldObject.cellPos.y + fieldObject.size.y > cellPos.y
                && fieldObject.cellPos.y < cellPos.y + size.y) return Index.Direction.Left;
        if (cellPos.x + size.x == fieldObject.cellPos.x
                && fieldObject.cellPos.y + fieldObject.size.y > cellPos.y
                && fieldObject.cellPos.y < cellPos.y + size.y) return Index.Direction.Right;
        if (cellPos.y == fieldObject.cellPos.y + fieldObject.size.y
                && fieldObject.cellPos.x + fieldObject.size.x > cellPos.x
                && fieldObject.cellPos.x < cellPos.x + size.x) return Index.Direction.Up;
        if (cellPos.y + size.y == fieldObject.cellPos.y
                && fieldObject.cellPos.x + fieldObject.size.x > cellPos.x
                && fieldObject.cellPos.x < cellPos.x + size.x) return Index.Direction.Down;
        return Index.Direction.None;
    }

    public Index.Direction getSameSide(FieldObject fieldObject){
        if (cellPos.x == fieldObject.cellPos.x + fieldObject.size.x
                && cellPos.y == fieldObject.cellPos.y && size.y == fieldObject.size.y) return Index.Direction.Left;
        if (cellPos.x + size.x == fieldObject.cellPos.x
                && cellPos.y == fieldObject.cellPos.y && size.y == fieldObject.size.y) return Index.Direction.Right;
        if (cellPos.y == fieldObject.cellPos.y + fieldObject.size.y
                && cellPos.x == fieldObject.cellPos.x && size.x == fieldObject.size.x) return Index.Direction.Up;
        if (cellPos.y + size.y == fieldObject.cellPos.y
                && cellPos.x == fieldObject.cellPos.x && size.x == fieldObject.size.x) return Index.Direction.Down;
        return Index.Direction.None;
    }

    public Interval getSideIntersection(FieldObject fieldObject){
        if ((cellPos.x == fieldObject.cellPos.x + fieldObject.size.x)
                && (fieldObject.cellPos.y + fieldObject.size.y > cellPos.y)
                && (fieldObject.cellPos.y < cellPos.y + size.y)) {
            Interval interval = new Interval(Math.max(fieldObject.cellPos.y, cellPos.y),
                    Math.min(fieldObject.cellPos.y + fieldObject.size.y, cellPos.y + size.y));
            return interval;
        }
        if ((cellPos.x + size.x == fieldObject.cellPos.x)
                && (fieldObject.cellPos.y + fieldObject.size.y > cellPos.y)
                && (fieldObject.cellPos.y < cellPos.y + size.y)){
            Interval interval = new Interval(Math.max(fieldObject.cellPos.y, cellPos.y),
                    Math.min(fieldObject.cellPos.y + fieldObject.size.y, cellPos.y + size.y));
            return interval;
        }

        if ((cellPos.y == fieldObject.cellPos.y + fieldObject.size.y)
                && (fieldObject.cellPos.x + fieldObject.size.x > cellPos.x)
                && (fieldObject.cellPos.x < cellPos.y + size.x)) {
            Interval interval = new Interval(Math.max(fieldObject.cellPos.y, cellPos.y),
                    Math.min(fieldObject.cellPos.y + fieldObject.size.y, cellPos.y + size.y));
            return interval;
        }

        if ((cellPos.y + size.y == fieldObject.cellPos.y)
                && (fieldObject.cellPos.x + fieldObject.size.x > cellPos.x)
                && (fieldObject.cellPos.x < cellPos.y + size.x)) {
            Interval interval = new Interval(Math.max(fieldObject.cellPos.y, cellPos.y),
                    Math.min(fieldObject.cellPos.y + fieldObject.size.y, cellPos.y + size.y));
            return interval;
        }
        return null;
    }

    public Interval getSideIntersection(FieldObject fieldObject, int shift){
        if ((cellPos.x == fieldObject.cellPos.x + fieldObject.size.x)
                && (fieldObject.cellPos.y + fieldObject.size.y > cellPos.y + shift)
                && (fieldObject.cellPos.y < cellPos.y + size.y - shift)) {
            Interval interval = new Interval(Math.max(fieldObject.cellPos.y, cellPos.y + shift),
                    Math.min(fieldObject.cellPos.y + fieldObject.size.y, cellPos.y + size.y - shift));
            return interval;
        }
        if ((cellPos.x + size.x == fieldObject.cellPos.x)
                && (fieldObject.cellPos.y + fieldObject.size.y > cellPos.y + shift)
                && (fieldObject.cellPos.y < cellPos.y + size.y - shift)){
            Interval interval = new Interval(Math.max(fieldObject.cellPos.y, cellPos.y + shift),
                    Math.min(fieldObject.cellPos.y + fieldObject.size.y, cellPos.y + size.y - shift));
            return interval;
        }

        if ((cellPos.y == fieldObject.cellPos.y + fieldObject.size.y)
                && (fieldObject.cellPos.x + fieldObject.size.x > cellPos.x + shift)
                && (fieldObject.cellPos.x < cellPos.x + size.x - shift)) {
            Interval interval = new Interval(Math.max(fieldObject.cellPos.x, cellPos.x+ shift),
                    Math.min(fieldObject.cellPos.x + fieldObject.size.x, cellPos.x + size.x - shift));
            return interval;
        }

        if ((cellPos.y + size.y == fieldObject.cellPos.y)
                && (fieldObject.cellPos.x + fieldObject.size.x > cellPos.x + shift)
                && (fieldObject.cellPos.x < cellPos.x + size.x - shift)) {
            Interval interval = new Interval(Math.max(fieldObject.cellPos.x, cellPos.x + shift),
                    Math.min(fieldObject.cellPos.x + fieldObject.size.x, cellPos.x + size.x - shift));
            return interval;
        }
        return null;
    }

    public abstract void setShapes();

    public abstract Window getInfoWindow();

    public Mode getModeOnClick(GameWindowHelperElement gameWindowHelperElement){
        return null;
    }

    @Override
    public boolean isTransportNetObject(){
        return false;
    }

    @Override
    public boolean isBuilding(){
        return false;
    }

    @Override
    public boolean isNaturalObject(){
        return false;
    }



}
