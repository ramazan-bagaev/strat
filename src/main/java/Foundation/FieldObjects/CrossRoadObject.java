package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

public class CrossRoadObject extends FieldObject{

    private ArrayList<Index.Direction> directions;

    public CrossRoadObject(Field parent, Index cellPos, Index size, ArrayList<Index.Direction> directions) {
        super(parent, cellPos, size);
        this.directions = directions;
        setShapes();
    }

    public ArrayList<Index.Direction> getDirections() {
        return directions;
    }

    @Override
    public void setShapes() {
        clearBasicShapes();
        double cellSize = parent.getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize*size.x, cellSize*size.y),
                new Color(Color.Type.Gray), false, true);

        addBasicShape(rectangleShape);
    }
}
