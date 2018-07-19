package Windows.FieldObjectWindow;

import Foundation.Button;
import Foundation.FieldObjects.BuildingObject.LivingBuildingObject;
import Foundation.Frame;
import Utils.Geometry.Coord;
import WindowElements.MonitoredBroadcastLabel;
import Windows.ClosableWindow;
import Windows.PeopleInfoWindow;
import Windows.ProductStoreWindow;

public class LivingBuildingInfoWindow extends ClosableWindow {

    private LivingBuildingObject livingBuildingObject;

    public LivingBuildingInfoWindow(LivingBuildingObject livingBuildingObject, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.livingBuildingObject = livingBuildingObject;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        MonitoredBroadcastLabel people = new MonitoredBroadcastLabel(new Coord(20, 20), new Coord(280, 20), "people: ",
                livingBuildingObject.getPeople(), "amount", this);
        addWindowElement(people);

        Button button = new Button(new Coord(0, 20), new Coord(20, 20),"p", this){

            @Override
            public void click(Coord point){
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("people group window", new PeopleInfoWindow(livingBuildingObject.getPeople(), frame));
            }
        };

        addWindowElement(button);

        button = new Button(new Coord(0, 50), new Coord(100, 20), "store", this){

            @Override
            public void click(Coord point){
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ProductStoreWindow(livingBuildingObject.getStore(), frame));
            }
        };

        addWindowElement(button);

    }
}
