import java.util.Random;


public class Field {

    public Ground.GroundType getGroundType() {
        return groundElement.getGroundType();
    }


    public void run() {
        groundElement.run();
        if (additionalElement != null) additionalElement.run();
    }

    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }

    public Ground getGround(){
        return groundElement;
    }

    public Element getAdditionalElement(){
        return additionalElement;
    }

    private int x;
    private int y;
    private int size;

    private Ground groundElement;
    private Element additionalElement;

    private Random random;
    private FieldMap map;


    public Field(int x, int y, int size, Random random, FieldMap map){
        setX(x);
        setY(y);
        setSize(size);
        setMap(map);
        additionalElement = null;
        this.random = random;
        int randNum = random.nextInt(5);
        Ground.GroundType tempType = Ground.GroundType.Soil;
        if (randNum == 0) tempType = Ground.GroundType.Soil;
        if (randNum == 1) tempType = Ground.GroundType.Sand;
        if (randNum == 2) tempType = Ground.GroundType.Water;
        if (randNum == 3) tempType = Ground.GroundType.Mud;
        if (randNum == 4) tempType = Ground.GroundType.Rock;
        groundElement = new Ground(getX(), getY(), getSize(), tempType, this);
        if (getGroundType() != Ground.GroundType.Water) {
            int elType = random.nextInt(3);
            //if (elType == 0) additionalElement = createRock();
            //if (elType == 1) additionalElement = createTree();
            //if (elType == 2) additionalElement = createCity();
        }
    }




    private Tree createTree(){
        if (getGroundType() == Ground.GroundType.Sand && random.nextInt(10) > 8){
            int typeNum = random.nextInt(10);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum < 1) type = Tree.SizeType.Big;
            if (typeNum < 4 && typeNum > 0) type = Tree.SizeType.Middle;
            if (typeNum > 3) type = Tree.SizeType.Small;
            Tree newTree = new Tree(getX(), getY(), getSize(), type, this);
            return newTree;
        }
        if ( getGroundType() == Ground.GroundType.Soil && random.nextInt(10) > 3) {
            int typeNum = random.nextInt(3);
            Tree.SizeType type = Tree.SizeType.Big;
            if (typeNum == 0) type = Tree.SizeType.Big;
            if (typeNum == 1) type = Tree.SizeType.Middle;
            if (typeNum == 2) type = Tree.SizeType.Small;
            Tree newTree = new Tree(getX(), getY(), getSize(), type, this);
            return newTree;
        }
        return null;
    }

    private Rock createRock(){
        if (getGroundType() == Ground.GroundType.Rock) {
            if (random.nextInt(10) > 7) {
                int typeNum = random.nextInt(3);
                Rock.SizeType type = Rock.SizeType.Big;
                if (typeNum == 0) type = Rock.SizeType.Big;
                if (typeNum == 1) type = Rock.SizeType.Middle;
                if (typeNum == 2) type = Rock.SizeType.Small;
                Rock newRock = new Rock(getX(), getY(), getSize(), type, this);
                return newRock;
            }
        }
        return null;
    }

    private City createCity(){
        if (getGroundType() == Ground.GroundType.Rock) return null;
        if (random.nextInt(10) > 8) {
            int typeNum = random.nextInt(10);
            City.SizeType type = City.SizeType.Big;
            if (typeNum == 0) type = City.SizeType.Big;
            if (typeNum == 1 || typeNum == 2) type = City.SizeType.Middle;
            if (typeNum > 2) type = City.SizeType.Small;
            City newCity = new City(getX(), getY(), getSize(), type, map, this);
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
}
