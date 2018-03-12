package Foundation.GameWindowHelper;

import Foundation.*;

import java.util.ArrayList;
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
        addCityNames();
    }


    public HelperField getFieldByIndex(Coord index){
        return map.getOrDefault(index, null);
    }

    public ArrayList<BasicShape> getShapes(Coord index, Coord number){
        ArrayList<BasicShape> result = new ArrayList<>();
        int c = 0;
        for (int i = index.x; i <= number.x + index.x; i++){
            for (int j = index.y; j <= number.y + index.y; j++){
                HelperField field = getFieldByIndex(new Coord(i, j));
                if (field == null) continue;
                c++;
                result.addAll(field.getShapes());
            }
        }
        return result;
    }

    public void addCityNames(){
        for(Field field: fieldMap.getValues()){
            if (field.getCity() == null) continue;
            HelperField helperField = new HelperField(field, this);
            CityInfoHelper cityInfoHelper = new CityInfoHelper(field.getCity(), helperField);
            helperField.setCityInfoHelper(cityInfoHelper);
            Coord pos = field.getFieldMapPos();
            map.put(pos, helperField);
        }
    }

    public GameWindowHelperElement getParent() {
        return parent;
    }

    public int getFieldSize() {
        return fieldSize;
    }
}
