package Utils.Boundary;

import Foundation.FieldMap;
import Utils.Geometry.Index;

public class MapBoundary implements Boundary {

    private FieldMap map;

    public MapBoundary(FieldMap map){
        this.map = map;
    }

    @Override
    public boolean contains(Index index) {
        return (!(map.getFieldByIndex(index) == null));
    }
}
