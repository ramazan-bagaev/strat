public class Rock extends Element {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        Big, Middle, Small
    }

    private int x;
    private int y;
    private int size;
    private Type type;


    public Rock(int x, int y, int size, Type type){
        super();
        setX(x);
        setY(y);
        setSize(size);
        setType(type);

        switch (type){
            case Big:{
                RectangleShape shape1 = new RectangleShape(getX() + getSize()/4, getY() + getSize()/4, getSize()/2, getSize()/2,
                                                            BasicShape.Color.Gray);
                RectangleShape shape2 = new RectangleShape(getX() + getSize()/3, getY() + getSize()/3, getSize()/3, getSize()/3,
                                                            BasicShape.Color.LightGray);

                addShape(shape1);
                addShape(shape2);
                break;
            }


            case Middle: {
                RectangleShape shape1 = new RectangleShape(getX() + getSize()/3, getY() + getSize()/3, getSize()/3, getSize()/3,
                        BasicShape.Color.Gray);
                RectangleShape shape2 = new RectangleShape(getX() + getSize() * 2/5, getY() + getSize() *2/5, getSize()/5, getSize()/5,
                        BasicShape.Color.LightGray);

                addShape(shape1);
                addShape(shape2);
                break;
            }
            case Small: {
                RectangleShape shape1 = new RectangleShape(getX() + getSize() * 2/5, getY() + getSize() *2/5, getSize()/5, getSize()/5,
                        BasicShape.Color.Gray);
                addShape(shape1);
                break;
            }
        }
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
}
