package Windows;

import Foundation.Elements.Manor;
import Foundation.Frame;
import Utils.Geometry.Coord;

public class AddMinorArmyWindow extends ClosableWindow {

    private Manor manor;

    public AddMinorArmyWindow(Manor manor, Frame parent) {
        super(new Coord(400, 400), new Coord(200, 200), parent);
        this.manor = manor;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        //People people manor.getSociety().getPeople();
    }
}
