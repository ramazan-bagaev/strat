package Foundation.FieldObjects;

import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;

public abstract class BuildingObject extends FieldObject{

    private ArrayList<FieldObject> transportNetObjects;

    public BuildingObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        transportNetObjects = new ArrayList<>();
    }

    public void addTranportNetObject(TransportNetObject transportNetObject){
        transportNetObjects.add(transportNetObject);
    }

    @Override
    public boolean isBuilding(){
        return true;
    }
}
