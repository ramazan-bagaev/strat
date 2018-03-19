package Windows;

import Foundation.*;
import WindowElementGroups.ScrollableGroup;
import WindowElements.ResourceWindowElement;

import java.util.ArrayList;

public class ResourceStoreWindow extends ClosableWindow {

    private ResourceStore resourceStore;

    public ResourceStoreWindow(ResourceStore resourceStore, Frame parent){
        super(new Coord(100, 800), new Coord(300, 100), parent);
        this.resourceStore = resourceStore;
        setElements();
    }

    public void setElements(){

        removeWindowElements();
        addCloseButton();

        ArrayList<WindowElement> elements = new ArrayList<>();

        int y = 10;
        for(Resource resource: resourceStore.getResources()){
            elements.add(new ResourceWindowElement(new Coord(10, y), new Coord(280, 20), resource, this));
            y += 20;
        }

        ScrollableGroup scrollableGroup = new ScrollableGroup(new Coord(0, 10), 300, 3, 20, this);
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
