package Images;

import Foundation.BasicShapes.LineShape;
import Windows.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class TurnArrowImage extends Image {

    public TurnArrowImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        addBasicShapes(new ArrowImage(new Coord(0, size.y/4), new Coord(size.x/2, size.y/2),
                Index.Direction.Up, null).getCopyOfBasicShapesWithoutShift());
        addBasicShapes(new ArrowImage(new Coord(size.x/2, size.y/4), new Coord(size.x/2, size.y/2),
                Index.Direction.Down, null).getCopyOfBasicShapesWithoutShift());
        addBasicShape(new LineShape(new Coord(size.x/2, 3*size.y/4), new Coord(size.x/4, 3* size.y/4)));
        addBasicShape(new LineShape(new Coord(size.x/2, size.y/4), new Coord(3*size.x/4, size.y/4)));
    }
}
