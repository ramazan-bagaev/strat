package Foundation.FieldObjects.BuildingObject;

import Foundation.BasicShapes.LineShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class ManorObject extends LivingBuildingObject {

    public ManorObject(Field parent, Index pos) {
        super(parent, pos, new Index(6, 6));
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = getParent().getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize*size.x, cellSize*size.y),
                new Color(Color.Type.LightGray), true, true);
        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(cellSize*size.x/4, cellSize*size.y/4), new Coord(cellSize*size.x/2, cellSize*size.y/2),
                new Color(Color.Type.Gray), true, true);
        addBasicShape(rectangleShape);

        LineShape lineShape = new LineShape(new Coord(cellSize*size.x/4, cellSize*size.y/4),
                new Coord(3*cellSize*size.x/4, 3*cellSize*size.y/4));
        addBasicShape(lineShape);

        lineShape = new LineShape(new Coord(3*cellSize*size.x/4, cellSize*size.y/4),
                new Coord(cellSize*size.x/4, 3*cellSize*size.y/4));
        addBasicShape(lineShape);


    }
}
