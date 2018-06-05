package Foundation.FieldObjects.TransportObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

public class PrimingRoadObject extends TransportNetEdgeObject {

    public PrimingRoadObject(Field parent, Index cellPos, Index size, boolean vertical) {
        super(parent, cellPos, size, vertical);
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();
        double cellSize = parent.getCellSize();
        RectangleShape rectangleShape;
        double shift = 0.1;
        if (vertical){
            rectangleShape = new RectangleShape(new Coord(shift, 0), new Coord(cellSize*size.x - 2*shift, cellSize*size.y),
                    new Color(Color.Type.Brown), false, true);
        }
        else {
            rectangleShape = new RectangleShape(new Coord(0, shift), new Coord(cellSize*size.x, cellSize*size.y - 2 * shift),
                    new Color(Color.Type.Brown), false, true);
        }
        addBasicShape(rectangleShape);
    }

    @Override
    public boolean isPriming(){
        return true;
    }
}
