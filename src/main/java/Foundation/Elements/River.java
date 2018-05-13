package Foundation.Elements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.FieldObjects.NaturalObjects.WaterFlowObject;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;

public class River extends FieldElement {



    public enum RiverType{
        Begin, End, Middle
    }

    private River nextPiece;
    private RiverType riverType;

    private ArrayList<Index.Direction> inStreams;
    private Index.Direction outStream;

    public River(Time time, FieldMap map, Field parent, ArrayList<Index.Direction> in, Index.Direction out, RiverType type) {
        super(Type.River, time, parent, map);
        inStreams = in;
        outStream = out;
        riverType = type;
        fillField();
        setShapes();
    }

    public River(Time time, FieldMap map, Field parent, Index.Direction in, Index.Direction out, RiverType type) {
        super(Type.River, time, parent, map);
        inStreams = new ArrayList<>();
        inStreams.add(in);
        outStream = out;
        riverType = type;
        fillField();
        setShapes();
    }

    public River(Time time, FieldMap map, Field parent, Index.Direction out) {
        super(Type.River, time, parent, map);
        this.map = map;
        inStreams = new ArrayList<>();
        outStream = out;
        riverType = RiverType.Begin;
        fillField();
        setShapes();
    }

    public void fillField(){
        Field field = getParent();
        int cellAmount = field.getCellAmount();

        field.addFieldObject(new WaterFlowObject(field, new Index(cellAmount/2-1, cellAmount/2-1), new Index(2, 2), inStreams, outStream));
        for(Index.Direction direction: inStreams){
            switch (direction){

                case Up:
                    field.addFieldObject(new WaterFlowObject(field,
                            new Index(cellAmount/2-1, 0), new Index(2, cellAmount/2 - 1),
                            direction, Index.opposite(direction)));
                    break;
                case Down:
                    field.addFieldObject(new WaterFlowObject(field,
                            new Index(cellAmount/2-1, cellAmount/2+1), new Index(2, cellAmount/2 - 1),
                            direction, Index.opposite(direction)));
                    break;
                case Right:
                    field.addFieldObject(new WaterFlowObject(field,
                            new Index(cellAmount/2+1, cellAmount/2-1), new Index(cellAmount/2-1, 2),
                            direction, Index.opposite(direction)));
                    break;
                case Left:
                    field.addFieldObject(new WaterFlowObject(field,
                            new Index(0, cellAmount/2-1), new Index(cellAmount/2-1, 2),
                            direction, Index.opposite(direction)));
                    break;
                case None:
                    break;
            }
        }
        switch (outStream){
            case Up:
                field.addFieldObject(new WaterFlowObject(field,
                        new Index(cellAmount/2-1, 0), new Index(2, cellAmount/2 - 1),
                        outStream, Index.opposite(outStream)));
                break;
            case Down:
                field.addFieldObject(new WaterFlowObject(field,
                        new Index(cellAmount/2-1, cellAmount/2+1), new Index(2, cellAmount/2 - 1),
                        outStream, Index.opposite(outStream)));
                break;
            case Right:
                field.addFieldObject(new WaterFlowObject(field,
                        new Index(cellAmount/2+1, cellAmount/2-1), new Index(cellAmount/2-1, 2),
                        outStream, Index.opposite(outStream)));
                break;
            case Left:
                field.addFieldObject(new WaterFlowObject(field,
                        new Index(0, cellAmount/2-1), new Index(cellAmount/2-1, 2),
                        outStream, Index.opposite(outStream)));
                break;
            case None:
                break;
        }
    }

    public void addInStream(Index.Direction direction){
        if (outStream != direction && !inStreams.contains(direction)){
            inStreams.add(direction);
        }
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = new ArrayList<>();
        int size = parent.getSize();
        Color color = new Color(Color.Type.Blue);
        if (inStreams.contains(Index.Direction.Up) || outStream == Index.Direction.Up){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, 0),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
            if (outStream == Index.Direction.Up){
                LineShape line1 = new LineShape(new Coord(9*size/20, 3*size/8),
                        new Coord(size/2, size/8), new Color(Color.Type.Black));
                LineShape line2 = new LineShape(new Coord(11*size/20, 3*size/8),
                        new Coord(size/2, size/8), new Color(Color.Type.Black));
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Index.Direction.Down) || outStream == Index.Direction.Down){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, size/2),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
            if (outStream == Index.Direction.Down){
                LineShape line1 = new LineShape(new Coord(9*size/20, 5*size/8),
                        new Coord(size/2, 7*size/8), new Color(Color.Type.Black));
                LineShape line2 = new LineShape(new Coord(11*size/20, 5*size/8),
                        new Coord(size/2, 7*size/8), new Color(Color.Type.Black));
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Index.Direction.Left) || outStream == Index.Direction.Left){
            RectangleShape rect = new RectangleShape(new Coord(0, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
            if (outStream == Index.Direction.Left){
                LineShape line1 = new LineShape(new Coord(3*size/8, 9*size/20),
                        new Coord(size/8, size/2), new Color(Color.Type.Black));
                LineShape line2 = new LineShape(new Coord(3*size/8, 11*size/20),
                        new Coord(size/8, size/2), new Color(Color.Type.Black));
                shapes.add(line1);
                shapes.add(line2);
            }
        }
        if (inStreams.contains(Index.Direction.Right) || outStream == Index.Direction.Right){
            RectangleShape rect = new RectangleShape(new Coord(size/2, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
            if (outStream == Index.Direction.Right){
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

}
