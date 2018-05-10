package Foundation.FieldObjects;

import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;

public class FieldObjects {

    private Field parent;
    private ArrayList<FieldObject> fieldObjects;

    public FieldObjects(Field parent){
        this.parent = parent;
    }

    public boolean isFree(Index pos, Index size){
        for(FieldObject object: fieldObjects){
            if (object.isIntersects(pos, size)) return false;
        }
        return true;
    }

    public void addFieldObject(FieldObject fieldObject){
        if (isFree(fieldObject.cellPos, fieldObject.size)){
            fieldObjects.add(fieldObject);
        }
    }
}
