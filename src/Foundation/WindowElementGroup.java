package Foundation;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Utils.Coord;

import java.util.ArrayList;

public class WindowElementGroup {

    protected Coord pos;
    protected Coord size;

    private Window parent;
    private ArrayList<WindowElement> windowElements;
    private ArrayList<BasicShape> basicShapes;

    public WindowElementGroup(Coord pos, Coord size, Window parent){
        setParent(parent);
        this.pos = new Coord(pos);
        this.size = new Coord(size);
        setSize(size);
        windowElements = new ArrayList<>();
        basicShapes = new ArrayList<>();
        addBasicShape(new RectangleShape(new Coord(0, 0), new Coord(size), new Color(Color.Type.Black), false));
    }

    public ArrayList<WindowElement> getWindowElements() {
        return windowElements;
    }

    public void setWindowElements(ArrayList<WindowElement> windowElements) {
        this.windowElements = windowElements;
    }

    public void addWindowElement(WindowElement windowElement){
        windowElements.add(windowElement);
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


    public void click(Coord point){
    }

    public void click2(Coord point){
    }

    public void scroll(double delta){
    }

    public boolean drag(Coord point, Coord pressedPos, boolean dragBegin){
        return false;
    }

    public void hoover(Coord point){

    }


    public void draw(OpenGLBinder openGLBinder){
        for(BasicShape basicShape: basicShapes){
            openGLBinder.draw(basicShape);
        }
        for(WindowElement windowElement: windowElements){
            windowElement.draw(openGLBinder);
        }
    }

    //public ArrayList<BasicShape> getBasicShapes() {
    //    return basicShapes;
    //}

    public void addBasicShape(BasicShape basicShape){
        basicShape.shift(getShift());
        basicShapes.add(basicShape);
    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        for(BasicShape basicShape: basicShapes){
            addBasicShape(basicShape);
        }
    }

    public void run(){
        for(WindowElement windowElement: windowElements){
            windowElement.run();
        }
    }

    public Coord getShift(){
        Coord shiftP = parent.getShift();
        return shiftP.add(pos);
    }
}
