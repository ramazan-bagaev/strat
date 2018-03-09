package Images;

import Foundation.*;

import java.util.ArrayList;

public class RockImage extends Image {

    public RockImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public RockImage(Coord pos, Window parent) {
        super(pos, parent);
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = new ArrayList<>();
        RectangleShape rectangleShape = new RectangleShape(pos, size, BasicShape.Color.Gray, true);
        shapes.add(rectangleShape);

        TriangleShape tr1 = new TriangleShape(pos.add(new Coord(2 *size.x/10, size.y)),
                pos.add(new Coord(size.x/2, size.y / 8)),
                pos.add(new Coord(8*size.x/10, size.y)), BasicShape.Color.LightGray);


        TriangleShape tr2 = new TriangleShape(pos.add(new Coord(0, size.y)),
                pos.add(new Coord(3*size.x/10, 2*size.y/3)),
                pos.add(new Coord(4 *size.x/10, size.y)), BasicShape.Color.LightGray);


        TriangleShape tr3 = new TriangleShape(pos.add(new Coord(6*size.x/10, size.y)),
                pos.add(new Coord(8*size.x/10, size.y/2)),
                pos.add(new Coord(size.x, size.y)), BasicShape.Color.LightGray);

        shapes.add(tr1);
        shapes.add(tr2);
        shapes.add(tr3);
        setBasicShapes(shapes);
    }
}
