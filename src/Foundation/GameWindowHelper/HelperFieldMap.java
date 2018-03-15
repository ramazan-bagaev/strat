package Foundation.GameWindowHelper;

import Foundation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class HelperFieldMap {

    private HashMap<Coord, HelperField> map;
    private int fieldSize;



    private FieldMap fieldMap;
    private GameWindowHelperElement parent;

    public HelperFieldMap(FieldMap fieldMap, GameWindowHelperElement parent){
        map = new HashMap<>();
        this.fieldSize = fieldMap.getFieldSize();
        this.fieldMap = fieldMap;
        this.parent = parent;
    }


    public HelperField getFieldByIndex(Coord index){
        return map.getOrDefault(index, null);
    }

    public HelperField getFieldByPos(Coord pos){
        return map.getOrDefault(new Coord(pos.x/fieldSize, pos.y/fieldSize), null);
    }

    public ArrayList<BasicShape> getShapes(Coord index, Coord number){
        ArrayList<BasicShape> result = new ArrayList<>();
        for (int i = index.x; i <= number.x + index.x; i++){
            for (int j = index.y; j <= number.y + index.y; j++){
                HelperField field = getFieldByIndex(new Coord(i, j));
                if (field == null) continue;
                result.addAll(field.getShapes());
            }
        }
        return result;
    }


    public GameWindowHelperElement getParent() {
        return parent;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public FieldMap getFieldMap() {
        return fieldMap;
    }

    public void addByIndex(Coord index, HelperField helperField){
        map.put(index, helperField);
    }

    public void addByPos(Coord pos, HelperField helperField){
        map.put(new Coord(pos.x/fieldSize, pos.y/fieldSize), helperField);
    }

    public Collection<HelperField> getValues(){
        return map.values();
    }
}
