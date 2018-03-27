package Images;

import Foundation.*;
import Foundation.BasicShapes.RectangleShape;
import Utils.Coord;

public class RockResourceImage extends Image {

    public RockResourceImage(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        RectangleShape rectangleShape = new RectangleShape(new Coord(0, 0), new Coord(getSize().x / 3, 2 * getSize().y/3), new Color(Color.Type.Gray), true);
        RectangleShape rectangleShape1 = new RectangleShape(new Coord(getSize().x/2, getSize().y/3),
                new Coord(getSize().x/3, getSize().y/3), new Color(Color.Type.LightGray));
        RectangleShape rectangleShape2 = new RectangleShape(new Coord(getSize().x/3, getSize().y/2),
                new Coord(getSize().x/3,getSize().y/3), new Color(Color.Type.Gray));
        clearBasicShapes();
        addBasicShape(rectangleShape);
        addBasicShape(rectangleShape1);
        addBasicShape(rectangleShape2);
    }
}
