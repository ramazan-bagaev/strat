package Foundation.FieldObjects.BuildingObject;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Products.ProductStore;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import Windows.FieldObjectWindow.MineInfoWindow;
import Windows.Window;

public class MineObject extends BuildingObject {

    private ProductStore store;

    public MineObject(Field parent, Index cellPos) {
        super(parent, cellPos, new Index(1, 2));
        this.store = new ProductStore(this);
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = parent.getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize * size.x, cellSize * size.y),
                new Color(Color.Type.Red), false, true);
        addBasicShape(rectangleShape);
    }

    @Override
    public Window getInfoWindow() {
        return new MineInfoWindow(this, getParent().getMap().getGameEngine().getGameWindowElement().getParent().getParent());
    }

    public ProductStore getStore() {
        return store;
    }
}
