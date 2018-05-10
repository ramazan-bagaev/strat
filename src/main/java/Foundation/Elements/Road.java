package Foundation.Elements;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

public class Road extends FieldElement{

    private ArrayList<Index.Direction> sides;

    public Road(Time time, Field parent, FieldMap map, ArrayList<Index.Direction> sides) {
        super(Type.Road, time, parent, map);
        this.sides = sides;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = new ArrayList<>();
        int size = parent.getSize();
        Color color = new Color(Color.Type.Brown);
        if (sides.contains(Index.Direction.Up)){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, 0),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
        }
        if (sides.contains(Index.Direction.Down)){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, size/2),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
        }
        if (sides.contains(Index.Direction.Right)){
            RectangleShape rect = new RectangleShape(new Coord(0, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
        }
        if (sides.contains(Index.Direction.Left)){
            RectangleShape rect = new RectangleShape(new Coord(size/2, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
        }
        RectangleShape rect = new RectangleShape(new Coord(9*size/20, 9 * size/20),
                new Coord(size/10, size/10), color, false, true);
        shapes.add(rect);
        setBasicShapes(shapes);
    }
}
