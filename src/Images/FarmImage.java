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
        LineShape line1 = new LineShape(pos.add(new Coord(4*size.x/9, 5*size.y/12)), pos.add(new Coord(5*size.x/9,5*size.y/12)), 1f);
        LineShape line2 = new LineShape(pos.add(new Coord(4*size.x/9, 6*size.y/12)), pos.add(new Coord(5*size.x/9,6*size.y/12)), 1f);
        LineShape line3 = new LineShape(pos.add(new Coord(4*size.x/9, 7*size.y/12)), pos.add(new Coord(5*size.x/9,7*size.y/12)), 1f);
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.add(farmField);
        basicShapes.add(line1);
        basicShapes.add(line2);
        basicShapes.add(line3);
    }
}
