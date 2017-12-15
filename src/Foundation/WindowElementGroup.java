package Foundation;

import java.util.ArrayList;

public class WindowElementGroup {

    private Coord pos;
    private Coord size;

    private Window parent;
    private ArrayList<WindowElement> windowElements;
    private ArrayList<BasicShape> basicShapes;

    public WindowElementGroup(Coord pos, Coord size, Window parent){
        setParent(parent);
        setPos(pos);
        setSize(size);
        windowElements = new ArrayList<>();
        basicShapes = new ArrayList<>();
        basicShapes.add(new RectangleShape(new Coord(pos), new Coord(size), BasicShape.Color.Black, false));
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

    public void drag(Coord point){

    }


    public void draw(OpenGLBinder openGLBinder){
        for(BasicShape basicShape: basicShapes){
            openGLBinder.draw(basicShape);
        }
        for(WindowElement windowElement: windowElements){
            windowElement.draw(openGLBinder);
        }
    }

    public ArrayList<BasicShape> getBasicShapes() {
        return basicShapes;
    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        this.basicShapes = basicShapes;
    }

    public void run(){
        for(WindowElement windowElement: windowElements){
            windowElement.run();
        }
    }
}
