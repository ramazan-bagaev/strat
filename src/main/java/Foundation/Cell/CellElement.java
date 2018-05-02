package Foundation.Cell;

import Foundation.BasicShapes.BasicShape;

import java.util.ArrayList;

public class CellElement {

    private ArrayList<BasicShape> basicShapes;

    private Cell parent;

    public CellElement(Cell parent){
        this.parent = parent;
        basicShapes = new ArrayList<>();
    }

    public Cell getParent() {
        return parent;
    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes){
        this.basicShapes = basicShapes;
        for(BasicShape basicShape: basicShapes){
            basicShape.shift(parent.getShift());
        }
    }

    public void addShape(BasicShape basicShape){
        basicShape.shift(parent.getShift());
        basicShapes.add(basicShape);
    }

    /// don`t use outside CellMap!!!!!!!!
    public ArrayList<BasicShape> getBasicShapes(){
        return basicShapes;
    }
}
