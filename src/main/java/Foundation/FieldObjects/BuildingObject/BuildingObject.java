package Foundation.FieldObjects.BuildingObject;

import Foundation.Color;
import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.TransportObjects.TransportNetNodeObject;
import Foundation.FieldObjects.TransportObjects.TransportNetObject;
import Foundation.GameWindowHelper.Modes.InFieldMode.LinkedToBuildingRoadCoveringMode;
import Foundation.GameWindowHelper.Modes.Mode;
import WindowElements.GameWindowElements.GameWindowHelperElement;
import Utils.Geometry.Index;

import java.util.ArrayList;

public abstract class BuildingObject extends FieldObject implements BuildingObjectType{

    private ArrayList<TransportNetObject> transportNetObjects;

    private TransportNetNodeObject transportNetEntranceNode;

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

    public void setTransportNetEntranceNode(TransportNetNodeObject transportNetEntranceNode){
        this.transportNetEntranceNode = transportNetEntranceNode;
    }

    public TransportNetNodeObject getTransportNetEntranceNode() {
        return transportNetEntranceNode;
    }

    public ArrayList<TransportNetObject> getTransportNetObjects() {
        return transportNetObjects;
    }

    @Override
    public boolean isBuilding(){
        return true;
    }

    @Override
    public boolean isLivingBuilding(){
        return false;
    }


    @Override
    public Mode getModeOnClick(GameWindowHelperElement gameWindowHelperElement){
        LinkedToBuildingRoadCoveringMode mode = new LinkedToBuildingRoadCoveringMode(gameWindowHelperElement,
                this, new Color(Color.Type.Yellow2, 0.5f));
        return mode;
    }
}
