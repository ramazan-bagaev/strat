package Windows;

import Foundation.*;
import WindowElementGroups.ScrollableGroup;

import java.util.ArrayList;

public class PopulationInfoWindow extends ClosableWindow{

    private Population population;

    public PopulationInfoWindow(Population population, Windows parent) {
        super(new Coord(300, 400), new Coord(500, 500), parent);

        setPopulation(population);
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;

        removeWindowElements();
        CloseButton closeButton = new CloseButton(new Coord(getPos().x + getSize().x - 15, getPos().y), new Coord(15, 15), this);
        addWindowElement(closeButton);

        MonitoredBroadcastLabel populationAmount = new MonitoredBroadcastLabel(new Coord(10, 10).add(getPos()), new Coord(200, 10),
                "Overall population:", population, "overallAmount", this);
        addWindowElement(populationAmount);

        MonitoredBroadcastLabel workingAmount = new MonitoredBroadcastLabel(new Coord(10, 30).add(getPos()), new Coord(200, 10),
                "Working population:", population, "workingAmount", this);
        addWindowElement(workingAmount);

        MonitoredBroadcastLabel manAmount = new MonitoredBroadcastLabel(new Coord(10, 50).add(getPos()), new Coord(200, 10),
                "Man populaion:", population, "manAmount", this);
        addWindowElement(manAmount);

        ArrayList<WindowElement> populationMonitoredLabels = new ArrayList<>();
        int index = 0;
        int scrollEl = 20;
        for (PopulationGroup populationGroup: population.getPopulationGroups()){
            PopulationMonitoredLabel populationMonitoredLabel = new PopulationMonitoredLabel(new Coord(10, 70 + index * scrollEl).add(getPos()),
                    new Coord(390, 10), populationGroup, this);
            index++;
            populationMonitoredLabels.add(populationMonitoredLabel);
        }

        ScrollableGroup scrollableGroup = new ScrollableGroup(new Coord(10, 70).add(getPos()), 390, 20, scrollEl, populationMonitoredLabels, this);
        addWindowElementGroup(scrollableGroup);
    }
}