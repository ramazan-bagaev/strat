package Generation.FieldObjectGenerator;

import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.NaturalObjects.NaturalObject;
import Foundation.FieldObjects.NaturalObjects.WaterFlowObject;
import Utils.FieldObjectPathFinder;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class RiverFieldObjectsGenerator {

    private Field field;
    private Index start;
    private Index finish;
    private FieldObjectPathFinder pathFinder;
    private WaterFlowObjectsFromPath waterFlowObjectsFromPath;

    public RiverFieldObjectsGenerator(){
        waterFlowObjectsFromPath = new WaterFlowObjectsFromPath();
    }

    public void generate(Field field, Index start, Index finish){
        this.field = field;
        this.start = start;
        this.finish = finish;
        initPathFinder();
        startGeneration();
    }

    private void startGeneration(){
        initPathFinder();
        ArrayList<Index> path = pathFinder.getPath(start);
        ArrayList<WaterFlowObject> waterFlowObjects = waterFlowObjectsFromPath.getWaterFlowObjects(path, field);
        if (waterFlowObjects == null) return;
        for(int i = 0; i < waterFlowObjects.size(); i++){
            WaterFlowObject flowObject = waterFlowObjects.get(i);
            if (i != 0) flowObject.addIn(waterFlowObjects.get(i - 1));
            if (i != waterFlowObjects.size() - 1) flowObject.setOut(waterFlowObjects.get(i + 1));
            field.addFieldObject(flowObject);
        }
    }

    private void initPathFinder(){
        if (finish != null){
            pathFinder = new FieldObjectPathFinder(field) {
                @Override
                public boolean isFinish(Index pos) {
                    return pos.equals(finish);
                }
            };
        }
        else {
            pathFinder = new FieldObjectPathFinder(field) {
                @Override
                public boolean isFinish(Index pos) {
                    FieldObject fieldObject = fieldObjects.getFieldObject(pos);
                    if (fieldObject.isNaturalObject()){
                        NaturalObject naturalObject = (NaturalObject)fieldObject;
                        if (naturalObject.isWaterObject()){
                            return true;
                        }
                    }
                    return false;
                }
            };
        }
    }

}
