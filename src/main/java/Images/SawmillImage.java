package Images;

import Foundation.*;
import Foundation.BasicShapes.RectangleShape;
import Utils.Geometry.Coord;

public class SawmillImage extends Image{

    public SawmillImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        Coord size = getSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(7*size.x/10, 7*size.y/10), new Coord(size.x/5, size.y/5),
                new Color(Color.Type.Brown), false, true);
        addBasicShape(rectangleShape);
    }
}
