import java.util.ArrayList;

public class Window {

    private Coord pos;
    private Coord size;
    private Window parent;

    private ArrayList<WindowElement> windowElements;


    public Window(Coord pos, Coord size, Window parent){
        setPos(pos);
        setSize(size);
        setParent(parent);
        windowElements = new ArrayList<>();
    }

    public Window getParent() {
        return parent;
    }

    public void setParent(Window parent) {
        this.parent = parent;
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

    public ArrayList<BasicShape> getShapes(){
        ArrayList<BasicShape> result = new ArrayList<>();
        for(WindowElement windowElement: windowElements){
            result.addAll(windowElement.getShapes());
        }
        return  result;
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
        for(WindowElement windowElement: windowElements) {
            if (windowElement.contain(point)){
                windowElement.click();
                return;
            }
        }

    }

}
