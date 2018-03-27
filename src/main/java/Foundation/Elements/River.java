package Foundation.Elements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;

public class River extends Element {



    public enum RiverType{
        Begin, End, Middle
    }

    public enum Side{
        North, East, West, South
    }

    private River nextPiece;
    private RiverType riverType;

    private ArrayList<Side> inStreams;
    private Side outStream;

    public River(Time time, FieldMap map, Field parent, ArrayList<Side> in, Side out, RiverType type) {
        super(Type.River, time, parent, map);
        inStreams = in;
        outStream = out;
        riverType = type;
        setShapes();
    }

    public River(Time time, FieldMap map, Field parent, Side in, Side out, RiverType type) {
        super(Type.River, time, parent, map);
        inStreams = new ArrayList<>();
        inStreams.add(in);
        outStream = out;
        riverType = type;
        setShapes();
    }

    public River(Time time, FieldMap map, Field parent, Side out) {
        super(Type.River, time, parent, map);
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
        ArrayList<BasicShape> shapes = new ArrayList<>();
        int size = parent.getSize();
        Color color = new Color(Color.Type.Blue);
        if (inStreams.contains(Side.North) || outStream == Side.North){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, 0),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
            if (outStream == Side.North){
                LineShape line1 = new LineShape(new Coord(9*size/20, 3*size/8),
                        new Coord(size/2, size/8), new Color(Color.Type.Black));
                LineShape line2 = new LineShape(new Coord(11*size/20, 3*size/8),
                        new Coord(size/2, size/8), new Color(Color.Type.Black));
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Side.South) || outStream == Side.South){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, size/2),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
            if (outStream == Side.South){
                LineShape line1 = new LineShape(new Coord(9*size/20, 5*size/8),
                        new Coord(size/2, 7*size/8), new Color(Color.Type.Black));
                LineShape line2 = new LineShape(new Coord(11*size/20, 5*size/8),
                        new Coord(size/2, 7*size/8), new Color(Color.Type.Black));
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Side.West) || outStream == Side.West){
            RectangleShape rect = new RectangleShape(new Coord(0, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
            if (outStream == Side.West){
                LineShape line1 = new LineShape(new Coord(3*size/8, 9*size/20),
                        new Coord(size/8, size/2), new Color(Color.Type.Black));
                LineShape line2 = new LineShape(new Coord(3*size/8, 11*size/20),
                        new Coord(size/8, size/2), new Color(Color.Type.Black));
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Side.East) || outStream == Side.East){
            RectangleShape rect = new RectangleShape(new Coord(size/2, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
            if (outStream == Side.East){
                LineShape line1 = new LineShape(new Coord(5*size/8, 9*size/20),
                        new Coord(7*size/8, size/2), new Color(Color.Type.Black));
                LineShape line2 = new LineShape(new Coord(5*size/8, 11*size/20),
                        new Coord(7*size/8, size/2), new Color(Color.Type.Black));
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        setBasicShapes(shapes);
        /*if (riverType == RiverType.End){
            RectangleShape rect = new RectangleShape(pos.add(new Index(size/4, size/4)), new Index(size/2, size/2),
                    BasicShape.Color.Red, true);
            shapes.add(rect);
        }
        if (riverType == RiverType.Begin){
            RectangleShape rect = new RectangleShape(pos.add(new Index(size/4, size/4)), new Index(size/2, size/2),
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

    public static Side convert(Index.Direction direction){
        if (direction == Index.Direction.Down) return Side.South;
        if (direction == Index.Direction.Up) return Side.North;
        if (direction == Index.Direction.Left) return Side.West;
        if (direction == Index.Direction.Right) return Side.East;
        return Side.North;
    }
}
