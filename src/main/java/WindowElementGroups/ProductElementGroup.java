package WindowElementGroups;

import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Window;
import Utils.Geometry.Coord;
import WindowElements.ScrollElements.ProductScrollRow;
import WindowElements.ScrollElements.ScrollableRow;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductElementGroup extends ScrollableGroup{

    protected ProductStore store;

    public ProductElementGroup(Coord pos, Coord size, ProductStore store, Window parent) {
        super(pos, size, parent);
        this.store = store;
        initRowElements();
    }

    public ProductElementGroup(Coord pos, Coord size, Coord scrollWindowSize, ProductStore store, Window parent) {
        super(pos, size, scrollWindowSize, parent);
        this.store = store;
        initRowElements();
    }

    public void initRowElements(){
        initCameraConfiguration();
        scrollableRows.clear();
        for(Product product: store.getProducts()){
            ProductScrollRow productScrollRow = new ProductScrollRow(product, new Coord(getSize().x, 20), this);
            addScrollableRow(productScrollRow);
        }
    }

    public void checkIntegrity(){
        for(ScrollableRow row: scrollableRows){
            ProductScrollRow prodRow = (ProductScrollRow)row;
            if (!store.getProducts().contains(prodRow.getProduct())){
                prodRow.closeSubscribtions();
            }
        }
    }

}
