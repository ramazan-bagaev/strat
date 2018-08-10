package Windows;

import Foundation.*;
import Foundation.Person.Society;
import Utils.Geometry.Coord;
import WindowElementGroups.PeopleInfoElementGroup;
import WindowElementGroups.ScrollableGroup;
import WindowElements.CloseButton;
import WindowElements.Label;
import WindowElements.MonitoredBroadcastLabel;

public class PopulationInfoWindow extends ClosableWindow{

    private Society society;

    public PopulationInfoWindow(Society society, Frame parent) {
        super(new Coord(300, 400), new Coord(500, 500), parent);

        setSociety(society);
    }

    public void setSociety(Society society) {
        this.society = society;

        removeWindowElements();
        CloseButton closeButton = new CloseButton(new Coord(getSize().x - 15, 0), new Coord(15, 15), this);
        addWindowElement(closeButton);

        MonitoredBroadcastLabel populationAmount = new MonitoredBroadcastLabel(new Coord(10, 10), new Coord(200, 10),
                "Overall population:", society, "amount", this);
        addWindowElement(populationAmount);

        MonitoredBroadcastLabel workingAmount = new MonitoredBroadcastLabel(new Coord(10, 30), new Coord(200, 10),
                "Working population:", society, "workingAmount", this);
        addWindowElement(workingAmount);

        MonitoredBroadcastLabel manAmount = new MonitoredBroadcastLabel(new Coord(10, 50), new Coord(200, 10),
                "Man populaion:", society, "manAmount", this);
        addWindowElement(manAmount);

        /*ArrayList<ScrollableElement> populationMonitoredLabels = new ArrayList<>();
        int index = 0;
        int scrollEl = 20;
        for (Person person: society.getPersonArray()){
            PersonScrolledElement personScrolledElement = new PersonScrolledElement(new Coord(10, index * scrollEl),
                    new Coord(390, 10), person, this);
            index++;
            populationMonitoredLabels.add(personScrolledElement);
        }

        ScrollableGroup scrollableGroup = new ScrollableGroup(new Coord(10, 70), new Coord(390, 300), populationMonitoredLabels, this);*/


        ScrollableGroup scrollableGroup = new PeopleInfoElementGroup(new Coord(10, 100), new Coord(390, 300), society.getPeople(), this);
        addWindowElementGroup(scrollableGroup);
        double x = scrollableGroup.getScrollWindowSize().x;
        Label label = new Label(new Coord(15, 70), new Coord(x/2 - 5, 20), "name", this);
        addWindowElement(label);
        label = new Label(new Coord(x/2+20, 70), new Coord(x/2 -5, 20), "kasta", this);
        addWindowElement(label);

    }
}
