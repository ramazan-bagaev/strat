package Images;

import Foundation.*;
import Foundation.BasicShapes.RectangleShape;
import Utils.Geometry.Coord;

public class TreeResourceImage extends Image {

    public TreeResourceImage(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        RectangleShape rectangleShape = new RectangleShape(new Coord(0, 0), getSize(), new Color(Color.Type.Red), true);
        RectangleShape rectangleShape1 = new RectangleShape(new Coord(getSize().x / 6, getSize().y/6),
                new Coord(2 * getSize().x/3, 2 * getSize().y/3), new Color(Color.Type.Black), false);
        RectangleShape rectangleShape2 = new RectangleShape(new Coord(getSize().x/3, getSize().y/3),
                new Coord(getSize().x/3, getSize().y/3), new Color(Color.Type.Black), false);
        clearBasicShapes();
        addBasicShape(rectangleShape);
        addBasicShape(rectangleShape1);
        addBasicShape(rectangleShape2);
    }
}
