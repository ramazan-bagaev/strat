package WindowElementGroups;

import Foundation.Resource;
import Foundation.ResourceStore;
import Foundation.Window;
import Utils.Coord;
import WindowElements.ScrollElements.ResourceScrollRow;

public class ResourceElementGroup extends ScrollableGroup {

    protected ResourceStore resourceStore;

    public ResourceElementGroup(Coord pos, Coord size, ResourceStore resourceStore, Window parent) {
        super(pos, size, parent);
        this.resourceStore = resourceStore;
        initRowElements();
    }

    public ResourceElementGroup(Coord pos, Coord size, Coord scrollWindowSize, ResourceStore resourceStore, Window parent) {
        super(pos, size, scrollWindowSize, parent);
        this.resourceStore = resourceStore;
        initRowElements();
    }

    public void initRowElements(){
        initCameraConfiguration();
        scrollableRows.clear();
        for(Resource resource: resourceStore.getResources()){
            ResourceScrollRow resourceScrollRow = new ResourceScrollRow(resource, new Coord(getSize().x, 20), this);
            addScrollableRow(resourceScrollRow);
        }
    }


}
