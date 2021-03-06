package Images;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.TriangleShape;
import Utils.Geometry.Coord;
import Windows.Window;

import java.util.ArrayList;

public class RockImage extends Image {

    private int height;

    public RockImage(Coord pos, Coord size, int height, Window parent) {
        super(pos, size, parent);
        this.height = height;
        setShapes();
    }

    public RockImage(Coord pos, Window parent) {
        super(pos, parent);
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = new ArrayList<>();
        Color color = new Color(Color.Type.Gray);
        color.moreRed(height);
        RectangleShape rectangleShape = new RectangleShape(new Coord(0, 0), size, color, false, true);
        shapes.add(rectangleShape);

        TriangleShape tr1 = new TriangleShape(new Coord(2 *size.x/10, size.y),
                new Coord(size.x/2, size.y / 8),
                new Coord(8*size.x/10, size.y), new Color(Color.Type.LightGray));


        TriangleShape tr2 = new TriangleShape(new Coord(0, size.y),
                new Coord(3*size.x/10, 2*size.y/3),
                new Coord(4 *size.x/10, size.y), new Color(Color.Type.LightGray));


        TriangleShape tr3 = new TriangleShape(new Coord(6*size.x/10, size.y),
                new Coord(8*size.x/10, size.y/2),
                new Coord(size.x, size.y), new Color(Color.Type.LightGray));

        shapes.add(tr1);
        shapes.add(tr2);
        shapes.add(tr3);
        setBasicShapes(shapes);
    }
}
