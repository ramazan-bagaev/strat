package Generation;

import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.FieldMap;
import Utils.Geometry.Index;

public class OceanGenerator {

    public static void generate(FieldMap map, Index mapSize){
        Index iter = new Index(0, 0);
        for(int i = 0; i < mapSize.y; i++){
            for(int j = 0; j < mapSize.x; j++){
                Index pos = new Index(j, i);
                Field field = new Field(pos, map.getRandom(), map, map.getGameEngine().getTime(), Ground.GroundType.Water);
                map.addField(pos, field);
            }
        }
    }
}
