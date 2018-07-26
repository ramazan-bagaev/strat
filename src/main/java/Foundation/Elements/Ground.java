package Foundation.Elements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Images.RockImage;
import Utils.Broadcaster;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class Ground extends FieldElement {


    public GroundType getGroundType() {
        return groundType;
    }

    public void setGroundType(GroundType groundType) {
        this.groundType = groundType;
    }

    public enum GroundType{
        Sand, Water, Soil, Mud, Rock
    }

    private GroundType groundType;

    public Ground(GroundType groundType, Field parent){
        super(Type.Ground, parent);
        setGroundType(groundType);
        setShapes();
    }

    public void setShapes(){
        int size = parent.getSize();
        Color color = new Color(Color.Type.White);
        int height = parent.getHeight();
        if (getGroundType() == GroundType.Soil){
            color = new Color(Color.Type.Green);
            color.moreGreen(height);
        }
        if (getGroundType() == GroundType.Sand){
            color = new Color(Color.Type.Yellow);
            color.moreBlue(height);
        }
        if (getGroundType() == GroundType.Mud){
            color = new Color(Color.Type.Green2);
            color.moreGreen(height);
        }
        if (getGroundType() == GroundType.Water){
            color = new Color(Color.Type.Blue);
            color.moreBlue(-height - 10);
        }
        if (getGroundType() == GroundType.Rock){
            color = new Color(Color.Type.Gray);
            color.moreGreen(height);
            color.moreRed(height);
        }
        /*if (getGroundType() == GroundType.Rock){
            ArrayList<BasicShape> shapes = new RockImage(new Coord(0, 0), new Coord(size, size), height, null)
                    .getBasicShapesRemoveAndShiftBack();
            for (BasicShape basicShape: shapes){
                addShape(basicShape);
            }
        }
        else {
            RectangleShape newShape = new RectangleShape(new Coord(0, 0), new Coord(size, size), color, false,true);
            addShape(newShape);
        }*/
        RectangleShape newShape = new RectangleShape(new Coord(0, 0), new Coord(size, size), color, false,true);
        addShape(newShape);
    }


    @Override
    public String getValue(String key){
        String result = super.getValue(key);
        if (!Broadcaster.noResult.equals(result)) return result;
        switch (key){
            case "groundType":
                switch (groundType) {
                    case Sand:
                        return "Sand";
                    case Water:
                        return "Water";
                    case Soil:
                        return "Soil";
                    case Mud:
                        return "Mud";
                    case Rock:
                        return "Rock";
                }
            case "height":
                return String.valueOf(parent.getHeight());
        }
        return Broadcaster.noResult;
    }
}
