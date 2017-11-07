public class City extends Element{

    public SizeType getSizeType() {
        return sizeType;
    }

    public void setSizeType(SizeType sizeType) {
        this.sizeType = sizeType;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getRockVolume() {
        return rockVolume;
    }

    public void setRockVolume(int rockVolume) {
        this.rockVolume = rockVolume;
    }

    public int getForestVolume() {
        return forestVolume;
    }

    public void setForestVolume(int forestVolume) {
        this.forestVolume = forestVolume;
    }

    public void mineRock(int delta){
        rockVolume += delta;
    }

    public void consumeRock(int delta){
        rockVolume -= delta;
    }

    public void mineForest(int delta){
        forestVolume += delta;
    }

    public void consumeForest(int delta){
        forestVolume += delta;
    }

    public FieldMap getMap() {
        return map;
    }

    public void setMap(FieldMap map) {
        this.map = map;
    }

    public enum SizeType{
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

    private int x;
    private int y;
    private int size;
    private SizeType sizeType;



    private int population;

    private int rockVolume;
    private int forestVolume;

    private FieldMap map;

    public City(int x, int y, int size, SizeType sizeType, FieldMap map){
        super(Type.City);
        setX(x);
        setY(y);
        setSize(size);
        setSizeType(sizeType);
        setMap(map);
        switch (sizeType){
            case Big: {
                setPopulation(10000); // magic constant, TODO: make something with these constants
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
                setPopulation(1000);
                RectangleShape shape1 = new RectangleShape(getX() + getSize()/8, getY() + 3 * getSize()/8, getSize()/4, getSize()/4,
                        BasicShape.Color.Green);
                RectangleShape shape2 = new RectangleShape(getX() + 5 * getSize()/8, getY() + 3 * getSize()/8, getSize()/4, getSize()/4,
                        BasicShape.Color.Red);
                addShape(shape1);
                addShape(shape2);
                break;
            }
            case Small:{
                setPopulation(100);
                RectangleShape shape1 = new RectangleShape(getX() + 3 * getSize()/8, getY() + 3 * getSize()/8, getSize()/4, getSize()/4,
                        BasicShape.Color.Red);
                addShape(shape1);
                break;
            }
        }
    }
}
