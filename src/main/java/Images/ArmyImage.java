package Images;

import Foundation.*;
import Foundation.BasicShapes.RectangleShape;
import Utils.Coord;

public class ArmyImage extends Image{

    public ArmyImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public ArmyImage(Coord pos, Window parent) {
        super(pos, parent);
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        Coord size = getSize();
        RectangleShape rectangleShape =
                new RectangleShape(new Coord(size.x/6, 5*size.y/6), new Coord(2*size.x/3, size.y/6), new Color(Color.Type.Black), true);
        RectangleShape rectangleShape1 =
                new RectangleShape(new Coord(size.x/3, size.y/3), new Coord(size.x/3, size.y/2), new Color(Color.Type.Black), true);
        addBasicShape(rectangleShape);
        addBasicShape(rectangleShape1);
    }
}
