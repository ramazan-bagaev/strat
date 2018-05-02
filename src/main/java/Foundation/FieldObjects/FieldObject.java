package Foundation.FieldObjects;

import Foundation.BasicShapes.BasicShape;
import Foundation.Cell.Cell;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

/// various object in the Field (house, road, army squad ...)

public abstract class FieldObject {

    protected Field parent;

    protected Index cellPos;

    private ArrayList<BasicShape> basicShapes;


    public FieldObject(Field parent, Index cellPos){
        this.parent = parent;
        this.cellPos = cellPos;
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

    public abstract void setShapes();

    public abstract ArrayList<Cell> getCells();


}
