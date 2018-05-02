package Windows;

import Foundation.*;
import Utils.Coord;
import WindowElementGroups.ResourceElementGroup;

public class ResourceStoreWindow extends ClosableWindow {

    private ResourceStore resourceStore;

    public ResourceStoreWindow(ResourceStore resourceStore, Frame parent){
        super(new Coord(100, 600), new Coord(400, 150), parent);
        this.resourceStore = resourceStore;
        setElements();
    }

    public void setElements(){

        removeWindowElements();
        addCloseButton();


        ResourceElementGroup resourceElementGroup = new ResourceElementGroup(new Coord(10, 30),
                new Coord(390, 90), resourceStore, this);
       // scrollableGroup.setScrollableElements(elements);
        addWindowElementGroup(resourceElementGroup);

        //MonitoredBroadcastLabel foodAmount = new MonitoredBroadcastLabel(getFieldPos().add(new Index(10, 10)), new Index(180, 20),
        //        "food:", food, "capacity", this);

        //addWindowElement(foodAmount);
    }
}
