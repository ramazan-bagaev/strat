package Foundation.FieldObjects.BuildingObject;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Products.ProductStore;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import Windows.FieldObjectWindow.MillInfoWindow;
import Windows.Window;

public class MillObject extends BuildingObject {

    private ProductStore store;

    public MillObject(Field parent, Index cellPos) {
        super(parent, cellPos, new Index(2, 1));
        this.store = new ProductStore(this);
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = parent.getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize * size.x, cellSize * size.y),
                new Color(Color.Type.Black), false, true);
        addBasicShape(rectangleShape);
    }

    @Override
    public Window getInfoWindow() {
        return new MillInfoWindow(this, getParent().getMap().getGameEngine().getGameWindowElement().getParent().getParent());
    }

    public ProductStore getStore() {
        return store;
    }
}
