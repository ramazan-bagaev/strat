package Foundation.FieldObjects.NaturalObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Windows.Window;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class ForestObject extends NaturalObject {

    private int woodAmount;

    public ForestObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        woodAmount = size.y * size.x * 1000;
        setShapes();
    }

    @Override
    public void setShapes() {
        clearBasicShapes();

        double cellSize = parent.getCellSize();

        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(cellSize * size.x, cellSize * size.y),
                new Color(Color.Type.Green3), false, true);
        addBasicShape(rectangleShape);
    }

    @Override
    public Window getInfoWindow() {
        return null;
    }

    @Override
    public boolean isForestObject(){
        return true;
    }

    public int getSquare(){
        return size.x * size.y;
    }

    public int getWoodAmount() {
        return woodAmount;
    }

    public void decreaseWoodAmount(int amount){
        woodAmount -= amount;
    }
}
