package Foundation.FieldObjects.NaturalObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class StoneObject extends NaturalObject {

    public StoneObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = parent.getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize * size.x, cellSize * size.y),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(rectangleShape);
    }

    @Override
    public boolean isStoneObject(){
        return true;
    }

    @Override
    public Window getInfoWindow() {
        return null;
    }
}
