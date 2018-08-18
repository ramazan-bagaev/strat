package Windows;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Frame;
import Utils.Content;
import Utils.Geometry.Coord;
import Utils.Subscription;
import WindowElementGroups.TradeShopElementGroup;

public class TradeShopsWindow extends ClosableWindow {

    private MarketObject marketObject;

    private Subscription marketSubscription;

    public TradeShopsWindow(MarketObject marketObject, Frame parent) {
        super(new Coord(600, 400), new Coord(400, 300), parent);
        this.marketObject = marketObject;
        marketSubscription = new Subscription() {
            @Override
            public void changed() {
                onChange();
            }
        };
        marketObject.subscribe("shopsAmount", marketSubscription);
        setElements();
    }

    private void onChange(){
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        TradeShopElementGroup tradeShopElementGroup = new TradeShopElementGroup(new Coord(10, 30),
                new Coord(350, 90), marketObject, this);
        addWindowElementGroup(tradeShopElementGroup);
    }
}
