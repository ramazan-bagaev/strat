package Foundation.GameWindowHelper.FieldObjectHelperElements;

import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.HelperField;
import Utils.Coord;
import Utils.Index;

public class ObjectHelperElement extends InFieldHelperElement {

    protected FieldObject fieldObject;

    public ObjectHelperElement(FieldObject fieldObject, HelperField helperField) {
        super(helperField);
        this.fieldObject = fieldObject;
    }

    @Override
    public Coord getShift(){
        return fieldObject.getShift();
    }
}
