package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Works.MeatMakingWork;
import Foundation.Works.Occupation.PeasantOccupation;
import Foundation.Works.VegetableMakingWork;
import Foundation.Works.WheatMakingWork;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;
import java.util.Random;

public class PeasantHouseObject extends LivingBuildingObject{

    private Index housePos;
    private PeasantOccupation occupation;

    public PeasantHouseObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        housePos = new Index(size.x/2, size.y/2);
        occupation = new PeasantOccupation(this);
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
        occupation.initWorks();
        getParent().getMap().getGameEngine().addRunEntity(occupation);
    }
}
