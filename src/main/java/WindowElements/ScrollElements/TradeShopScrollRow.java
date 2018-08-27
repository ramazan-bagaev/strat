package WindowElements.ScrollElements;

import Foundation.Economics.TradeShop;
import Utils.Geometry.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.Label;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;

public class TradeShopScrollRow extends ScrollableRow {

    private TradeShop tradeShop;

    public TradeShopScrollRow(Coord size, TradeShop tradeShop, ScrollableGroup parent) {
        super(size, parent);
        this.tradeShop = tradeShop;
        initRow();
    }

    private void initRow(){

        Label name = new Label(new Coord(5, 0), new Coord(size.x/2 - 10, size.y),
                tradeShop.getTrader().getName(), parent);
        rowElements.add(name);

        MonitoredBroadcastLabel amount = new MonitoredBroadcastLabel(new Coord(size.x/2 + 5, 0), new Coord(size.x/2 - 10, size.y),
                tradeShop, "amount", parent);
        rowElements.add(amount);

    }
}
