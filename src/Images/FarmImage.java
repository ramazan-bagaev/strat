package Images;

import Foundation.*;

import java.util.ArrayList;

public class FarmImage extends Image {

    public FarmImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        RectangleShape farmField = new RectangleShape(pos.add(new Coord(size.x/3, size.y/3)), new Coord(size.x/3, size.y/3),
                new Color(Color.Type.Green2, 0.1f), true, true);
        LineShape line1 = new LineShape(new Coord(4*size.x/9, 5*size.y/12), new Coord(5*size.x/9,5*size.y/12), 1f);
        LineShape line2 = new LineShape(new Coord(4*size.x/9, 6*size.y/12), new Coord(5*size.x/9,6*size.y/12), 1f);
        LineShape line3 = new LineShape(new Coord(4*size.x/9, 7*size.y/12), new Coord(5*size.x/9,7*size.y/12), 1f);
        clearBasicShapes();
        addBasicShape(farmField);
        addBasicShape(line1);
        addBasicShape(line2);
        addBasicShape(line3);
    }
}
