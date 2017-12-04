package Images;

import Foundation.*;

import java.util.ArrayList;

public class FoodResourceImage extends Image {

    public FoodResourceImage(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        RectangleShape rectangleShape = new RectangleShape(getPos().add(new Coord(0, getSize().y/4)), new Coord(getSize().x, getSize().y/2),
                BasicShape.Color.Red, true);
        RectangleShape rectangleShape1 = new RectangleShape(getPos().add(new Coord(getSize().x/4, 2 * getSize().y/5)), new Coord(getSize().x/10, getSize().y/5),
                BasicShape.Color.Yellow, true);

        RectangleShape rectangleShape2 = new RectangleShape(getPos().add(new Coord(3 * getSize().x/4, 2 * getSize().y/5)), new Coord(getSize().x/10, getSize().y/5),
                BasicShape.Color.Yellow, true);
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        basicShapes.add(rectangleShape);
        basicShapes.add(rectangleShape1);
        basicShapes.add(rectangleShape2);

    }

}
