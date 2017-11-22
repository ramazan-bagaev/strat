package Foundation;

import java.util.ArrayList;

public abstract class WindowElement {

    private Coord pos;
    private Coord size;

    private Window parent;

    private ArrayList<BasicShape> basicShapes;



    public WindowElement(Coord pos, Coord size, Window parent){
        setPos(pos);
        setSize(size);
        setParent(parent);
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

    public void setPos(Coord pos) {
        this.pos = pos;
    }

    public Coord getSize() {
        return size;
    }

    public void setSize(Coord size) {
        this.size = size;
    }

    public boolean contain(Coord point){
        return point.inRectangle(pos, size);
    }

    public abstract void click(Coord point);

    public void run(){
        return;
    }

    public void draw(OpenGLBinder openGLBinder){
        for (BasicShape basicShape: getBasicShapes()){
            openGLBinder.draw(basicShape);
        }
    }

    public ArrayList<BasicShape> getBasicShapes() {
        return basicShapes;
    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        this.basicShapes = basicShapes;
    }
}
