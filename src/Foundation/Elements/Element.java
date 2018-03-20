package Foundation.Elements;

import Foundation.*;

import java.util.ArrayList;

public class Element extends Broadcaster {

    public enum Type{
        City, Ground, Ecosystem, Army, River, Tree, Manor, Village, Farm, Sawmill
    }

    private Type type;
    protected Field parent;
    protected Time time;
    protected FieldMap map;

    private ArrayList<BasicShape> shapes;

    public Element(Type type, Time time, Field parent, FieldMap map){
        this.shapes = new ArrayList<>();
        this.time = time;
        this.map = map;

        setType(type);
        setParent(parent);
    }

    public void renewResourceCause(){
        //resourceCause.renew();
    }


    // do not use anywhere except FieldMap!
    public ArrayList<BasicShape> getShapes() {
        return shapes;
    }

    public void setBasicShapes(ArrayList<BasicShape> shapes) {
        this.shapes = shapes;
        for (BasicShape basicShape: this.shapes){
            basicShape.shift(getShift());
        }
    }

    private Coord getShift() {
        Coord parentShift = parent.getMap().getGameEngine().getGameWindowElement().getShift();
        Coord parentPos = parent.getFieldMapPos();
        int parentSize = parent.getSize();
        return parentShift.add(new Coord(parentPos.x * parentSize, parentPos.y * parentSize));
    }

    public void addShape(BasicShape shape){
        shape.shift(getShift());
        shapes.add(shape);
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Field getParent() {
        return parent;
    }

    public void setParent(Field parent) {
        this.parent = parent;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String getValue(String key) {
        return Broadcaster.noResult;
    }

}
