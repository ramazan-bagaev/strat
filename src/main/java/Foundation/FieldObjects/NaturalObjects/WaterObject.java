package Foundation.FieldObjects.NaturalObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.Window;
import Utils.Coord;
import Utils.Index;

public class WaterObject extends FieldObject{

    public WaterObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = parent.getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize * size.x, cellSize * size.y),
                new Color(Color.Type.Blue), false, true);
        addBasicShape(rectangleShape);
    }

    @Override
    public Window getInfoWindow() {
        return null;
    }
}
