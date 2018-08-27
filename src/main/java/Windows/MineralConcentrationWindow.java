package Windows;

import Foundation.FieldObjects.NaturalObjects.MineralConcentration;
import Foundation.Frame;
import Utils.Geometry.Coord;
import WindowElementGroups.MineralConcentrationElementGroup;

public class MineralConcentrationWindow extends ClosableWindow {

    private MineralConcentration mineralConcentration;

    public MineralConcentrationWindow(MineralConcentration mineralConcentration, Frame parent) {
        super(new Coord(0, 400), new Coord(300, 200), parent);
        this.mineralConcentration = mineralConcentration;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        MineralConcentrationElementGroup group = new MineralConcentrationElementGroup(new Coord(10, 10),
                new Coord(250, 150), mineralConcentration, this);
        addWindowElementGroup(group);

    }
}
