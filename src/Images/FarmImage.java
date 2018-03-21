package Images;

import Foundation.*;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class FarmImage extends Image {

    public FarmImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        RectangleShape rectangleShape = new RectangleShape(new Coord(size.x/20,size.y/20), new Coord(18*size.x/20,18*size.y/20),
                new Color(Color.Type.Yellow2), false, true);
        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(4*size.x/10,2*size.y/10), new Coord(size.x/5,2*size.y/5),
                new Color(Color.Type.Gray), false, true);
        addBasicShape(rectangleShape);

        //rectangleShape = new RectangleShape(new Coord(9*size.x/20,3*size.y/10), new Coord(size.x/10,size.y/10),
        //        new Color(Color.Type.Gray), false, true);
        //addBasicShape(rectangleShape);

        TriangleShape triangleShape = new TriangleShape(new Coord(size.x/2, 3* size.y/10),
                new Coord(3*size.x/10,size.y/10), new Coord(5*size.x/20, 3*size.y/20), new Color(Color.Type.Red));
        addBasicShape(triangleShape);

        triangleShape = new TriangleShape(new Coord(size.x/2, 3* size.y/10),
                new Coord(5*size.x/20, size.y/2), new Coord(3*size.x/10, 11*size.y/20), new Color(Color.Type.Red));
        addBasicShape(triangleShape);

        triangleShape = new TriangleShape(new Coord(size.x/2, 3* size.y/10),
                new Coord(7*size.x/10, size.y/10), new Coord(15*size.x/20, 3*size.y/20), new Color(Color.Type.Red));
        addBasicShape(triangleShape);

        triangleShape = new TriangleShape(new Coord(size.x/2, 3* size.y/10),
                new Coord(15*size.x/20, size.y/2), new Coord(7*size.x/10, 11*size.y/20), new Color(Color.Type.Red));
        addBasicShape(triangleShape);

    }
}
