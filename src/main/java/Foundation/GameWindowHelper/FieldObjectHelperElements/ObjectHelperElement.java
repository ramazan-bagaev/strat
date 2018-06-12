package Foundation.GameWindowHelper.FieldObjectHelperElements;

import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.HelperField;
import Utils.Geometry.Coord;

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
