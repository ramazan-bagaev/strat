public class Tree extends Element{

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

    public int getElementSize() {
        return elementSize;
    }

    public void setElementSize(int elementSize) {
        this.elementSize = elementSize;
    }

    public int getTrunkHeight() {
        return trunkHeight;
    }

    public void setTrunkHeight(int trunkHeight) {
        this.trunkHeight = trunkHeight;
    }

    public int getTrunkWidth() {
        return trunkWidth;
    }

    public void setTrunkWidth(int trunkWidth) {
        this.trunkWidth = trunkWidth;
    }

    public int getFoliageHeight() {
        return foliageHeight;
    }

    public void setFoliageHeight(int foliageHeight) {
        this.foliageHeight = foliageHeight;
    }

    public int getFoliageWidth() {
        return foliageWidth;
    }

    public void setFoliageWidth(int foliageWidth) {
        this.foliageWidth = foliageWidth;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public Field getParent() {
        return parent;
    }

    public void setParent(Field parent) {
        this.parent = parent;
    }


    public enum SizeType{
        Small, Big, Middle
    }

    private Field parent;
    private int x;
    private int y;
    private int elementSize; // this is the size of side of a square that element could annex
    private int trunkHeight;
    private int trunkWidth;
    private int foliageHeight;
    private int foliageWidth;
    private SizeType sizeType;

    public Tree(int x, int y, int elementSize, SizeType sizeType, Field parent){
        super(Type.Tree, parent);
        setSizeType(sizeType);

        int maxCapacity = 0;
        if (parent.getGroundType() == Ground.GroundType.Soil) maxCapacity = 100000;
        if (parent.getGroundType() == Ground.GroundType.Sand) maxCapacity = 10000;
        int cap = 0;
        if (sizeType == SizeType.Big) cap = 100000;
        if (sizeType == SizeType.Middle) cap = 10000;
        if (sizeType == SizeType.Small) cap = 1000;

        ResourceCause treeCause = new ResourceCause(Resource.Type.Tree, cap, maxCapacity, 1);
        setResourceCause(treeCause);

        setElementSize(elementSize);
        setX(x);
        setY(y);
        RectangleShape trunk;
        RectangleShape foliage;
        int trunkX;
        int trunkY;
        int foliageX;
        int foliageY;
        switch (sizeType){
            case Big:
                setFoliageHeight(elementSize / 2);
                setFoliageWidth(elementSize * 3 / 4);
                setTrunkHeight(elementSize / 2);
                setTrunkWidth(elementSize / 4);
                foliageX = x + elementSize / 8;
                foliageY = y;
                trunkX = x + elementSize * 3 / 8;
                trunkY = foliageY + getFoliageHeight();
                trunk = new RectangleShape(trunkX, trunkY, getTrunkWidth(), getTrunkHeight(), BasicShape.Color.Red, true);
                foliage = new RectangleShape(foliageX, foliageY, getFoliageWidth(), getFoliageHeight(), BasicShape.Color.Green, true);
                addShape(trunk);
                addShape(foliage);
                break;
            case Middle:
                setFoliageHeight(elementSize * 3 / 8);
                setFoliageWidth(elementSize / 2);
                setTrunkHeight(elementSize / 4);
                setTrunkWidth(elementSize / 5);
                trunkX = x + elementSize * 2 / 5;
                trunkY = y + elementSize - getTrunkHeight();
                foliageX = x + elementSize / 4;
                foliageY = trunkY - getFoliageHeight();
                trunk = new RectangleShape(trunkX, trunkY, getTrunkWidth(), getTrunkHeight(), BasicShape.Color.Red);
                foliage = new RectangleShape(foliageX, foliageY, getFoliageWidth(), getFoliageHeight(), BasicShape.Color.Green);
                addShape(trunk);
                addShape(foliage);
                break;
            case Small:
                setFoliageHeight(elementSize / 4);
                setFoliageWidth(elementSize / 4);
                setTrunkHeight(elementSize / 8);
                setTrunkWidth(elementSize / 7);
                trunkX = x + elementSize * 5 / 12;
                trunkY = y + elementSize - getTrunkHeight();
                foliageX = x + elementSize * 3 / 8;
                foliageY = trunkY - getFoliageHeight();
                trunk = new RectangleShape(trunkX, trunkY, getTrunkWidth(), getTrunkHeight(), BasicShape.Color.Red);
                foliage = new RectangleShape(foliageX, foliageY, getFoliageWidth(), getFoliageHeight(), BasicShape.Color.Green);
                addShape(trunk);
                addShape(foliage);
                break;
        }
    }

    public void run(){
        renewResourceCause();
    }
}