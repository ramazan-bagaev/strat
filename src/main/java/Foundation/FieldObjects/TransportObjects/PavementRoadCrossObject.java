package Foundation.FieldObjects.TransportObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class PavementRoadCrossObject extends TransportNetNodeObject {


    public PavementRoadCrossObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        setShapes();
    }


    @Override
    public void setShapes() {
        clearBasicShapes();
        double cellSize = parent.getCellSize();
        double shift = 0.2;

        RectangleShape rectangleShape = new RectangleShape(new Coord(shift, shift),
                new Coord(cellSize*size.x - 2*shift, cellSize*size.y - 2*shift), new Color(Color.Type.Gray), false, true);

        addBasicShape(rectangleShape);

        for(Index.Direction direction: directions){
            switch (direction){

                case Up:
                    rectangleShape = new RectangleShape(new Coord(shift, 0), new Coord(cellSize*size.x - 2*shift , shift),
                            new Color(Color.Type.Black));
                    addBasicShape(rectangleShape);
                    break;
                case Down:
                    rectangleShape = new RectangleShape(new Coord(shift, cellSize * size.y - shift),
                            new Coord(cellSize*size.x - 2*shift, shift), new Color(Color.Type.Black));
                    addBasicShape(rectangleShape);
                    break;
                case Right:
                    rectangleShape = new RectangleShape(new Coord(cellSize * size.x - shift, shift),
                            new Coord(shift , cellSize*size.y- 2*shift), new Color(Color.Type.Black));
                    addBasicShape(rectangleShape);
                    break;
                case Left:
                    rectangleShape = new RectangleShape(new Coord(0, shift),
                            new Coord(shift , cellSize*size.y - 2 *shift), new Color(Color.Type.Black));
                    addBasicShape(rectangleShape);
                    break;
                case None:
                    break;
            }
        }
    }

    @Override
    public boolean isPavement(){
        return true;
    }
}
