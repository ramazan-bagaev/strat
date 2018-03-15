package Images;

import Foundation.*;

import java.util.ArrayList;

public class ManorImage extends Image {

    public ManorImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);

        setShapes();
    }

    public void setShapes(){
        RectangleShape rect =
                new RectangleShape(pos.add(new Coord(size.x/4, size.y/4)), new Coord(size.x/2, 3*size.y/4), new Color(Color.Type.LightGray));
        LineShape line1 = new LineShape(pos.add(new Coord(0, 0)), pos.add(size), new Color(Color.Type.Yellow), 4.0f);
        LineShape line2 = new LineShape(pos.add(new Coord(size.x, 0)), pos.add(new Coord(0, size.y)), new Color(Color.Type.Yellow), 4.0f);
        ArrayList<BasicShape> shapes = getBasicShapes();
        shapes.add(rect);
        shapes.add(line1);
        shapes.add(line2);
    }
}
