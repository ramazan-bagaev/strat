package Images;

import Foundation.*;

import java.util.ArrayList;

public class RockResourceImage extends Image {

    public RockResourceImage(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        RectangleShape rectangleShape = new RectangleShape(getPos(), new Coord(getSize().x / 3, 2 * getSize().y/3), new Color(Color.Type.Gray), true);
        RectangleShape rectangleShape1 = new RectangleShape(getPos().add(new Coord(getSize().x/2, getSize().y/3)),
                new Coord(getSize().x/3, getSize().y/3), new Color(Color.Type.LightGray));
        RectangleShape rectangleShape2 = new RectangleShape(getPos().add(new Coord(getSize().x/3, getSize().y/2)),
                new Coord(getSize().x/3,getSize().y/3), new Color(Color.Type.Gray));
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        basicShapes.add(rectangleShape);
        basicShapes.add(rectangleShape1);
        basicShapes.add(rectangleShape2);
    }
}
