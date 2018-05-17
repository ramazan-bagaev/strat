package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Coord;
import Utils.Index;

public class MarketObject extends BuildingObject {

    public MarketObject(Field parent, Index cellPos) {
        super(parent, cellPos, new Index(4, 4));
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = getParent().getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(size.x * cellSize, size.y * cellSize),
                new Color(Color.Type.Gray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(size.x * cellSize/8, size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(5*size.x * cellSize/8, size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(size.x * cellSize/8, 5*size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(5*size.x * cellSize/8, 5*size.y * cellSize/8),
                new Coord(size.x * cellSize/4, size.y * cellSize/4), new Color(Color.Type.LightGray), true, true);

        addBasicShape(rectangleShape);
    }
}
