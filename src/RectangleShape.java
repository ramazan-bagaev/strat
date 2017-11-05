public class RectangleShape extends BasicShape {

    private int x;
    private int y;
    private int width;
    private int height;

    public RectangleShape(int x, int y, int width, int height, BasicShape.Color color){
        super(Type.Rectangle, color);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
