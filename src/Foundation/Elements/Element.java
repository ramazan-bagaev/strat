package Foundation.Elements;

import Foundation.*;

import java.util.ArrayList;

public class Element extends Broadcaster {

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

    public enum Type{
        City, Ground, Ecosystem, Army, River, Tree, Manor, Farm
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


    public ArrayList<BasicShape> getShapes() {
        return shapes;
    }

    public void setBasicShapes(ArrayList<BasicShape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(BasicShape shape){
        shapes.add(shape);
    }

}
