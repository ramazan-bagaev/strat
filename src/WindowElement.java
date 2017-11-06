public class WindowElement {

    private int x;
    private int y;
    private int sizeX;
    private int sizeY;



    public WindowElement(int x, int y, int sizeX, int sizeY){
        setX(x);
        setY(y);
        setSizeX(sizeX);
        setSizeY(sizeY);
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
}
