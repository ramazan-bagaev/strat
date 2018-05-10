package Foundation.FieldObjects;

import Foundation.BasicShapes.BasicShape;
import Foundation.Cell.Cell;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

/// various object in the Field (house, road)

public abstract class FieldObject {

    protected Field parent;

    protected Index cellPos;
    protected Index size;

    private ArrayList<BasicShape> basicShapes;


    public FieldObject(Field parent, Index cellPos, Index size){
        this.parent = parent;
        this.cellPos = cellPos;
        this.size = size;
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
        int size = parent.getCellSize();
        return parent.getShift().add(new Coord(cellPos.x * size, cellPos.y * size));
    }

    public boolean isIntersects(FieldObject other){
        Index a1 = cellPos;
        Index a2 = cellPos.add(size).minus(new Index(-1, -1));
        Index b1 = other.cellPos;
        Index b2 = other.cellPos.add(other.size).minus(new Index(-1, -1));
        if (a1.x > b2.x) return false;
        if (a2.x < b1.x) return false;
        if (a1.y > b2.y) return false;
        if (a2.y < b1.y) return false;
        return true;
    }

    public boolean isIntersects(Index pos, Index size){
        Index a1 = this.cellPos;
        Index a2 = this.cellPos.add(this.size).minus(new Index(-1, -1));
        Index b1 = pos;
        Index b2 = pos.add(size).minus(new Index(-1, -1));
        if (a1.x > b2.x) return false;
        if (a2.x < b1.x) return false;
        if (a1.y > b2.y) return false;
        if (a2.y < b1.y) return false;
        return true;
    }

    public abstract void setShapes();


}
