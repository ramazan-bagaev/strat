package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

public class CityHouseObject extends LivingBuildingObject {

    public CityHouseObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = getParent().getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize*size.x, cellSize * size.y),
                new Color(Color.Type.Brown), true, true);
        addBasicShape(rectangleShape);

        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x; x++){
                rectangleShape = new RectangleShape(new Coord(cellSize*(x+0.3), cellSize*(y+0.3)),
                        new Coord(cellSize*0.4, cellSize*0.4), new Color(Color.Type.Black), true, true);
                addBasicShape(rectangleShape);
            }
        }
    }
}
