package Windows;

import Foundation.*;
import WindowElementGroups.ScrollableGroup;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.ResourceBankElement;

import java.util.ArrayList;

public class ResourceStoreWindow extends ClosableWindow {

    private ResourceStore resourceStore;

    public ResourceStoreWindow(ResourceStore resourceStore, Windows parent){
        super(new Coord(100, 800), new Coord(300, 100), parent);
        this.resourceStore = resourceStore;
        setElements();
    }

    public void setElements(){

        removeWindowElements();
        addCloseButton();

        ArrayList<WindowElement> elements = new ArrayList<>();

        int y = 10;
        for(ResourceBank resourceBank: resourceStore.getResourceBanks()){
            elements.add(new ResourceBankElement(new Coord(10, y).add(getPos()), new Coord(280, 20), resourceBank, this));
            y += 20;
        }

        ScrollableGroup scrollableGroup = new ScrollableGroup(new Coord(0, 10).add(getPos()), 300, 3, 20, this);
        scrollableGroup.setScrollableElements(elements);
        addWindowElementGroup(scrollableGroup);

        //MonitoredBroadcastLabel foodAmount = new MonitoredBroadcastLabel(getPos().add(new Coord(10, 10)), new Coord(180, 20),
        //        "food:", food, "capacity", this);

        //addWindowElement(foodAmount);
    }

    public void setResourceStore(ResourceStore resourceStore){
        this.resourceStore = resourceStore;
        setElements();
    }
}
