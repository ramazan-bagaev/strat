package Windows;

import Foundation.Coord;
import Foundation.Production;
import Foundation.Window;
import Foundation.Windows;

public class CityProductionWindow extends ClosableWindow{

    private Production production;

    public CityProductionWindow(Production production, Windows parent){
        super(new Coord(700, 700), new Coord(200, 200), parent);
        this.production = production;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();
        MonitoredBroadcastLabel workersAmount = new MonitoredBroadcastLabel(getPos().add(new Coord(20, 20)), new Coord(180, 20), "workers amount", production,
                "workerAmount", this);
        addWindowElement(workersAmount);
    }

    public void setProduction(Production production){
        this.production = production;
        setShapes();
    }

}
