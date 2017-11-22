package Images;

import Foundation.*;

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
        BasicShape.Color color = BasicShape.Color.White;
        switch (groundType){
            case Sand:
                color = BasicShape.Color.Yellow;
                break;
            case Water:
                color = BasicShape.Color.Blue;
                break;
            case Soil:
                color = BasicShape.Color.Green;
                break;
            case Mud:
                color = BasicShape.Color.Green2;
                break;
            case Rock:
                color = BasicShape.Color.Gray;
                break;
        }
        RectangleShape newShape = new RectangleShape(new Coord(getPos()), new Coord(getSize()), color, true);
        groundShapes.add(newShape);
        setBasicShapes(groundShapes);
    }
}
