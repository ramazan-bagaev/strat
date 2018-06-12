package Foundation.Cell;

import Foundation.BasicShapes.BasicShape;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class Cell{

    private CellElement cellElement;

    private Index cellIndex;

    private CellMap cellMap;

    public Cell(Index cellIndex, CellMap map){
        this.cellIndex = cellIndex;
        this.cellMap = cellMap;
    }

    public Coord getShift(){
        int size = cellMap.getCellSize();
        return cellMap.getParent().getShift().add(new Coord(cellIndex.x * size, cellIndex.y * size));
    }

    public CellElement getCellElement() {
        return cellElement;
    }

    public void setCellElement(CellElement cellElement){
        this.cellElement = cellElement;
    }

    public ArrayList<BasicShape> getBasicShapes(){
        return cellElement.getBasicShapes();
    }
}
