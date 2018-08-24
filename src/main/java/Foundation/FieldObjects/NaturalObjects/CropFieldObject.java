package Foundation.FieldObjects.NaturalObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Flora.CropField;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import Windows.Window;

public class CropFieldObject extends NaturalObject{

    private CropField cropField;

    public CropFieldObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        this.cropField = new CropField(size.x * size.y);
        setShapes();
    }

    @Override
    public boolean isCropFieldObject(){
        return true;
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = parent.getCellSize();
        double shift = 0.1;

        RectangleShape rectangleShape = new RectangleShape(new Coord(shift, shift), new Coord(cellSize * size.x - 2*shift,
                cellSize * size.y - 2*shift), new Color(Color.Type.Yellow2), false, true);
        addBasicShape(rectangleShape);
    }

    @Override
    public Window getInfoWindow() {
        return null;
    }

    public CropField getCropField() {
        return cropField;
    }
}
