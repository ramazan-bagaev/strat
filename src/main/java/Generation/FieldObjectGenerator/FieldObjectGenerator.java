package Generation.FieldObjectGenerator;

import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.FieldMap;
import Generation.FieldMapGenerator;
import Utils.Geometry.Index;

public class FieldObjectGenerator extends FieldMapGenerator{

    private Index iter;

    @Override
    public void startGeneration() {

    }

    private void fillFieldsWithObjects(){
        for(int y = 0; y < size.y; y++){
            iter.y = y;
            for(int x = 0; x < size.x; x++){
                iter.x = x;
                Field field = map.getFieldByIndex(iter);
                fillFieldWithObjects(field);
            }
        }
    }

    private void fillFieldWithObjects(Field field){
        if (field.getGroundType() == Ground.GroundType.Rock) ...;
        if (field.getRiver() != null) ...;
        if (field.getTree() != null) ...;
        if (field.getCity() != null) ...;
    }

    @Override
    public void init(FieldMap map, Index size){
        this.map = map;
        this.size = size;
        this.iter = new Index(0, 0);
    }
}
