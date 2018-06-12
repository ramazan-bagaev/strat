package Images;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.LineShape;
import Foundation.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class ArrowImage extends Image {

    private Index.Direction direction;

    public ArrowImage(Coord pos, Coord size, Index.Direction direction, Window parent) {
        super(pos, size, parent);
        this.direction = direction;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();

        switch (direction){

            case Up:
                basicShapes.add(new LineShape(new Coord(size.x/2, 0), new Coord(size.x/2, size.y), 2));
                basicShapes.add(new LineShape(new Coord(size.x/3, size.y/3), new Coord(size.x/2, 0), 2));
                basicShapes.add(new LineShape(new Coord(2*size.x/3, size.y/3), new Coord(size.x/2, 0), 2));
                break;
            case Down:
                basicShapes.add(new LineShape(new Coord(size.x/2, 0), new Coord(size.x/2, size.y), 2));
                basicShapes.add(new LineShape(new Coord(size.x/3, 2*size.y/3), new Coord(size.x/2, size.y), 2));
                basicShapes.add(new LineShape(new Coord(2*size.x/3, 2*size.y/3), new Coord(size.x/2, size.y), 2));
                break;
            case Right:
                basicShapes.add(new LineShape(new Coord(0, size.y/2), new Coord(size.x, size.y/2), 2));
                basicShapes.add(new LineShape(new Coord(2*size.x/3, size.y/3), new Coord(size.x, size.y/2), 2));
                basicShapes.add(new LineShape(new Coord(2*size.x/3, 2*size.y/3), new Coord(size.x, size.y/2), 2));
                break;
            case Left:
                basicShapes.add(new LineShape(new Coord(0, size.y/2), new Coord(size.x, size.y/2), 2));
                basicShapes.add(new LineShape(new Coord(size.x/3, size.y/3), new Coord(0, size.y/2), 2));
                basicShapes.add(new LineShape(new Coord(size.x/3, 2*size.y/3), new Coord(0, size.y/2), 2));
                break;
            case None:
                break;
        }

        setBasicShapes(basicShapes);
    }
}
