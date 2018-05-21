package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.TransportInfrostructure.TransportNetEdge;
import Foundation.TransportInfrostructure.TransportNetNode;
import Utils.Coord;
import Utils.Index;

import java.util.ArrayList;

public class CrossRoadObject extends TransportNetObject implements TransportNetNode {

    private ArrayList<Index.Direction> directions;

    private ArrayList<TransportNetEdge> edges;

    private ArrayList<BuildingObject> linkedBuildings;

    public CrossRoadObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        this.directions = new ArrayList<>();
        edges = new ArrayList<>();
        linkedBuildings = new ArrayList<>();
        setShapes();
    }

    public void addLinkedBuilding(BuildingObject buildingObject){
        linkedBuildings.add(buildingObject);
    }

    public void addEdge(RoadObject road){
        edges.add(road);
        Index.Direction side = getSameSide(road);
        if (side == Index.Direction.None) return;
        directions.add(side);
        setShapes();
    }

    public void removeEdge(RoadObject road){
        edges.remove(road);
        Index.Direction side = getSameSide(road);
        if (side == Index.Direction.None) return;
        directions.remove(side);
        setShapes();
    }

    public ArrayList<Index.Direction> getDirections() {
        return directions;
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
    public ArrayList<TransportNetEdge> getEdges() {
        return edges;
    }

    @Override
    public TransportNetEdge getEdge(TransportNetNode transportNode) {
        for(TransportNetEdge edge: edges){
            TransportNetNode node = edge.getOppositeNode(this);
            if (node != null) return edge;
        }
        return null;
    }

    @Override
    public boolean isNode() {
        return true;
    }
}
