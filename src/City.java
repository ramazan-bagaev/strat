public class City extends Element{

    public enum Type{
        Big, Middle, Small
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private int x;
    private int y;
    private int size;
    private Type type;

    public City(int x, int y, int size, Type type){
        setX(x);
        setY(y);
        setSize(size);
        setType(type);

        switch (type){
            case Big: {
                RectangleShape shape1 = new RectangleShape(getX() + getSize()/8, getY() + getSize()/8, getSize()/4, getSize()/4,
                                                            BasicShape.Color.Green);
                RectangleShape shape2 = new RectangleShape(getX() + 5 * getSize()/8, getY() + getSize()/8, getSize()/4, getSize()/4,
                                                            BasicShape.Color.Red);
                RectangleShape shape3 = new RectangleShape(getX() + getSize()/8, getY() + 5 * getSize()/8, getSize()/4, getSize()/4,
                                                            BasicShape.Color.Blue);
                RectangleShape shape4 = new RectangleShape(getX() + 5 * getSize()/8, getY() + 5 * getSize()/8, getSize()/4, getSize()/4,
                                                            BasicShape.Color.Yellow);
                addShape(shape1);
                addShape(shape2);
                addShape(shape3);
                addShape(shape4);
                break;
            }
            case Middle: {
                RectangleShape shape1 = new RectangleShape(getX() + getSize()/8, getY() + 3 * getSize()/8, getSize()/4, getSize()/4,
                        BasicShape.Color.Green);
                RectangleShape shape2 = new RectangleShape(getX() + 5 * getSize()/8, getY() + 3 * getSize()/8, getSize()/4, getSize()/4,
                        BasicShape.Color.Red);
                addShape(shape1);
                addShape(shape2);
                break;
            }
            case Small:{
                RectangleShape shape1 = new RectangleShape(getX() + 3 * getSize()/8, getY() + 3 * getSize()/8, getSize()/4, getSize()/4,
                        BasicShape.Color.Red);
                addShape(shape1);
                break;
            }
        }
    }
}
