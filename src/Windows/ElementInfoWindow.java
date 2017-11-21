package Windows;

import Foundation.Coord;
import Foundation.Element;
import Foundation.Label;
import Foundation.Windows;

public class ElementInfoWindow extends ClosableWindow{

    private Element element;

    public ElementInfoWindow(Windows parent) {
        super(new Coord(0 ,0), new Coord(200, 100), parent);
        Label elementType = new Label(new Coord(20, 20), new Coord(20, 10), "TYPE:", this);
        addWindowElement(elementType);
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
