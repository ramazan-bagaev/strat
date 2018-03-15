package Windows;

import Foundation.Elements.Army;
import Foundation.Coord;
import Foundation.People;
import Foundation.Windows;
import WindowElements.MonitoredBroadcastLabel;

public class ArmyInfoWindow extends ClosableWindow {

    public Army army;

    public ArmyInfoWindow(Army army, Windows parent) {
        super(new Coord(0, 700), new Coord(300, 300), parent);

        this.army = army;

        setShapes();
    }

    public void setShapes(){

        People people = army.getPeople();

        MonitoredBroadcastLabel amount = new MonitoredBroadcastLabel(new Coord(10, 10).add(getPos()), new Coord(250, 20), "Army size:",
                people, "amount", this);

        addWindowElement(amount);

    }

    public void setArmy(Army army){
        this.army = army;
        setShapes();
    }
}
