package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShape;
import Foundation.Color;
import Foundation.Coord;
import Foundation.GameWindowHelper.HelperField;
import Foundation.LineShape;

import java.util.ArrayList;

public class BorderHelper extends HelperElement{

    private final int ro = 5;
    private final int wi = 20;

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
                    basicShapes.add(new LineShape(pos.add(new Coord(0, 0)), pos.add(new Coord(size.x - 1, 0)), color, width));
                    addCleats(basicShapes, Coord.Direction.Up);
                    break;
                case Down:
                    basicShapes.add(new LineShape(pos.add(new Coord(0, size.y - 1)), pos.add(new Coord(size.x - 1, size.y - 1)), color, width));
                    addCleats(basicShapes, Coord.Direction.Down);
                    break;
                case Right:
                    basicShapes.add(new LineShape(pos.add(new Coord(size.x - 1, 0)), pos.add(new Coord(size.x - 1, size.y - 1)), color, width));
                    addCleats(basicShapes, Coord.Direction.Right);
                    break;
                case Left:
                    basicShapes.add(new LineShape(pos.add(new Coord(0, 0)), pos.add(new Coord(0, size.y - 1)), color, width));
                    addCleats(basicShapes, Coord.Direction.Left);
                    break;
                case None:
                    break;
            }
        }
    }

    public void addCleats(ArrayList<BasicShape> basicShapes, Coord.Direction direction){
        switch (direction){
            case Up:
                for(int i = 1; i < size.x/ro; i++){
                    basicShapes.add(new LineShape(pos.add(new Coord(i * ro, 0)), pos.add(new Coord(i * ro + ro/2, size.y/wi)), color));
                }
                break;
            case Down:
                for(int i = 1; i < size.x/ro; i++){
                    basicShapes.add(new LineShape(pos.add(new Coord(i * ro, size.y-1)), pos.add(new Coord(i * ro - ro/2, size.y - 1 - size.y/wi)), color));
                }
                break;
            case Right:
                for(int i = 1; i < size.y/ro; i++){
                    basicShapes.add(new LineShape(pos.add(new Coord(size.x-1, i * ro)), pos.add(new Coord(size.x - 1 - size.x/wi, i * ro + ro/2)), color));
                }
                break;
            case Left:
                for(int i = 1; i < size.y/ro; i++){
                    basicShapes.add(new LineShape(pos.add(new Coord(0, i * ro)), pos.add(new Coord(size.x/wi, i * ro - ro/2)), color));
                }
                break;
            case None:
                break;
        }
    }
}
