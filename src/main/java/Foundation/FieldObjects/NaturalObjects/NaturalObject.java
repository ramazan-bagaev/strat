package Foundation.FieldObjects.NaturalObjects;

import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Utils.Geometry.Index;

public abstract class NaturalObject extends FieldObject implements NaturalObjectType {

    public NaturalObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
    }

    @Override
    public boolean isWaterObject(){
        return false;
    }

    @Override
    public boolean isForestObject(){
        return false;
    }

    @Override
    public boolean isStoneObject(){
        return false;
    }

    @Override
    public boolean isCropFieldObject(){
        return false;
    }

    @Override
    public boolean isNaturalObject(){
        return true;
    }
}
