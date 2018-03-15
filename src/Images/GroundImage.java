package Images;

import Foundation.*;
import Foundation.Elements.Ground;

import java.util.ArrayList;

public class GroundImage extends Image {

    public GroundImage(Coord pos, Coord size, Ground.GroundType groundType, Window parent) {
        super(pos, size, parent);
        setShapes(groundType);
    }

    public GroundImage(Coord pos, Ground.GroundType groundType, Window parent) {
        super(pos, parent);
        setShapes(groundType);
    }

    public void setShapes(Ground.GroundType groundType){
        ArrayList<BasicShape> groundShapes = new ArrayList<>();
        Color color = new Color(Color.Type.White);
        switch (groundType){
            case Sand:
                color = new Color(Color.Type.Yellow);
                break;
            case Water:
                color = new Color(Color.Type.Blue);
                break;
            case Soil:
                color = new Color(Color.Type.Green);
                break;
            case Mud:
                color = new Color(Color.Type.Green2);
                break;
            case Rock:
                color = new Color(Color.Type.Gray);
                break;
        }
        RectangleShape newShape = new RectangleShape(new Coord(getPos()), new Coord(getSize()), color, false, true);
        groundShapes.add(newShape);
        setBasicShapes(groundShapes);
    }
}
