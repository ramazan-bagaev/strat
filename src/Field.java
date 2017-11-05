import java.util.ArrayList;
import java.util.Random;


public class Field {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        Ground, Sand
    }

    private int x;
    private int y;
    private int size;
    private int elementSize;

    private ArrayList<Element> elements;
    private Type type;

    private Random random;

    public Field(){
        elements = new ArrayList<Element>();
        setX(0);
        setY(0);
        setSize(0);
        setElementSize(1);
    }

    public Field(int x, int y, int size, Random random){
        elements = new ArrayList<Element>();
        setX(x);
        setY(y);
        setSize(size);
        setElementSize(size/5);
        this.random = random;
        if (random.nextBoolean()) setType(Type.Ground);
        else setType(Type.Sand);
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void fillWithRandomTrees() {
        int number = getSize() / getElementSize();
        for (int i = 0; i < number; i++)
            for (int j = 0; j < number; j++) {
                int dx = i * getElementSize();
                int dy = j * getElementSize();
                createTree(x + dx, y + dy);
            }
    }

    private void createTree(int x, int y){
        if (type == Type.Sand && random.nextInt(10) > 8){
            int typeNum = random.nextInt(10);
            Tree.Type type = Tree.Type.Big;
            if (typeNum < 1) type = Tree.Type.Big;
            if (typeNum < 4) type = Tree.Type.Middle;
            else type = Tree.Type.Small;
            Tree newTree = new Tree(x, y, getElementSize(), type);
            addElement(newTree);
        }
        else if ( type == Type.Ground && random.nextInt(10) > 5){
            int typeNum = random.nextInt(3);
            Tree.Type type = Tree.Type.Big;
            if (typeNum == 0) type = Tree.Type.Big;
            if (typeNum == 1) type = Tree.Type.Middle;
            if (typeNum == 2) type = Tree.Type.Small;
            Tree newTree = new Tree(x, y, getElementSize(), type);
            addElement(newTree);
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

    public void addElement(Element element){
        elements.add(element);
    }

    public int getElementSize() {
        return elementSize;
    }

    public void setElementSize(int elementSize) {
        this.elementSize = elementSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public RectangleShape getRectangleShape(){
        BasicShape.Color color = BasicShape.Color.White;
        if (type == Type.Ground) color = BasicShape.Color.Green2;
        if (type == Type.Sand) color = BasicShape.Color.Yellow;
        return new RectangleShape(getX(), getY(), getSize(), getSize(), color);
    }
}
