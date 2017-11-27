package Foundation;

public class Rock extends Element {

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public enum SizeType{
        Big, Middle, Small
    }

    private int x;
    private int y;
    private int size;
    private SizeType sizeType;


    public Rock(int x, int y, int intSize, SizeType sizeType, Field parent){
        super(Type.Rock, parent);

        int cap = 0;
        if (sizeType == SizeType.Big) cap = 100000;
        if (sizeType == SizeType.Middle) cap = 100000;
        if (sizeType == SizeType.Small) cap = 10000;
        ResourceCause rockCause = new ResourceCause(Resource.Type.Stone, cap, 100000, 0);
        setResourceCause(rockCause);

        setX(x);
        setY(y);
        setSize(intSize);
        setSizeType(sizeType);
        Coord pos;
        Coord size;
        switch (sizeType){
            case Big:{
                pos = new Coord(getX() + getSize()/4, getY() + getSize()/4);
                size = new Coord(getSize()/2, getSize()/2);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Gray);
                pos = new Coord(getX() + getSize()/3, getY() + getSize()/3);
                size = new Coord(getSize()/3, getSize()/3);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.LightGray);

                addShape(shape1);
                addShape(shape2);
                break;
            }


            case Middle: {
                pos = new Coord(getX() + getSize()/3, getY() + getSize()/3);
                size = new Coord(getSize()/3, getSize()/3);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Gray);
                pos = new Coord(getX() + getSize() * 2/5, getY() + getSize() *2/5);
                size = new Coord(getSize()/5, getSize()/5);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.LightGray);

                addShape(shape1);
                addShape(shape2);
                break;
            }
            case Small: {
                pos = new Coord(getX() + getSize() * 2/5, getY() + getSize() *2/5);
                size = new Coord(getSize()/5, getSize()/5);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Gray);
                addShape(shape1);
                break;
            }
        }
    }

    public void run(){
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

    @Override
    public String getValue(String key){
        String result = super.getValue(key);
        if (!Broadcaster.noResult.equals(result)) return result;
        switch (key){
            case "sizeType":
                switch (sizeType){
                    case Big:
                        return "big";
                    case Middle:
                        return "middle";
                    case Small:
                        return "small";
                }
        }
        return Broadcaster.noResult;
    }
}