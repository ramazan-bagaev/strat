package Generation.TerrainGenerator;

import Foundation.FieldMap;
import Foundation.Field;
import Utils.Geometry.Index;

public class EmptyFieldGenerator {

    public void generate(FieldMap map, Index size){
        fillWithEmptyFields(map, size);
    }

    private void fillWithEmptyFields(FieldMap map, Index size){
        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x; x++){
                Index pos = new Index(x, y);
                Field field = new Field(pos, map.getRandom(), map, map.getGameEngine().getTime());
                map.addField(pos, field);
            }
        }
    }
}
