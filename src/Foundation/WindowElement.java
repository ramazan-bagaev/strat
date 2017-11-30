package Foundation;

import java.util.ArrayList;

public class WindowElement {

    private Coord pos;
    private Coord size;

    private Window parent;
    private WindowElementGroup groupParent;

    private ArrayList<BasicShape> basicShapes;


    public WindowElement(Coord pos, Coord size, Window parent){
        this.pos = pos;
        this.size = size;
        setParent(parent);
        basicShapes = new ArrayList<>();
    }

    public WindowElement(Coord pos, Coord size, WindowElementGroup groupParent, Window parent){
        this.pos = pos;
        this.size = size;
        setParent(parent);
        setGroupParent(groupParent);
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

    public WindowElementGroup getGroupParent() {
        return groupParent;
    }

    public void setGroupParent(WindowElementGroup groupParent) {
        this.groupParent = groupParent;
    }

    public void shift(Coord shift){
        pos = getPos().add(shift);
        for(BasicShape basicShape: basicShapes){
            basicShape.shift(shift);
        }
    }

    public void characterInput(char c){
    }

    public void drag(Coord pos){
    }
}
