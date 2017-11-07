import java.util.Random;


public class Field {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void run() {
        if (element.getType() == Element.Type.City){
        }
    }

    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }

    public enum Type{
        Ground, Sand, Water
    }

    private int x;
    private int y;
    private int size;

    private Element element;
    private Type type;

    private Random random;
    private FieldMap map;


    public Field(int x, int y, int size, Random random, FieldMap map){
        setX(x);
        setY(y);
        setSize(size);
        setMap(map);
        this.random = random;
        int randNum = random.nextInt(3);
        Type tempType = Type.Ground;
        if (randNum == 0) tempType = Type.Ground;
        if (randNum == 1) tempType = Type.Sand;
        if (randNum == 2) tempType = Type.Water;
        setType(tempType);
        if (getType() != Type.Water) {
            int elType = random.nextInt(3);
            if (elType == 0) element = createRock();
            if (elType == 1) element = createTree();
            if (elType == 2) element = createCity();
        }
    }




    private Tree createTree(){
        if (type == Type.Sand && random.nextInt(10) > 8){
            int typeNum = random.nextInt(10);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum < 1) type = Tree.SizeType.Big;
            if (typeNum < 4 && typeNum > 0) type = Tree.SizeType.Middle;
            if (typeNum > 3) type = Tree.SizeType.Small;
            Tree newTree = new Tree(getX(), getY(), getSize(), type);
            return newTree;
        }
        else if ( type == Type.Ground && random.nextInt(10) > 3){
            int typeNum = random.nextInt(3);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum == 0) type = Tree.SizeType.Big;
            if (typeNum == 1) type = Tree.SizeType.Middle;
            if (typeNum == 2) type = Tree.SizeType.Small;
            Tree newTree = new Tree(getX(), getY(), getSize(), type);
            return newTree;
        }

        return null;
    }

    private Rock createRock(){
        if (random.nextInt(10) > 7) {
            int typeNum = random.nextInt(3);
            Rock.SizeType type = Rock.SizeType.Big;
            if (typeNum == 0) type = Rock.SizeType.Big;
            if (typeNum == 1) type = Rock.SizeType.Middle;
            if (typeNum == 2) type = Rock.SizeType.Small;
            Rock newRock = new Rock(getX(), getY(), getSize(), type);
            return newRock;
        }
        return null;
    }

    private City createCity(){
        if (random.nextInt(10) > 8) {
            int typeNum = random.nextInt(10);
            City.SizeType type = City.SizeType.Big;
            if (typeNum == 0) type = City.SizeType.Big;
            if (typeNum == 1 || typeNum == 2) type = City.SizeType.Middle;
            if (typeNum > 2) type = City.SizeType.Small;
            City newCity = new City(getX(), getY(), getSize(), type, map);
            return newCity;
        }
        return null;
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

    public RectangleShape getRectangleShape(){
        BasicShape.Color color = BasicShape.Color.White;
        if (getType() == Type.Ground) color = BasicShape.Color.Green;
        if (getType() == Type.Sand) color = BasicShape.Color.Yellow;
        if (getType() == Type.Water) color = BasicShape.Color.Blue;
        return new RectangleShape(getX(), getY(), getSize(), getSize(), color, true);
    }
}
