package Images;

import Foundation.*;

import java.util.ArrayList;

public class FarmImage extends Image {

    public FarmImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);

        setShapes();
    }

    public void setShapes(){
        RectangleShape rect = new RectangleShape(pos.add(new Coord(size.x/4, size.y/4)), new Coord(size.x/2, size.y/2), BasicShape.Color.Black);
        ArrayList<BasicShape> shapes = getBasicShapes();
        shapes.add(rect);
    }
}
