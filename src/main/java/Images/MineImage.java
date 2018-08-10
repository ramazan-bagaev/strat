package Images;

import Foundation.Color;
import Utils.Geometry.Coord;
import Foundation.BasicShapes.RectangleShape;
import Windows.Window;

public class MineImage extends Image{

    public MineImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShape();
    }

    public void setShape(){
        clearBasicShapes();

        RectangleShape rectangleShape = new RectangleShape(new Coord(size.x/10, size.y/10), new Coord(size.x/10, size.y/10),
                new Color(Color.Type.Brown), false, true);
        addBasicShape(rectangleShape);
    }
}
