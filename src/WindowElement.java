import java.util.ArrayList;

public abstract class WindowElement {

    private int x;
    private int y;
    private int sizeX;
    private int sizeY;

    private Window parent;



    public WindowElement(int x, int y, int sizeX, int sizeY, Window parent){
        setX(x);
        setY(y);
        setSizeX(sizeX);
        setSizeY(sizeY);
        setParent(parent);
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

    public abstract ArrayList<BasicShape> getShapes();
}
