package Foundation.FieldObjects.NaturalObjects;

import Foundation.Field;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class WaterFlowObject extends WaterObject{

    private ArrayList<WaterFlowObject> ins;
    private WaterFlowObject out;

    public WaterFlowObject(Field parent, Index cellPos, Index size){
        super(parent, cellPos, size);
        this.ins = new ArrayList<>();
    }

    public void setOut(WaterFlowObject out){
        this.out = out;
    }

    public void addIn(WaterFlowObject in){
        ins.add(in);
    }
}
