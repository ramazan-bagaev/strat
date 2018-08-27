package WindowElements.ScrollElements;

import Foundation.FieldObjects.NaturalObjects.MineralConcentration;
import Foundation.FieldObjects.NaturalObjects.MineralConcentrationItem;
import Images.Image;
import Images.MineralImage;
import Utils.Geometry.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.Label;

public class MineralConcentrationItemRow extends ScrollableRow {

    private MineralConcentrationItem mineralConcentrationItem;

    public MineralConcentrationItemRow(MineralConcentrationItem mineralConcentrationItem, Coord size, ScrollableGroup parent) {
        super(size, parent);
        this.mineralConcentrationItem = mineralConcentrationItem;
        initRow();
    }

    private void initRow(){
        Image mineralImage = new MineralImage(new Coord(5, 0), new Coord(size.x/2 - 10, size.y),
                mineralConcentrationItem.elementConcentration, parent);
        rowElements.add(mineralImage);

        Label amount = new Label(new Coord(size.x/2 + 5, 0), new Coord(size.x/2 - 10, size.y),
                String.valueOf(mineralConcentrationItem.part), parent);
        rowElements.add(amount);
    }
}
