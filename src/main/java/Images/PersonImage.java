package Images;

import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Windows.Window;
import Utils.Geometry.Coord;

public class PersonImage extends Image {

    public PersonImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        RectangleShape rectangleShape = new RectangleShape(new Coord(size.x/10 ,size.y/10), new Coord(8*size.x/10, 8*size.x/10),
                new Color(Color.Type.Black), true, false);

        addBasicShape(rectangleShape);

        RectangleShape eye = new RectangleShape(new Coord(3*size.x/10 ,3*size.y/10), new Coord(size.x/10, size.x/10),
                new Color(Color.Type.Blue), false, true);

        addBasicShape(eye);

        eye = new RectangleShape(new Coord(6*size.x/10 ,3*size.y/10), new Coord(size.x/10, size.x/10),
                new Color(Color.Type.Blue), false, true);

        addBasicShape(eye);

        LineShape lineShape = new LineShape(new Coord(4*size.x/10, 7*size.y/10), new Coord(6*size.x/10, 7*size.y/10),
                new Color(Color.Type.Red), 2f);

        addBasicShape(lineShape);
    }
}
