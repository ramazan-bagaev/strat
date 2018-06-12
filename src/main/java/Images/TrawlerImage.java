package Images;

import Foundation.*;
import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.TriangleShape;
import Utils.Geometry.Coord;

public class TrawlerImage extends Image{

    public TrawlerImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        Coord size = getSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(3*size.x/20, 15*size.y/20), new Coord(3*size.x/20, size.y/20),
                new Color(Color.Type.Brown), false, true);
        addBasicShape(rectangleShape);

        TriangleShape triangleShape = new TriangleShape(new Coord(3*size.x/20, 15*size.y/20), new Coord(3*size.x/20, 8*size.y/10),
                new Coord(size.x/10, 15*size.y/20), new Color(Color.Type.Brown));
        addBasicShape(triangleShape);

        triangleShape = new TriangleShape(new Coord(3*size.x/10, 15*size.y/20), new Coord(3*size.x/10, 8*size.y/10),
                new Coord(7*size.x/20, 15*size.y/20), new Color(Color.Type.Brown));
        addBasicShape(triangleShape);

        triangleShape = new TriangleShape(new Coord(5*size.x/20, 10*size.y/20), new Coord(5*size.x/20, 14*size.y/20),
                new Coord(7*size.x/20, 14*size.y/20), new Color(Color.Type.White));
        addBasicShape(triangleShape);

        LineShape lineShape = new LineShape(new Coord(5*size.x/20 , 15*size.y/20), new Coord(5*size.x/20 , 10*size.y/20),
                new Color(Color.Type.Brown));
        addBasicShape(lineShape);
    }
}
