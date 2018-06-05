package Foundation.GameWindowHelper.FieldObjectHelperElements;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.HelperField;
import Utils.Coord;
import Utils.Index;

public class CoveringObjectHelper extends ObjectHelperElement {

    private Color color;

    public CoveringObjectHelper(FieldObject fieldObject, HelperField helperField, Color color) {
        super(fieldObject, helperField);
        this.color = color;
        setShapes();
    }

    public void setShapes(){
        clearShapes();
        double cellSize = fieldObject.getParent().getCellSize();
        Index size = fieldObject.getSize();
        //Index pos = fieldObject.getCellPos();

        addShape(new RectangleShape(new Coord(), new Coord(size.x * cellSize, size.y * cellSize),
                color, true, true));
    }
}
