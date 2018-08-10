package Images;

import Foundation.*;
import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Utils.Geometry.Coord;
import Windows.Window;

public class ManorImage extends Image {

    public ManorImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);

        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        RectangleShape rect = new RectangleShape(new Coord(3*size.x/10, 3*size.y/10), new Coord(4*size.x/10, 4*size.y/10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(rect);

        rect = new RectangleShape(new Coord(4*size.x/10, 4*size.y/10), new Coord(2*size.x/10, 2*size.y/10),
                new Color(Color.Type.Red), false, true);
        addBasicShape(rect);

        rect = new RectangleShape(new Coord(size.x/10, size.y/10), new Coord(size.x/10, size.y/10),
                new Color(Color.Type.Gray), false, true);
        addBasicShape(rect);

        rect = new RectangleShape(new Coord(8*size.x/10, size.y/10), new Coord(size.x/10, size.y/10),
                new Color(Color.Type.Gray), false, true);
        addBasicShape(rect);

        rect = new RectangleShape(new Coord(size.x/10, 8*size.y/10), new Coord(size.x/10, size.y/10),
                new Color(Color.Type.Gray), false, true);
        addBasicShape(rect);

        rect = new RectangleShape(new Coord(8*size.x/10, 8*size.y/10), new Coord(size.x/10, size.y/10),
                new Color(Color.Type.Gray), false, true);
        addBasicShape(rect);

        LineShape line1 = new LineShape(new Coord(2*size.x/10, size.y/10), new Coord(8*size.x/10, size.y/10),
                new Color(Color.Type.Black));
        addBasicShape(line1);

        line1 = new LineShape(new Coord(size.x/10, 2*size.y/10), new Coord(size.x/10, 8*size.y/10),
                new Color(Color.Type.Black));
        addBasicShape(line1);

        line1 = new LineShape(new Coord(2*size.x/10, 9*size.y/10), new Coord(8*size.x/10, 9*size.y/10),
                new Color(Color.Type.Black));
        addBasicShape(line1);

        line1 = new LineShape(new Coord(9*size.x/10, 2*size.y/10), new Coord(9*size.x/10, 8*size.y/10),
                new Color(Color.Type.Black));
        addBasicShape(line1);


    }
}
