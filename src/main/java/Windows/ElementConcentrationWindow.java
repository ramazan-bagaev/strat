package Windows;

import Foundation.Frame;
import Foundation.Products.RawMaterials.ElementConcentration;
import Utils.Geometry.Coord;
import WindowElementGroups.ElementConcentrationGroup;
import WindowElementGroups.MineralConcentrationElementGroup;

public class ElementConcentrationWindow extends ClosableWindow {

    private ElementConcentration elementConcentration;

    public ElementConcentrationWindow(ElementConcentration elementConcentration, Frame parent) {
        super(new Coord(300, 400), new Coord(200, 200), parent);
        this.elementConcentration = elementConcentration;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        ElementConcentrationGroup group = new ElementConcentrationGroup(new Coord(10, 10),
                new Coord(150, 150), elementConcentration, this);
        addWindowElementGroup(group);
    }
}
