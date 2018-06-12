package Foundation.Elements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Utils.Broadcaster;
import Utils.Subscription;

import java.util.ArrayList;

public class FieldElement implements Broadcaster {

    public enum Type{
        City, Ground, Ecosystem, Army, River, Tree, Manor, Village, Farm, Sawmill, Trawler, Mine, Road
    }

    private Type type;
    protected Field parent;
    protected Time time;
    protected FieldMap map;

    private ArrayList<BasicShape> shapes;

    public FieldElement(Type type, Field parent){
        this.shapes = new ArrayList<>();
        this.time = parent.getTime();
        this.map = parent.getMap();
        this.type = type;
        this.parent = parent;
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
            basicShape.shift(parent.getShift());
        }
    }

    public void addShape(BasicShape shape){
        shape.shift(parent.getShift());
        shapes.add(shape);
    }

    public ArrayList<BasicShape> getCopyOfBasicShapesWithoutShift(){
        ArrayList<BasicShape> copy = new ArrayList<>();
        for(BasicShape basicShape: shapes){
            BasicShape copyShape = BasicShape.getCopy(basicShape);
            copyShape.shift(parent.getShift().multiply(-1));
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

    @Override
    public void subscribe(String key, Subscription subscription) {

    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {

    }

}
