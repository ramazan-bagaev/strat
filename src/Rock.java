public class Rock extends Element {

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void decrease(int delta){
        capacity -= delta;
    }

    public enum SizeType{
        Big, Middle, Small
    }

    private int x;
    private int y;
    private int size;
    private SizeType sizeType;

    private int capacity;


    public Rock(int x, int y, int size, SizeType sizeType){
        super(Type.Rock);
        setX(x);
        setY(y);
        setSize(size);
        setSizeType(sizeType);

        switch (sizeType){
            case Big:{
                setCapacity(1000);
                RectangleShape shape1 = new RectangleShape(getX() + getSize()/4, getY() + getSize()/4, getSize()/2, getSize()/2,
                                                            BasicShape.Color.Gray);
                RectangleShape shape2 = new RectangleShape(getX() + getSize()/3, getY() + getSize()/3, getSize()/3, getSize()/3,
                                                            BasicShape.Color.LightGray);

                addShape(shape1);
                addShape(shape2);
                break;
            }


            case Middle: {
                setCapacity(100);
                RectangleShape shape1 = new RectangleShape(getX() + getSize()/3, getY() + getSize()/3, getSize()/3, getSize()/3,
                        BasicShape.Color.Gray);
                RectangleShape shape2 = new RectangleShape(getX() + getSize() * 2/5, getY() + getSize() *2/5, getSize()/5, getSize()/5,
                        BasicShape.Color.LightGray);

                addShape(shape1);
                addShape(shape2);
                break;
            }
            case Small: {
                setCapacity(10);
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
