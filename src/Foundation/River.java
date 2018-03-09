package Foundation;

import java.util.ArrayList;

public class River extends Element {



    public enum RiverType{
        Begin, End, Middle
    }

    public enum Side{
        North, East, West, South
    }

    private River nextPiece;
    private FieldMap map;
    private RiverType riverType;

    private ArrayList<Side> inStreams;
    private Side outStream;

    public River(Time time, FieldMap map, Field parent, ArrayList<Side> in, Side out, RiverType type) {
        super(Type.River, time, parent);
        this.map = map;
        inStreams = in;
        outStream = out;
        riverType = type;
        setShapes();
    }

    public River(Time time, FieldMap map, Field parent, Side in, Side out, RiverType type) {
        super(Type.River, time, parent);
        this.map = map;
        inStreams = new ArrayList<>();
        inStreams.add(in);
        outStream = out;
        riverType = type;
        setShapes();
    }

    public River(Time time, FieldMap map, Field parent, Side out) {
        super(Type.River, time, parent);
        this.map = map;
        inStreams = new ArrayList<>();
        outStream = out;
        riverType = RiverType.Begin;
        setShapes();
    }

    public void addInStream(Side side){
        if (outStream != side && !inStreams.contains(side)){
            inStreams.add(side);
        }
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = getShapes();
        shapes.clear();
        Coord pos = getParent().getFieldMapPos();
        int size = getParent().getSize();
        pos.x = pos.x * size;
        pos.y = pos.y * size;
        BasicShape.Color color = BasicShape.Color.Blue;
        if (inStreams.contains(Side.North) || outStream == Side.North){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(4*size/10, 0)),
                    new Coord(size/5, size/2), color, false, true);
            shapes.add(rect);
        }
        if (inStreams.contains(Side.South) || outStream == Side.South){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(4*size/10, size/2)),
                    new Coord(size/5, size/2), color, false, true);
            shapes.add(rect);
        }
        if (inStreams.contains(Side.West) || outStream == Side.West){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(0, 4*size/10)),
                    new Coord(size/2, size/5), color, false, true);
            shapes.add(rect);
        }
        if (inStreams.contains(Side.East) || outStream == Side.East){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(size/2, 4*size/10)),
                    new Coord(size/2, size/5), color, false, true);
            shapes.add(rect);
        }
    }

    public River getNextPiece() {
        return nextPiece;
    }

    public void setNextPiece(River nextPiece) {
        this.nextPiece = nextPiece;
    }

    public static Side convert(Coord.Direction direction){
        if (direction == Coord.Direction.Down) return Side.South;
        if (direction == Coord.Direction.Up) return Side.North;
        if (direction == Coord.Direction.Left) return Side.West;
        if (direction == Coord.Direction.Right) return Side.East;
        return Side.North;
    }
}
