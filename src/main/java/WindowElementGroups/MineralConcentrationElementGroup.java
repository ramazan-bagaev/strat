package WindowElementGroups;

import Foundation.FieldObjects.NaturalObjects.MineralConcentration;
import Foundation.FieldObjects.NaturalObjects.MineralConcentrationItem;
import Utils.Geometry.Coord;
import WindowElements.ScrollElements.MineralConcentrationItemRow;
import Windows.Window;

public class MineralConcentrationElementGroup extends ScrollableGroup {

    private MineralConcentration mineralConcentration;

    public MineralConcentrationElementGroup(Coord pos, Coord size, MineralConcentration mineralConcentration, Window parent) {
        super(pos, size, parent);
        this.mineralConcentration = mineralConcentration;
        initRowElements();
    }

    private void initRowElements() {
        initCameraConfiguration();
        scrollableRows.clear();
        for(MineralConcentrationItem item: mineralConcentration.getItems()){
            MineralConcentrationItemRow row = new MineralConcentrationItemRow(item, new Coord(getSize().x, 20),this);
            addScrollableRow(row);
        }
    }
}
