package WindowElements.ScrollElements;


import Foundation.Products.RawMaterials.ElementConcentrationItem;
import Images.ChemicalElementImage;
import Images.Image;
import Images.MineralImage;
import Utils.Geometry.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.Label;

public class ElementConcentrationItemRow extends ScrollableRow {

    private ElementConcentrationItem elementConcentrationItem;

    public ElementConcentrationItemRow(ElementConcentrationItem elementConcentrationItem, Coord size, ScrollableGroup parent) {
        super(size, parent);
        this.elementConcentrationItem = elementConcentrationItem;
        initRow();
    }

    private void initRow(){
        Image elementImage = new ChemicalElementImage(new Coord(5, 0), new Coord(size.x/2 - 10, size.y),
                elementConcentrationItem.element, parent);
        rowElements.add(elementImage);

        Label amount = new Label(new Coord(size.x/2 + 5, 0), new Coord(size.x/2 - 10, size.y),
                String.valueOf(elementConcentrationItem.part), parent);
        rowElements.add(amount);
    }
}
