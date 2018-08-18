package WindowElementGroups;

import Foundation.Products.Product;
import Foundation.Products.ProductContainer;
import Windows.Window;
import Utils.Geometry.Coord;
import WindowElements.ScrollElements.ProductScrollRow;
import WindowElements.ScrollElements.ScrollableRow;

public class ProductElementGroup extends ScrollableGroup{

    protected ProductContainer container;

    public ProductElementGroup(Coord pos, Coord size, ProductContainer container, Window parent) {
        super(pos, size, parent);
        this.container = container;
        initRowElements();
    }

    public ProductElementGroup(Coord pos, Coord size, Coord scrollWindowSize, ProductContainer container, Window parent) {
        super(pos, size, scrollWindowSize, parent);
        this.container = container;
        initRowElements();
    }

    public void initRowElements(){
        initCameraConfiguration();
        scrollableRows.clear();
        for(Product product: container.getProducts()){
            ProductScrollRow productScrollRow = new ProductScrollRow(product, new Coord(getSize().x, 20), this);
            addScrollableRow(productScrollRow);
        }
    }

    public void checkIntegrity(){
        for(ScrollableRow row: scrollableRows){
            ProductScrollRow prodRow = (ProductScrollRow)row;
            if (!container.getProducts().contains(prodRow.getProduct())){
                prodRow.closeSubscriptions();
            }
        }
    }

}
