package Windows.FieldObjectWindow;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Frame;
import Utils.Geometry.Coord;
import WindowElements.Button;
import WindowElements.MonitoredBroadcastLabel;
import Windows.ClosableWindow;
import Windows.PeopleInfoWindow;
import Windows.ProductContainerWindow;
import Windows.TradeShopsWindow;

public class MarketInfoWindow extends ClosableWindow {

    private MarketObject marketObject;

    public MarketInfoWindow(MarketObject marketObject, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.marketObject = marketObject;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        MonitoredBroadcastLabel shops = new MonitoredBroadcastLabel(new Coord(20, 20), new Coord(280, 20), "shops: ",
                marketObject, "shopsAmount", this){

            @Override
            public void click(Coord point){
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("middleRight", new TradeShopsWindow(marketObject, frame));
            }
        };
        addWindowElement(shops);
    }
}
