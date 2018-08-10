package WindowElements.ScrollElements;

import Foundation.Products.Product;
import Foundation.WindowElement;
import Utils.Geometry.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;

public class ProductScrollRow extends ScrollableRow {

    private Product product;

    private MonitoredBroadcastLabel amountLabel;

    public ProductScrollRow(Product product, Coord size, ScrollableGroup parent) {
        super(size, parent);
        this.product = product;
        initRow();
    }

    public void initRow(){
        StaticBroadcastLabel type = new StaticBroadcastLabel(new Coord(5, 0), new Coord(size.x/3 - 5, size.y),
                product, "type", parent, parent.getParent());
        rowElements.add(type);

        StaticBroadcastLabel name = new StaticBroadcastLabel(new Coord(size.x/3 + 5, 0), new Coord(size.x/3 - 5, size.y),
                product, "name", parent, parent.getParent());
        rowElements.add(name);

        MonitoredBroadcastLabel amount = new MonitoredBroadcastLabel(new Coord(2*size.x/3 + 5, 0), new Coord(size.x/3 - 5, size.y),
                product, "amount", parent, parent.getParent());
        rowElements.add(amount);
        this.amountLabel = amount;
    }

    public void closeSubscribtions(){
        amountLabel.unsubscribe();
    }

    public Product getProduct() {
        return product;
    }
}
