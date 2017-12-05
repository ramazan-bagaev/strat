package Foundation;

import Foundation.BasicShape;

import java.util.ArrayList;

public class Element extends Broadcaster {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setResourceCause(ResourceCause resourceCause) {
        this.resourceCause = resourceCause;
    }

    public ResourceCause getResourceCause(){
        return resourceCause;
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

    public enum Type{
        City, Ground, Ecosystem
    }

    private ResourceCause resourceCause;
    private Type type;
    private Field parent;
    private Time time;

    private ArrayList<BasicShape> shapes;

    public Element(Type type, Time time, Field parent){
        this.shapes = new ArrayList<>();
        this.time = time;

        setType(type);
        setParent(parent);
    }

    public void run(){

    }

    public void renewResourceCause(){
        resourceCause.renew();
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

    @Override
    public String getValue(String key) {
        switch (key) {
            case "resourceCause.capacity":
                return getResourceCause().getValue("capacity");
            case "resourceCause.maxCapacity":
                return getResourceCause().getValue("maxCapacity");
            case "resourceCause.renewAmount":
                return getResourceCause().getValue("renewAmount");
        }
        return Broadcaster.noResult;
    }
}
