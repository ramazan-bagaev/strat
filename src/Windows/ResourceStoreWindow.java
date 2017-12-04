package Windows;

import Foundation.*;

public class ResourceStoreWindow extends ClosableWindow {

    private ResourceStore resourceStore;
    private ResourceBank food;

    public ResourceStoreWindow(ResourceStore resourceStore, Windows parent){
        super(new Coord(100, 800), new Coord(200, 100), parent);
        food = resourceStore.getResourceBank(Resource.Type.Food);
        this.resourceStore = resourceStore;
        setElements();
    }

    public void setElements(){

        removeWindowElements();
        addCloseButton();

        MonitoredBroadcastLabel foodAmount = new MonitoredBroadcastLabel(getPos().add(new Coord(10, 10)), new Coord(180, 20),
                "food:", food, "capacity", this);

        addWindowElement(foodAmount);
    }

    public void setResourceStore(ResourceStore resourceStore){
        this.resourceStore = resourceStore;
        setElements();
    }
}
