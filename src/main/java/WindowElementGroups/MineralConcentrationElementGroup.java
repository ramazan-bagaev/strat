package WindowElementGroups;

import Foundation.FieldObjects.NaturalObjects.MineralConcentration;
import Foundation.FieldObjects.NaturalObjects.MineralConcentrationItem;
import Foundation.Person.Person;
import Utils.Geometry.Coord;
import WindowElements.ScrollElements.MineralConcentrationItemRow;
import WindowElements.ScrollElements.PersonScrolledRow;
import WindowElements.ScrollElements.ScrollableRow;
import Windows.ElementConcentrationWindow;
import Windows.ElementInfoWindow.PersonInfoWindow;
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

    @Override
    public void click(Coord point){
        point = cameraConfiguration.transform(point);
        for(ScrollableRow scrollableRow: scrollableRows){
            if (!scrollableRow.contain(point)) continue;
            MineralConcentrationItemRow row = (MineralConcentrationItemRow) scrollableRow;
            MineralConcentrationItem item = row.getMineralConcentrationItem();
            getParent().getParent().addSpecialWindow("element concentration window",
                    new ElementConcentrationWindow(item.elementConcentration, getParent().getParent()));
        }
    }
}
