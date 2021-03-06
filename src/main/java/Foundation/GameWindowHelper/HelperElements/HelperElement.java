package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Utils.Geometry.Index;
import Foundation.GameWindowHelper.HelperField;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class HelperElement {

    private ArrayList<BasicShape> shapes;

    protected HelperField parent;
    protected Coord size;

    public HelperElement(HelperField helperField){
        shapes = new ArrayList<>();
        parent = helperField;
        //Index index = helperField.getFieldPos();
        size = new Coord(helperField.getSize().x, helperField.getSize().y);
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

    private Coord getShift() {
        Coord parentShift = parent.getMap().getParent().getShift();
        Index pos = parent.getPos();
        return parentShift.add(new Coord(pos.x * size.x, pos.y * size.y));
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

    public ArrayList<BasicShape> getCopyOfBasicShapesWithoutShift(){
        ArrayList<BasicShape> copy = new ArrayList<>();
        for(BasicShape basicShape: shapes){
            BasicShape copyShape = BasicShape.getCopy(basicShape);
            copyShape.shift(getShift().multiply(-1));
            copy.add(copyShape);
        }
        return copy;
    }

    public void clearBasicShapes(){
        shapes.clear();
    }

    public HelperField getParent() {
        return parent;
    }
}
