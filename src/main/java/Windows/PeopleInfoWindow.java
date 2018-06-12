package Windows;

import Foundation.Frame;
import Foundation.Label;
import Foundation.Person.People;
import Utils.Geometry.Coord;
import WindowElementGroups.PeopleInfoElementGroup;
import WindowElementGroups.ScrollableGroup;

public class PeopleInfoWindow extends ClosableWindow {

    private People people;

    public PeopleInfoWindow(People people, Frame parent) {
        super(new Coord(300, 400), new Coord(500, 500), parent);
        this.people = people;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        ScrollableGroup scrollableGroup = new PeopleInfoElementGroup(new Coord(10, 90), new Coord(390, 300), people, this);
        addWindowElementGroup(scrollableGroup);
        double x = scrollableGroup.getScrollWindowSize().x;
        Label label = new Label(new Coord(15, 50), new Coord(x/2 - 5, 20), "name", this);
        addWindowElement(label);
        label = new Label(new Coord(x/2+20, 50), new Coord(x/2 -5, 20), "kasta", this);
        addWindowElement(label);
    }
}
