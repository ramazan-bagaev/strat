import java.util.ArrayList;

public abstract class WindowElement {

    private Coord pos;
    private Coord size;

    private Window parent;



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

    public abstract ArrayList<BasicShape> getShapes();

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

    public abstract void click();
}
