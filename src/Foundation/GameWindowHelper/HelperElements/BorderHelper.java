package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShape;
import Foundation.Color;
import Foundation.Coord;
import Foundation.GameWindowHelper.HelperField;
import Foundation.LineShape;

import java.util.ArrayList;

public class BorderHelper extends HelperElement{

    private Color color;
    private ArrayList<Coord.Direction> directions;
    private float width;

    public BorderHelper(HelperField helperField, ArrayList<Coord.Direction> directions, Color color, float width) {
        super(helperField);
        this.directions = directions;
        this.color = color;
        this.width = width;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        for (Coord.Direction direction: directions) {
            switch (direction) {
                case Up:
                    basicShapes.add(new LineShape(pos.add(new Coord(1, 1)), pos.add(new Coord(size.x - 1, 1)), color, width));
                    break;
                case Down:
                    basicShapes.add(new LineShape(pos.add(new Coord(1, size.y - 1)), pos.add(new Coord(size.x - 1, size.y - 1)), color, width));
                    break;
                case Right:
                    basicShapes.add(new LineShape(pos.add(new Coord(size.x - 1, 1)), pos.add(new Coord(size.x - 1, size.y - 1)), color, width));
                    break;
                case Left:
                    basicShapes.add(new LineShape(pos.add(new Coord(1, 1)), pos.add(new Coord(1, size.y - 1)), color, width));
                    break;
                case None:
                    break;
            }
        }
    }
}
