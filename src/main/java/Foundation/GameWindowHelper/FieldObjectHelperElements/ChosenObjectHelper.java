package Foundation.GameWindowHelper.FieldObjectHelperElements;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.HelperField;
import Utils.Coord;
import Utils.Index;

public class ChosenObjectHelper extends ObjectHelperElement{

    public ChosenObjectHelper(FieldObject fieldObject, HelperField helperField) {
        super(fieldObject, helperField);
        setShapes();
    }

    public void setShapes(){
        clearShapes();
        double cellSize = fieldObject.getParent().getCellSize();
        Index size = fieldObject.getSize();
        //Index pos = fieldObject.getCellPos();

        addShape(new RectangleShape(new Coord(), new Coord(size.x * cellSize, size.y * cellSize),
                new Color(Color.Type.Yellow), true, false));
    }
}
