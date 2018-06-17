package Generation;

import Foundation.FieldMap;
import Utils.Geometry.Index;

public abstract class FieldMapGenerator {

    protected FieldMap map;
    protected Index size;

    public void generate(FieldMap map, Index size){
        init(map, size);
        startGeneration();
    }

    public abstract void startGeneration();

    public void init(FieldMap map, Index size){
        this.map = map;
        this.size = size;
    }
}
