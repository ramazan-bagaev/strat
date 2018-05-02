package Foundation.Elements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Images.RockImage;
import Utils.Broadcaster;
import Utils.Coord;

import java.util.ArrayList;

public class Ground extends FieldElement {


    public GroundType getGroundType() {
        return groundType;
    }

    public void setGroundType(GroundType groundType) {
        this.groundType = groundType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public enum GroundType{
        Sand, Water, Soil, Mud, Rock
    }

    private int capacity;
    private int maxCapacity;

    private GroundType groundType;

    public Ground(GroundType groundType, Time time, Field parent, FieldMap map){
        super(Type.Ground, time, parent, map);
        setGroundType(groundType);


        int cap = 0;
        int maxCap = 0;
        int renewAm = 0;
        if (getGroundType() == GroundType.Soil) {
            cap = 100000;
            maxCap = 100000;
            renewAm = 10000;
        }
        if (getGroundType() == GroundType.Mud){
            cap = 10000;
            maxCap = 10000;
            renewAm  = 1000;
        }
        if (getGroundType() == GroundType.Sand){
            cap = 1000;
            maxCap = 1000;
            renewAm = 100;
        }
        if (getGroundType() == GroundType.Water){
            cap = 1000000;
            maxCap = 1000000;
            renewAm = 1000;
        }
        if (getGroundType() == GroundType.Rock){
            cap = 10000;
            maxCap = 10000;
            renewAm = 100;
        }
        //ResourceCause fertilityCause = new ResourceCause(Resource.Type.Fertility);
        //setResourceCause(fertilityCause);

        int size = parent.getSize();
        Color color = new Color(Color.Type.White);
        if (getGroundType() == GroundType.Soil) color = new Color(Color.Type.Green);
        if (getGroundType() == GroundType.Sand) color = new Color(Color.Type.Yellow);
        if (getGroundType() == GroundType.Water) color = new Color(Color.Type.Blue);
        if (getGroundType() == GroundType.Mud) color = new Color(Color.Type.Green2);
        if (getGroundType() == GroundType.Rock){
            ArrayList<BasicShape> shapes = new RockImage(new Coord(0, 0), new Coord(size, size), null)
                    .getBasicShapesRemoveAndShiftBack();
            for (BasicShape basicShape: shapes){
                addShape(basicShape);
            }
        }
        else {
            RectangleShape newShape = new RectangleShape(new Coord(0, 0), new Coord(size, size), color, false,true);
            addShape(newShape);
        }
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

        }
        return Broadcaster.noResult;
    }
}
