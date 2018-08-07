package Foundation.FieldObjects.BuildingObject;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Works.Occupation.PeasantOccupation;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class PeasantHouseObject extends LivingBuildingObject{

    private Index housePos;

    public PeasantHouseObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        housePos = new Index(size.x/2, size.y/2);
        setShapes();
    }

    @Override
    public void setShapes(){
        clearBasicShapes();

        double cellSize = getParent().getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize*size.x, cellSize * size.y),
                new Color(Color.Type.Yellow2), true, true);
        addBasicShape(rectangleShape);

        rectangleShape = new RectangleShape(new Coord(cellSize*(housePos.x), cellSize*(housePos.y)),
                new Coord(cellSize, cellSize), new Color(Color.Type.Brown), true, true);
        addBasicShape(rectangleShape);
    }

    public void distributeWork(){
       // occupation.initWorks();
       // getParent().getMap().getGameEngine().addRunEntity(occupation);
    }
}
