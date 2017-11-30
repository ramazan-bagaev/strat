package Images;

import Foundation.*;

import java.util.ArrayList;

public class TreeResourceImage extends Image {

    public TreeResourceImage(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        RectangleShape rectangleShape = new RectangleShape(getPos(), getSize(), BasicShape.Color.Red, true);
        RectangleShape rectangleShape1 = new RectangleShape(getPos().add(new Coord(getSize().x / 6, getSize().y/6)),
                new Coord(2 * getSize().x/3, 2 * getSize().y/3), BasicShape.Color.Black, false);
        RectangleShape rectangleShape2 = new RectangleShape(getPos().add(new Coord(getSize().x/3, getSize().y/3)),
                new Coord(getSize().x/3, getSize().y/3), BasicShape.Color.Black, false);
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        basicShapes.add(rectangleShape);
        basicShapes.add(rectangleShape1);
        basicShapes.add(rectangleShape2);
    }
}
