package Foundation.FieldObjects.NaturalObjects;

import Foundation.Field;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class WaterFlowObject extends WaterObject{

    private ArrayList<Index.Direction> in;
    private Index.Direction out;

    public WaterFlowObject(Field parent, Index cellPos, Index size, Index.Direction in, Index.Direction out){
        super(parent, cellPos, size);
        this.in = new ArrayList<>();
        this.in.add(in);
        this.out = out;
    }

    public WaterFlowObject(Field parent, Index cellPos, Index size, ArrayList<Index.Direction> in, Index.Direction out) {
        super(parent, cellPos, size);
        this.in = in;
        this.out = out;
    }
}
