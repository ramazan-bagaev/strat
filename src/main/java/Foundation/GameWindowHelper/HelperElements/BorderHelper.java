package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.Color;
import Utils.Geometry.Index;
import Foundation.GameWindowHelper.HelperField;
import Foundation.BasicShapes.LineShape;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class BorderHelper extends HelperElement{

    private Color color;
    private ArrayList<Index.Direction> directions;
    private float width;

    public BorderHelper(HelperField helperField, ArrayList<Index.Direction> directions, Color color, float width) {
        super(helperField);
        this.directions = directions;
        this.color = color;
        this.width = width;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();

        for (Index.Direction direction: directions) {
            switch (direction) {
                case Up:
                    basicShapes.add(new LineShape(new Coord(0, 0), new Coord(size.x - 1, 0), color, width));
                    break;
                case Down:
                    basicShapes.add(new LineShape(new Coord(0, size.y - 1), new Coord(size.x - 1, size.y - 1), color, width));
                    break;
                case Right:
                    basicShapes.add(new LineShape(new Coord(size.x - 1, 0), new Coord(size.x - 1, size.y - 1), color, width));
                    break;
                case Left:
                    basicShapes.add(new LineShape(new Coord(0, 0), new Coord(0, size.y - 1), color, width));
                    break;
                case None:
                    break;
            }
        }
        setBasicShapes(basicShapes);
    }
}
