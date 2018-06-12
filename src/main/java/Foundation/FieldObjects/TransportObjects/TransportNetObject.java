package Foundation.FieldObjects.TransportObjects;

import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.TransportInfrostructure.TransportNetElement;
import Utils.Geometry.Index;

public abstract class TransportNetObject extends FieldObject implements TransportNetElement, RoadType {


    public TransportNetObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
    }


    @Override
    public boolean isTransportNetObject(){
        return true;
    }

    @Override
    public boolean isNode(){
        return false;
    }

    @Override
    public boolean isEdge(){
        return false;
    }

    @Override
    public boolean isPavement(){
        return false;
    }

    @Override
    public boolean isPriming(){
        return false;
    }
}
