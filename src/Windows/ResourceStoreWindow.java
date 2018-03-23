package Windows;

import Foundation.*;
import Utils.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.ScrollElements.ResourceScrollElement;
import WindowElements.ScrollElements.ScrollableElement;

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

        ArrayList<ScrollableElement> elements = new ArrayList<>();

        int y = 10;
        for(Resource resource: resourceStore.getResources()){
            elements.add(new ResourceScrollElement(new Coord(10, y), new Coord(280, 20), resource, this));
            y += 20;
        }

        ScrollableGroup scrollableGroup = new ScrollableGroup(new Coord(0, 10), new Coord(280, 100), this);
       // scrollableGroup.setScrollableElements(elements);
        addWindowElementGroup(scrollableGroup);

        //MonitoredBroadcastLabel foodAmount = new MonitoredBroadcastLabel(getPos().add(new Index(10, 10)), new Index(180, 20),
        //        "food:", food, "capacity", this);

        //addWindowElement(foodAmount);
    }

    public void setResourceStore(ResourceStore resourceStore){
        this.resourceStore = resourceStore;
        setElements();
    }
}
