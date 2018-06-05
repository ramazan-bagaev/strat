package Foundation.Elements;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.FieldObjects.TransportObjects.PavementRoadCrossObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.TransportObjects.PavementRoadObject;
import Foundation.Time;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

public class Road extends FieldElement{

    private ArrayList<Index.Direction> sides;

    public Road(Time time, Field parent, FieldMap map, ArrayList<Index.Direction> sides) {
        super(Type.Road, time, parent, map);
        this.sides = sides;
        fillField();
        setShapes();
    }

    public void fillField(){
        Field field = getParent();
        FieldObjects fieldObject = field.getFieldObjects();
        int cellAmount = field.getCellAmount();

        fieldObject.addTransportNetElement(new PavementRoadCrossObject(field, new Index(cellAmount/2-1, cellAmount/2-1), new Index(2, 2)));
        for(Index.Direction direction: sides){
            switch (direction){

                case Up:
                    fieldObject.addTransportNetElement(new PavementRoadObject(field,
                            new Index(cellAmount/2-1, 0), new Index(2, cellAmount/2 - 1), true));
                    break;
                case Down:
                    fieldObject.addTransportNetElement(new PavementRoadObject(field,
                            new Index(cellAmount/2-1, cellAmount/2+1), new Index(2, cellAmount/2 - 1), true));
                    break;
                case Right:
                    fieldObject.addTransportNetElement(new PavementRoadObject(field,
                            new Index(cellAmount/2+1, cellAmount/2-1), new Index(cellAmount/2-1, 2), false));
                    break;
                case Left:
                    fieldObject.addTransportNetElement(new PavementRoadObject(field,
                            new Index(0, cellAmount/2-1), new Index(cellAmount/2-1, 2), false));
                    break;
                case None:
                    break;
            }
        }
    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = new ArrayList<>();
        int size = parent.getSize();
        Color color = new Color(Color.Type.Brown);
        if (sides.contains(Index.Direction.Up)){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, 0),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
        }
        if (sides.contains(Index.Direction.Down)){
            RectangleShape rect = new RectangleShape(new Coord(9*size/20, size/2),
                    new Coord(size/10, size/2), color, false, true);
            shapes.add(rect);
        }
        if (sides.contains(Index.Direction.Left)){
            RectangleShape rect = new RectangleShape(new Coord(0, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
        }
        if (sides.contains(Index.Direction.Right)){
            RectangleShape rect = new RectangleShape(new Coord(size/2, 9*size/20),
                    new Coord(size/2, size/10), color, false, true);
            shapes.add(rect);
        }
        RectangleShape rect = new RectangleShape(new Coord(9*size/20, 9 * size/20),
                new Coord(size/10, size/10), color, false, true);
        shapes.add(rect);
        setBasicShapes(shapes);
    }
}
