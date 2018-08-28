package WindowElementGroups;

import Foundation.Products.RawMaterials.ElementConcentration;
import Foundation.Products.RawMaterials.ElementConcentrationItem;
import Utils.Geometry.Coord;
import WindowElements.ScrollElements.ElementConcentrationItemRow;
import Windows.Window;

public class ElementConcentrationGroup extends ScrollableGroup {

    private ElementConcentration elementConcentration;

    public ElementConcentrationGroup(Coord pos, Coord size, ElementConcentration elementConcentration, Window parent) {
        super(pos, size, parent);
        this.elementConcentration = elementConcentration;
        initRowElements();
    }

    private void initRowElements(){
        initCameraConfiguration();
        scrollableRows.clear();
        for(ElementConcentrationItem item: elementConcentration.getItems()){
            ElementConcentrationItemRow row = new ElementConcentrationItemRow(item, new Coord(getSize().x, 20),this);
            addScrollableRow(row);
        }
    }


}
