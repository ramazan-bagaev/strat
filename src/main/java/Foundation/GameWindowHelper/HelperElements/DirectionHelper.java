package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.LineShape;
import Foundation.GameWindowHelper.HelperField;
import Images.ArrowImage;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class DirectionHelper extends HelperElement {

    private Index.Direction from;
    private Index.Direction to;

    public DirectionHelper(HelperField helperField, Index.Direction from, Index.Direction to) {
        super(helperField);
        this.from = from;
        this.to = to;
        setShapes();
    }

    /*public void setShapes() {
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        LineShape lineShape;
        switch (to) {

            case Up:
                lineShape = new LineShape(new Coord(pieceSize.x/2, 0), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(pieceSize.x/4, pieceSize.y/4), new Coord(pieceSize.x/2, 0), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(3*pieceSize.x/4, pieceSize.y/4), new Coord(pieceSize.x/2, 0), 2);
                basicShapes.add(lineShape);
                break;
            case Down:
                lineShape = new LineShape(new Coord(pieceSize.x/2, pieceSize.y), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(pieceSize.x/4, 3*pieceSize.y/4), new Coord(pieceSize.x/2, pieceSize.y), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(3*pieceSize.x/4, 3*pieceSize.y/4), new Coord(pieceSize.x/2, pieceSize.y), 2);
                basicShapes.add(lineShape);
                break;
            case Right:
                lineShape = new LineShape(new Coord(pieceSize.x, pieceSize.y/2), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(3*pieceSize.x/4, pieceSize.y/4), new Coord(pieceSize.x, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(pieceSize.x, pieceSize.y/2), new Coord(3*pieceSize.x/4, 3*pieceSize.y/4), 2);
                basicShapes.add(lineShape);
                break;
            case Left:
                lineShape = new LineShape(new Coord(0, pieceSize.y/2), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(pieceSize.x/4, pieceSize.y/4), new Coord(0, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                lineShape = new LineShape(new Coord(pieceSize.x/4, 3*pieceSize.y/4), new Coord(0, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                break;
            case None:
                break;
        }

        switch (from) {

            case Up:
                lineShape = new LineShape(new Coord(pieceSize.x/2, 0), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                break;
            case Down:
                lineShape = new LineShape(new Coord(pieceSize.x/2, pieceSize.y), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                break;
            case Right:
                lineShape = new LineShape(new Coord(pieceSize.x, pieceSize.y/2), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                break;
            case Left:
                lineShape = new LineShape(new Coord(0, pieceSize.y/2), new Coord(pieceSize.x/2, pieceSize.y/2), 2);
                basicShapes.add(lineShape);
                break;
            case None:
                break;
        }

        setBasicShapes(basicShapes);
    }*/

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        switch (to){

            case Up:
                basicShapes.addAll( new ArrowImage(new Coord(size.x/3, size.y/8), new Coord(size.x/3, size.y/3),
                        Index.Direction.Up,null).getCopyOfBasicShapesWithoutShift());
                break;
            case Down:
                basicShapes.addAll(new ArrowImage(new Coord(size.x/3, size.y/2 + size.y/8), new Coord(size.x/3, size.y/3),
                        Index.Direction.Down,null).getCopyOfBasicShapesWithoutShift());
                break;
            case Right:
                basicShapes.addAll(new ArrowImage(new Coord(size.x/2 + size.x/8, size.y/3), new Coord(size.x/3, size.y/3),
                        Index.Direction.Right,null).getCopyOfBasicShapesWithoutShift());
                break;
            case Left:
                basicShapes.addAll(new ArrowImage(new Coord(size.x/8, size.y/3), new Coord(size.x/3, size.y/3),
                        Index.Direction.Left,null).getCopyOfBasicShapesWithoutShift());
                break;
            case None:
                break;
        }

        LineShape lineShape;

        switch (from) {

            case Up:
                basicShapes.addAll( new ArrowImage(new Coord(size.x/3, size.y/8), new Coord(size.x/3, size.y/3),
                        Index.Direction.Down,null).getCopyOfBasicShapesWithoutShift());
                break;
            case Down:
                basicShapes.addAll(new ArrowImage(new Coord(size.x/3, size.y/2 + size.y/8), new Coord(size.x/3, size.y/3),
                        Index.Direction.Up,null).getCopyOfBasicShapesWithoutShift());
                break;
            case Right:
                basicShapes.addAll(new ArrowImage(new Coord(size.x/2 + size.x/8, size.y/3), new Coord(size.x/3, size.y/3),
                        Index.Direction.Left,null).getCopyOfBasicShapesWithoutShift());
                break;
            case Left:
                basicShapes.addAll(new ArrowImage(new Coord(size.x/8, size.y/3), new Coord(size.x/3, size.y/3),
                        Index.Direction.Right,null).getCopyOfBasicShapesWithoutShift());
                break;
            case None:
                break;
        }

        setBasicShapes(basicShapes);
    }
}
