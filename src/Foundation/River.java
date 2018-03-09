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
        Coord pos = new Coord(getParent().getFieldMapPos());
        int size = getParent().getSize();
        pos.x = pos.x * size;
        pos.y = pos.y * size;
        BasicShape.Color color = BasicShape.Color.Blue;
        if (inStreams.contains(Side.North) || outStream == Side.North){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(9*size/20, 0)),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
            if (outStream == Side.North){
                LineShape line1 = new LineShape(pos.add(new Coord(9*size/20, 3*size/8)),
                        pos.add(new Coord(size/2, size/8)), BasicShape.Color.Black);
                LineShape line2 = new LineShape(pos.add(new Coord(11*size/20, 3*size/8)),
                        pos.add(new Coord(size/2, size/8)), BasicShape.Color.Black);
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Side.South) || outStream == Side.South){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(9*size/20, size/2)),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
            if (outStream == Side.South){
                LineShape line1 = new LineShape(pos.add(new Coord(9*size/20, 5*size/8)),
                        pos.add(new Coord(size/2, 7*size/8)), BasicShape.Color.Black);
                LineShape line2 = new LineShape(pos.add(new Coord(11*size/20, 5*size/8)),
                        pos.add(new Coord(size/2, 7*size/8)), BasicShape.Color.Black);
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Side.West) || outStream == Side.West){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(0, 9*size/20)),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
            if (outStream == Side.West){
                LineShape line1 = new LineShape(pos.add(new Coord(3*size/8, 9*size/20)),
                        pos.add(new Coord(size/8, size/2)), BasicShape.Color.Black);
                LineShape line2 = new LineShape(pos.add(new Coord(3*size/8, 11*size/20)),
                        pos.add(new Coord(size/8, size/2)), BasicShape.Color.Black);
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Side.East) || outStream == Side.East){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(size/2, 9*size/20)),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
            if (outStream == Side.East){
                LineShape line1 = new LineShape(pos.add(new Coord(5*size/8, 9*size/20)),
                        pos.add(new Coord(7*size/8, size/2)), BasicShape.Color.Black);
                LineShape line2 = new LineShape(pos.add(new Coord(5*size/8, 11*size/20)),
                        pos.add(new Coord(7*size/8, size/2)), BasicShape.Color.Black);
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        /*if (riverType == RiverType.End){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(size/4, size/4)), new Coord(size/2, size/2),
                    BasicShape.Color.Red, true);
            shapes.add(rect);
        }
        if (riverType == RiverType.Begin){
            RectangleShape rect = new RectangleShape(pos.add(new Coord(size/4, size/4)), new Coord(size/2, size/2),
                    BasicShape.Color.White, true);
            shapes.add(rect);
        }*/

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
