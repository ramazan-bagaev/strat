package WindowElementGroups;

import Foundation.Economics.TradeShop;
import Foundation.FieldObjects.BuildingObject.MarketObject;
import Utils.Geometry.Coord;
import WindowElements.ScrollElements.TradeShopScrollRow;
import Windows.Window;

public class TradeShopElementGroup extends ScrollableGroup {

    private MarketObject marketObject;

    public TradeShopElementGroup(Coord pos, Coord size, MarketObject marketObject, Window parent) {
        super(pos, size, parent);
        this.marketObject = marketObject;
        initRowElements();
    }

    private void initRowElements(){
        initCameraConfiguration();
        scrollableRows.clear();
        for(TradeShop tradeShop: marketObject.getTradeShops()){
            TradeShopScrollRow tradeShopScrollRow = new TradeShopScrollRow(new Coord(getSize().x, 20), tradeShop, this);
            addScrollableRow(tradeShopScrollRow);
        }
    }
}
