package Foundation;

import Foundation.BasicShapes.BasicShape;
import Utils.Coord;

import java.util.ArrayList;

public class WindowElement extends Broadcaster{

    protected Coord pos;
    protected Coord size;

    private Window parent;
    private WindowElementGroup groupParent;

    private ArrayList<BasicShape> basicShapes;


    public WindowElement(Coord pos, Coord size, Window parent){
        this.pos = new Coord(pos);
        this.size = new Coord(size);
        setParent(parent);
        basicShapes = new ArrayList<>();
    }

    public WindowElement(Coord pos, Coord size, WindowElementGroup groupParent, Window parent){
        this.pos = new Coord(pos);
        this.size = new Coord(size);
        setParent(parent);
        this.groupParent = groupParent;
        basicShapes = new ArrayList<>();
    }

    public Window getParent() {
        return parent;
    }

    public void setParent(Window parent) {
        this.parent = parent;
    }

    public Coord getPos() {
        return pos;
    }

    public Coord getSize() {
        return size;
    }

    public boolean contain(Coord point){
        return point.inRectangle(pos, size);
    }

    public void click(Coord point){
    }

    public void click2(Coord point){
    }

    public void hoover(Coord point){
    }

    public void run(){
        return;
    }

    public void draw(OpenGLBinder openGLBinder){
        for (BasicShape basicShape: basicShapes){
            openGLBinder.draw(basicShape);
        }
    }

    public void addBasicShape(BasicShape basicShape){
        basicShape.shift(getShift());
        basicShapes.add(basicShape);
    }

    public void addBasicShapes(ArrayList<BasicShape> basicShapes){
        for(BasicShape basicShape: basicShapes){
            addBasicShape(basicShape);
        }
    }

    public void clearBasicShapes(){
        basicShapes.clear();
    }

    //public ArrayList<BasicShape> getBasicShapes() {
    //    return basicShapes;
    //}

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        this.basicShapes = basicShapes;
        for(BasicShape basicShape: this.basicShapes){
            basicShape.shift(getShift());
        }
    }

    public ArrayList<BasicShape> getCopyOfBasicShapesWithoutShift(){
        ArrayList<BasicShape> copy = new ArrayList<>();
        for(BasicShape basicShape: basicShapes){
            BasicShape copyShape = BasicShape.getCopy(basicShape);
            copyShape.shift(getShift().multiply(-1));
            copy.add(copyShape);
        }
        return copy;
    }

    public WindowElementGroup getGroupParent() {
        return groupParent;
    }

    //public void setGroupParent(WindowElementGroup groupParent) {
    //    this.groupParent = groupParent;
    //}

    public void shift(Coord shift){
        pos = getPos().add(shift);
        for(BasicShape basicShape: basicShapes){
            basicShape.shift(shift);
        }
    }

    public void characterInput(char c){
    }

    public boolean drag(Coord pos, Coord pressedPos, boolean dragBegin){
        return false;
    }

    public Coord getShift(){
        if (groupParent != null) {
            Coord shiftP = groupParent.getShift();
            return shiftP.add(pos);
        }
        if (parent == null) return new Coord(pos);
        Coord shiftP = parent.getShift();
        return shiftP.add(pos);
    }

    public void setBasicShapesWithoutShift(ArrayList<BasicShape> basicShapes){
        this.basicShapes = basicShapes;
    }

    public ArrayList<BasicShape> getBasicShapesRemoveAndShiftBack(){
        ArrayList<BasicShape> result = basicShapes;
        basicShapes = null;
        for(BasicShape basicShape: result){
            basicShape.shift(getShift().multiply(-1));
        }
        return result;
    }

    public void setShapes(){

    }

    @Override
    public String getValue(String key) {
        return null;
    }
}
