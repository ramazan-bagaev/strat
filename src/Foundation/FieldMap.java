package Foundation;

import Foundation.Coord;
import Foundation.Field;

import java.util.Collection;
import java.util.HashMap;

public class FieldMap {

    private HashMap<Coord, Field> map;

    public FieldMap(){
        map = new HashMap<>();
    }

    public Field getField(Coord coord){
        return map.getOrDefault(coord, null);
    }

    public void addField(Coord coord, Field field){
        map.put(coord, field);
    }

    public Collection<Field> getValues() {
        return map.values();
    }

    public void setMap(HashMap<Coord, Field> map){
        this.map = map;
    }
}
