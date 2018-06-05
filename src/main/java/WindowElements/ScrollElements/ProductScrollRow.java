package WindowElements.ScrollElements;

import Foundation.Recources.Product;
import Foundation.WindowElement;
import Utils.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;

public class ProductScrollRow extends ScrollableRow {

    private Product product;

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
    }


    @Override
    public void run(){
        for(WindowElement windowElement: rowElements){
            //windowElement.run();
        }
    }
}
