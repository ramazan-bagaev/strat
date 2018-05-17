package Foundation.FieldObjects;

import Foundation.Field;
import Foundation.TransportInfrostructure.TransportNetElement;
import Utils.Index;

import java.util.ArrayList;

public abstract class TransportNetObject extends FieldObject implements TransportNetElement {


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
}
