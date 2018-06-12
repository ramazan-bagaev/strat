package Foundation.FieldObjects;

import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class StewardBuildingObject extends LivingBuildingObject {

    public StewardBuildingObject(Field parent, Index cellPos) {
        super(parent, cellPos, new Index(3, 3));
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = getParent().getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize*size.x, cellSize * size.y),
                new Color(Color.Type.Gray), true, true);
        addBasicShape(rectangleShape);

        LineShape lineShape = new LineShape(new Coord(), new Coord(cellSize*size.x, cellSize * size.y),
                new Color(Color.Type.Brown));
        addBasicShape(lineShape);

        lineShape = new LineShape(new Coord(0, cellSize*size.x), new Coord(cellSize * size.x, 0),
                new Color(Color.Type.Brown));
        addBasicShape(lineShape);

    }
}
