package Windows;

import Foundation.Frame;
import Foundation.Products.ProductStore;
import Utils.Geometry.Coord;
import WindowElementGroups.ProductElementGroup;

public class ProductStoreWindow extends ClosableWindow {

    private ProductStore store;

    public ProductStoreWindow(ProductStore store, Frame parent) {
        super(new Coord(100, 600), new Coord(400, 150), parent);
        this.store = store;
        setShapes();
    }

    public void setShapes() {

        removeWindowElements();
        addCloseButton();

        ProductElementGroup resourceElementGroup = new ProductElementGroup(new Coord(10, 30),
                new Coord(390, 90), store, this);
        // scrollableGroup.setScrollableElements(elements);
        addWindowElementGroup(resourceElementGroup);
    }
}
