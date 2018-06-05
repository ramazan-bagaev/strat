package Foundation.FieldObjects.TransportObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.FieldObjects.TransportObjects.TransportNetEdgeObject;
import Foundation.TransportInfrostructure.TransportNetNode;
import Foundation.Window;
import Utils.Coord;
import Utils.Index;

public class PavementRoadObject extends TransportNetEdgeObject {


    public PavementRoadObject(Field parent, Index cellPos, Index size, boolean vertical) {
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
                    new Color(Color.Type.Gray), false, true);
        }
        else {
            rectangleShape = new RectangleShape(new Coord(0, shift), new Coord(cellSize*size.x, cellSize*size.y - 2 * shift),
                    new Color(Color.Type.Gray), false, true);
        }
        addBasicShape(rectangleShape);
    }

    @Override
    public boolean isPavement(){
        return true;
    }
}
