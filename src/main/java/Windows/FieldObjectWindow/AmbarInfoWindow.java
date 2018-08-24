package Windows.FieldObjectWindow;

import Foundation.FieldObjects.BuildingObject.AmbarObject;
import Foundation.Frame;
import Utils.Geometry.Coord;
import WindowElements.Button;
import WindowElements.MonitoredBroadcastLabel;
import Windows.ClosableWindow;
import Windows.ProductContainerWindow;
import Windows.TradeShopsWindow;

public class AmbarInfoWindow extends ClosableWindow {

    private AmbarObject ambarObject;

    public AmbarInfoWindow(Frame parent, AmbarObject ambarObject) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.ambarObject = ambarObject;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        Button button = new Button(new Coord(0, 50), new Coord(100, 20), "store", this){

            @Override
            public void click(Coord point){
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ProductContainerWindow(ambarObject.getStore(), frame));
            }
        };

        addWindowElement(button);

    }
}
