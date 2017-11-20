package Foundation;

public class Tree extends Element {

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public enum SizeType{
        Small, Big, Middle
    }

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

        RectangleShape trunk;
        RectangleShape foliage;
        int trunkX;
        int trunkY;
        int foliageX;
        int foliageY;
        int foliageHeight;
        int foliageWidth;
        int trunkHeight;
        int trunkWidth;
        switch (sizeType){
            case Big:
                foliageHeight = elementSize / 2;
                foliageWidth = elementSize * 3 / 4;
                trunkHeight = elementSize / 2;
                trunkWidth = elementSize / 4;
                foliageX = x + elementSize / 8;
                foliageY = y;
                trunkX = x + elementSize * 3 / 8;
                trunkY = foliageY + foliageHeight;
                trunk = new RectangleShape(trunkX, trunkY, trunkWidth, trunkHeight, BasicShape.Color.Red, true);
                foliage = new RectangleShape(foliageX, foliageY, foliageWidth, foliageHeight, BasicShape.Color.Green, true);
                addShape(trunk);
                addShape(foliage);
                break;
            case Middle:
                foliageHeight = elementSize * 3 / 8;
                foliageWidth = elementSize / 2;
                trunkHeight = elementSize / 4;
                trunkWidth = elementSize / 5;
                trunkX = x + elementSize * 2 / 5;
                trunkY = y + elementSize - trunkHeight;
                foliageX = x + elementSize / 4;
                foliageY = trunkY - foliageHeight;
                trunk = new RectangleShape(trunkX, trunkY, trunkWidth, trunkHeight, BasicShape.Color.Red);
                foliage = new RectangleShape(foliageX, foliageY, foliageWidth, foliageHeight, BasicShape.Color.Green);
                addShape(trunk);
                addShape(foliage);
                break;
            case Small:
                foliageHeight = elementSize / 4;
                foliageWidth = elementSize / 4;
                trunkHeight = elementSize / 8;
                trunkWidth = elementSize / 7;
                trunkX = x + elementSize * 5 / 12;
                trunkY = y + elementSize - trunkHeight;
                foliageX = x + elementSize * 3 / 8;
                foliageY = trunkY - foliageHeight;
                trunk = new RectangleShape(trunkX, trunkY, trunkWidth, trunkHeight, BasicShape.Color.Red);
                foliage = new RectangleShape(foliageX, foliageY, foliageWidth, foliageHeight, BasicShape.Color.Green);
                addShape(trunk);
                addShape(foliage);
                break;
        }
    }

    public void run(){
        renewResourceCause();
    }
}