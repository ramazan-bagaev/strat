package Windows;

import Foundation.Coord;
import Foundation.Elements.Ecosystem;
import Foundation.Fauna.Fauna;
import Foundation.Flora.Flora;
import Foundation.Label;
import Foundation.Windows;
import WindowElements.MonitoredBroadcastLabel;

public class EcosystemInfoWindow extends ClosableWindow{

    private Ecosystem ecosystem;

    public EcosystemInfoWindow(Ecosystem ecosystem, Windows parent) {
        super(new Coord(300, 600), new Coord(250, 150), parent);

        this.ecosystem = ecosystem;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        Label floraLabel = new Label(new Coord(100, 10).add(getPos()), new Coord(50, 20), "flora", this);
        addWindowElement(floraLabel);

        Flora flora = ecosystem.getFlora();

        MonitoredBroadcastLabel treeAmount = new MonitoredBroadcastLabel(new Coord(10, 30).add(getPos()), new Coord(230, 20), "tree:",
                flora, "tree", this);
        addWindowElement(treeAmount);

        MonitoredBroadcastLabel wildAmounts = new MonitoredBroadcastLabel(new Coord(10, 50).add(getPos()), new Coord(230, 20), "wildPlants:",
                flora, "wildPlants", this);
        addWindowElement(wildAmounts);

        MonitoredBroadcastLabel cultivatedAmounts = new MonitoredBroadcastLabel(new Coord(10, 70).add(getPos()), new Coord(230, 20),
                "cultivated:", flora, "cultivatedPlants", this);
        addWindowElement(cultivatedAmounts);


        Label faunaLabel = new Label(new Coord(100, 90).add(getPos()), new Coord(50, 20), "fauna", this);
        addWindowElement(faunaLabel);

        Fauna fauna = ecosystem.getFauna();


    }

    public void setEcosystem(Ecosystem ecosystem){
        this.ecosystem = ecosystem;
        setElements();
    }
}
