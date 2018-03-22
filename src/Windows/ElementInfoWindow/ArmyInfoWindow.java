package Windows.ElementInfoWindow;

import Foundation.Frame;
import Foundation.Runnable.Army;
import Utils.Coord;
import Foundation.Person.People;
import WindowElements.MonitoredBroadcastLabel;
import Windows.ClosableWindow;

public class ArmyInfoWindow extends ClosableWindow {

    public Army army;

    public ArmyInfoWindow(Army army, Frame parent) {
        super(new Coord(0, 700), new Coord(300, 300), parent);

        this.army = army;

        setShapes();
    }

    public void setShapes(){

        People people = army.getPeople();

        MonitoredBroadcastLabel amount = new MonitoredBroadcastLabel(new Coord(10, 10), new Coord(250, 20), "Army size:",
                people, "amount", this);

        addWindowElement(amount);

    }

    public void setArmy(Army army){
        this.army = army;
        setShapes();
    }
}
