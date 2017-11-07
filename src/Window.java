import java.util.ArrayList;

public class Window {

    private int x;
    private int y;
    private int sizeX;
    private int sizeY;
    private Window parent;

    private ArrayList<WindowElement> windowElements;


    public Window(int x, int y, int sizeX, int sizeY, Window parent){
        setX(x);
        setY(y);
        setSizeX(sizeX);
        setSizeY(sizeY);
        setParent(parent);
        windowElements = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
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
}
