package Foundation.FieldObjects.NaturalObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Products.RawMaterials.ElementConcentration;
import Windows.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class StoneObject extends NaturalObject {

    private MineralConcentration mineralConcentration;

    public StoneObject(Field parent, MineralConcentration mineralConcentration, Index cellPos, Index size) {
        super(parent, cellPos, size);
        this.mineralConcentration = mineralConcentration;
        setShapes();
    }

    public int getStoneSize(){
        return size.x * size.y;
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

    public MineralConcentration getMineralConcentration() {
        return mineralConcentration;
    }
}
