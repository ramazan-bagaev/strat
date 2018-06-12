package WindowElements;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Window;
import Foundation.WindowElement;
import Foundation.WindowElementGroup;
import Utils.Geometry.Coord;

public class BackgroundElement extends WindowElement {

    private RectangleShape background;

    public BackgroundElement(Coord pos, Coord size, Color color, Window parent) {
        super(pos, size, parent);
        background = new RectangleShape(new Coord(0, 0), new Coord(size), color, false, true);
        addBasicShape(background);
    }

    public BackgroundElement(Coord pos, Coord size, Color color, WindowElementGroup groupParent, Window parent) {
        super(pos, size, groupParent, parent);
        background = new RectangleShape(new Coord(0, 0), new Coord(size), color, false, true);
        addBasicShape(background);
    }

    public void changeColor(Color color){
        background.setColor(color);
    }
}
