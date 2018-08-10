package Windows;

import Foundation.Frame;
import Foundation.Products.ProductStore;
import Utils.Geometry.Coord;
import Utils.Subscription;
import WindowElementGroups.ProductElementGroup;

public class ProductStoreWindow extends ClosableWindow {

    private ProductStore store;

    private ProductElementGroup productElementGroup;
    private Subscription productStoreSubscribtion;

    public ProductStoreWindow(ProductStore store, Frame parent) {
        super(new Coord(100, 600), new Coord(400, 150), parent);
        this.store = store;
        productStoreSubscribtion = new Subscription() {
            @Override
            public void changed() {
                onChanged();
            }
        };
        store.subscribe("goods", productStoreSubscribtion);
        setShapes();
    }

    private void onChanged(){
        productElementGroup.checkIntegrity();
        setShapes();
    }

    public void setShapes() {

        removeWindowElements();
        addCloseButton();

        ProductElementGroup productElementGroup = new ProductElementGroup(new Coord(10, 30),
                new Coord(390, 90), store, this);
        // scrollableGroup.setScrollableElements(elements);
        this.productElementGroup = productElementGroup;
        addWindowElementGroup(productElementGroup);
    }
}
