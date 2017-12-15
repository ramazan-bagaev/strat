package Foundation;

public class Ground extends Element {


    public GroundType getGroundType() {
        return groundType;
    }

    public void setGroundType(GroundType groundType) {
        this.groundType = groundType;
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

    public int getCapacity() {
        return capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public enum GroundType{
        Sand, Water, Soil, Mud, Rock
    }

    private int x;
    private int y;
    private int size;
    private int capacity;
    private int maxCapacity;

    private GroundType groundType;

    public Ground(int x, int y, int elementSize, GroundType groundType, Time time, Field parent){
        super(Type.Ground, time, parent);
        setGroundType(groundType);
        setX(x);
        setY(y);
        setSize(elementSize);

        int cap = 0;
        int maxCap = 0;
        int renewAm = 0;
        if (getGroundType() == GroundType.Soil) {
            cap = 100000;
            maxCap = 100000;
            renewAm = 10000;
        }
        if (getGroundType() == GroundType.Mud){
            cap = 10000;
            maxCap = 10000;
            renewAm  = 1000;
        }
        if (getGroundType() == GroundType.Sand){
            cap = 1000;
            maxCap = 1000;
            renewAm = 100;
        }
        if (getGroundType() == GroundType.Water){
            cap = 1000000;
            maxCap = 1000000;
            renewAm = 1000;
        }
        if (getGroundType() == GroundType.Rock){
            cap = 10000;
            maxCap = 10000;
            renewAm = 100;
        }
        //ResourceCause fertilityCause = new ResourceCause(Resource.Type.Fertility);
        //setResourceCause(fertilityCause);

        BasicShape.Color color = BasicShape.Color.White;
        if (getGroundType() == GroundType.Soil) color = BasicShape.Color.Green;
        if (getGroundType() == GroundType.Sand) color = BasicShape.Color.Yellow;
        if (getGroundType() == GroundType.Water) color = BasicShape.Color.Blue;
        if (getGroundType() == GroundType.Mud) color = BasicShape.Color.Green2;
        if (getGroundType() == GroundType.Rock) color = BasicShape.Color.Gray;
        RectangleShape newShape = new RectangleShape(new Coord(getX(), getY()), new Coord(getSize(), getSize()), color, true);
        addShape(newShape);
    }

    @Override
    public void run(){
        renewResourceCause();
    }

    @Override
    public String getValue(String key){
        String result = super.getValue(key);
        if (!Broadcaster.noResult.equals(result)) return result;
        switch (key){
            case "groundType":
                switch (groundType) {
                    case Sand:
                        return "Sand";
                    case Water:
                        return "Water";
                    case Soil:
                        return "Soil";
                    case Mud:
                        return "Mud";
                    case Rock:
                        return "Rock";
                }

        }
        return Broadcaster.noResult;
    }
}
