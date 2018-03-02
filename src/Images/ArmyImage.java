package Images;

import Foundation.*;

import java.util.ArrayList;

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
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        Coord size = getSize();
        RectangleShape rectangleShape = new RectangleShape(new Coord(size.x/6, 0).add(getPos()), new Coord(size.x/6, size.y), BasicShape.Color.Black, true);
        RectangleShape rectangleShape1 = new RectangleShape(new Coord(2 * size.x/3, 0).add(getPos()), new Coord(size.x/6, size.y), BasicShape.Color.Black, true);
        basicShapes.add(rectangleShape);
        basicShapes.add(rectangleShape1);
    }
}
