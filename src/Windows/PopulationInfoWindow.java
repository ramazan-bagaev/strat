package Windows;

import Foundation.*;
import Foundation.Person.People;
import Utils.Coord;
import WindowElementGroups.PeopleElementGroup;
import WindowElementGroups.PeopleInfoElementGroup;
import WindowElementGroups.ScrollableGroup;
import WindowElements.CloseButton;
import WindowElements.MonitoredBroadcastLabel;

public class PopulationInfoWindow extends ClosableWindow{

    private People people;

    public PopulationInfoWindow(People people, Frame parent) {
        super(new Coord(300, 400), new Coord(500, 500), parent);

        setPeople(people);
    }

    public void setPeople(People people) {
        this.people = people;

        removeWindowElements();
        CloseButton closeButton = new CloseButton(new Coord(getSize().x - 15, 0), new Coord(15, 15), this);
        addWindowElement(closeButton);

        MonitoredBroadcastLabel populationAmount = new MonitoredBroadcastLabel(new Coord(10, 10), new Coord(200, 10),
                "Overall population:", people, "amount", this);
        addWindowElement(populationAmount);

        MonitoredBroadcastLabel workingAmount = new MonitoredBroadcastLabel(new Coord(10, 30), new Coord(200, 10),
                "Working population:", people, "workingAmount", this);
        addWindowElement(workingAmount);

        MonitoredBroadcastLabel manAmount = new MonitoredBroadcastLabel(new Coord(10, 50), new Coord(200, 10),
                "Man populaion:", people, "manAmount", this);
        addWindowElement(manAmount);

        /*ArrayList<ScrollableElement> populationMonitoredLabels = new ArrayList<>();
        int index = 0;
        int scrollEl = 20;
        for (Person person: people.getPersonArray()){
            PersonScrolledElement personScrolledElement = new PersonScrolledElement(new Coord(10, index * scrollEl),
                    new Coord(390, 10), person, this);
            index++;
            populationMonitoredLabels.add(personScrolledElement);
        }

        ScrollableGroup scrollableGroup = new ScrollableGroup(new Coord(10, 70), new Coord(390, 300), populationMonitoredLabels, this);*/


        ScrollableGroup scrollableGroup = new PeopleInfoElementGroup(new Coord(10, 100), new Coord(390, 300), people, this);
        addWindowElementGroup(scrollableGroup);
        double x = scrollableGroup.getScrollWindowSize().x;
        Label label = new Label(new Coord(15, 70), new Coord(x/2 - 5, 20), "name", this);
        addWindowElement(label);
        label = new Label(new Coord(x/2+20, 70), new Coord(x/2 -5, 20), "kasta", this);
        addWindowElement(label);

    }
}
