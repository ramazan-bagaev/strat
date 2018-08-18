package Windows;

import Foundation.Frame;
import Foundation.Products.ProductContainer;
import Foundation.Products.ProductStore;
import Utils.Geometry.Coord;
import Utils.Subscription;
import WindowElementGroups.ProductElementGroup;

public class ProductContainerWindow extends ClosableWindow {

    private ProductContainer container;

    private ProductElementGroup productElementGroup;
    private Subscription productStoreSubscribtion;

    public ProductContainerWindow(ProductContainer conainer, Frame parent) {
        super(new Coord(100, 600), new Coord(400, 150), parent);
        this.container = conainer;
        productStoreSubscribtion = new Subscription() {
            @Override
            public void changed() {
                onChanged();
            }
        };
        conainer.subscribe("goods", productStoreSubscribtion);
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
                new Coord(390, 90), container, this);
        // scrollableGroup.setScrollableElements(elements);
        this.productElementGroup = productElementGroup;
        addWindowElementGroup(productElementGroup);
    }
}
