package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

public class RoadObject extends FieldObject {

    private boolean vertical; // true -> vertical; false -> horizontal

    public RoadObject(Field parent, Index cellPos, Index size, boolean vertical) {
        super(parent, cellPos, size);
        this.vertical = vertical;
        setShapes();
    }

    public int getCapacity(){
        if (vertical) return size.x;
        return size.y;
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
