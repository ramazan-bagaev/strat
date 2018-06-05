package WindowElementGroups;

import Foundation.Recources.Product;
import Foundation.Recources.ProductStore;
import Foundation.Recources.Resource;
import Foundation.Recources.ResourceStore;
import Foundation.Window;
import Utils.Coord;
import WindowElements.ScrollElements.ProductScrollRow;
import WindowElements.ScrollElements.ResourceScrollRow;

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

}
