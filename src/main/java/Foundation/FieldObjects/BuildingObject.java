package Foundation.FieldObjects;

import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;

public abstract class BuildingObject extends FieldObject{

    private ArrayList<TransportNetObject> transportNetObjects;

    public BuildingObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        transportNetObjects = new ArrayList<>();
    }

    public void addTransportNetObject(TransportNetObject transportNetObject){
        transportNetObjects.add(transportNetObject);
    }

    public void removeTransportNetObject(TransportNetObject transportNetObject){
        transportNetObjects.remove(transportNetObject);
    }

    public ArrayList<TransportNetObject> getTransportNetObjects() {
        return transportNetObjects;
    }

    @Override
    public boolean isBuilding(){
        return true;
    }
}
