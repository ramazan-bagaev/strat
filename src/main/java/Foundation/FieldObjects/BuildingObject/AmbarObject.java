package Foundation.FieldObjects.BuildingObject;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.FieldObjects.FieldObject;
import Foundation.Products.ProductStore;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import Windows.FieldObjectWindow.AmbarInfoWindow;
import Windows.Window;

public class AmbarObject extends BuildingObject{

    private ProductStore store;

    public AmbarObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
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
        return new AmbarInfoWindow(getParent().getMap().getGameEngine().getGameWindowElement().getParent().getParent(), this);
    }

    public ProductStore getStore() {
        return store;
    }
}
