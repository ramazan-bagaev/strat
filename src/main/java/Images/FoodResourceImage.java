package Images;

import Foundation.*;
import Foundation.BasicShapes.RectangleShape;
import Utils.Geometry.Coord;
import Windows.Window;

public class FoodResourceImage extends Image {

    public FoodResourceImage(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        RectangleShape rectangleShape = new RectangleShape(new Coord(0, getSize().y/4), new Coord(getSize().x, getSize().y/2),
                new Color(Color.Type.Red), true);
        RectangleShape rectangleShape1 = new RectangleShape(new Coord(getSize().x/4, 2 * getSize().y/5), new Coord(getSize().x/10, getSize().y/5),
                new Color(Color.Type.Yellow), true);

        RectangleShape rectangleShape2 = new RectangleShape(new Coord(3 * getSize().x/4, 2 * getSize().y/5), new Coord(getSize().x/10, getSize().y/5),
                new Color(Color.Type.Yellow), true);
        clearBasicShapes();
        addBasicShape(rectangleShape);
        addBasicShape(rectangleShape1);
        addBasicShape(rectangleShape2);

    }

}
