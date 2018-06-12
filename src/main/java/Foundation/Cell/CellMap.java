package Foundation.Cell;

import Utils.Geometry.Index;
import Foundation.Field;

import java.util.Collection;
import java.util.HashMap;

public class CellMap {

    private HashMap<Index, Cell> map;
    private int cellSize;

    private Field parent;

    public CellMap(Field parent){
        map = new HashMap<>();
        this.parent = parent;
    }

    public Cell getCellByIndex(Index index){
        return map.getOrDefault(index, null);
    }

    public void addCell(Cell cell, Index index){
        map.put(index, cell);
    }

    public Collection<Cell> getValues(){
        return map.values();
    }

    public Field getParent() {
        return parent;
    }

    public int getCellSize() {
        return cellSize;
    }
}
