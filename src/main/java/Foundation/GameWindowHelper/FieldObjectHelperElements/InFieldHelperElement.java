package Foundation.GameWindowHelper.FieldObjectHelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.GameWindowHelper.HelperField;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class InFieldHelperElement {

    protected HelperField parent;
    private ArrayList<BasicShape> shapes;

    public InFieldHelperElement(HelperField helperField){
        this.parent = helperField;
        shapes = new ArrayList<>();
    }

    public ArrayList<BasicShape> getBasicShapes(){
        return shapes;
    }

    public void setBasicShapes(ArrayList<BasicShape> shapes) {
        this.shapes = shapes;
        for (BasicShape basicShape: this.shapes){
            basicShape.shift(getShift());
        }
    }

    public Coord getShift() {
        return parent.getField().getShift();
    }

    public void addShape(BasicShape shape){
        shape.shift(getShift());
        shapes.add(shape);
    }

    public void addShapes(ArrayList<BasicShape> basicShapes){
        for(BasicShape basicShape: basicShapes){
            basicShape.shift(getShift());
            shapes.add(basicShape);
        }
    }

    public void clearShapes(){
        shapes.clear();
    }

    public HelperField getParent() {
        return parent;
    }
}
