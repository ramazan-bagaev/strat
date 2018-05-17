package Foundation.FieldObjects;

import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Field;
import Foundation.TransportInfrostructure.TransportNetEdge;
import Foundation.TransportInfrostructure.TransportNetNode;
import Utils.Coord;
import Utils.Index;

public class RoadObject extends TransportNetObject implements TransportNetEdge{

    private boolean vertical; // true -> vertical; false -> horizontal

    private TransportNetNode first;
    private TransportNetNode second;

    public RoadObject(Field parent, Index cellPos, Index size, boolean vertical) {
        super(parent, cellPos, size);
        this.vertical = vertical;
        setShapes();
    }

    public void setFirstNode(TransportNetNode node) {
        this.first = node;
    }

    public void setSecondNode(TransportNetNode node) {
        this.second = node;
    }

    public void setNode(TransportNetNode node){
        if (first == null){
            first = node;
            return;
        }
        if (second == null){
            second = node;
            return;
        }
    }

    public boolean isVertical(){
        return vertical;
    }

    @Override
    public void setShapes() {
        clearBasicShapes();
        double cellSize = parent.getCellSize();
        RectangleShape rectangleShape;
        double shift = 0.1;
        if (vertical){
            rectangleShape = new RectangleShape(new Coord(shift, 0), new Coord(cellSize*size.x - 2*shift, cellSize*size.y),
                    new Color(Color.Type.Gray), false, true);
        }
        else {
            rectangleShape = new RectangleShape(new Coord(0, shift), new Coord(cellSize*size.x, cellSize*size.y - 2 * shift),
                    new Color(Color.Type.Gray), false, true);
        }
        addBasicShape(rectangleShape);
    }

    @Override
    public TransportNetNode getOppositeNode(TransportNetNode node) {
        if (node.equals(first) && !node.equals(second)) return second;
        if (!node.equals(first) && node.equals(second)) return first;
        return null;
    }

    @Override
    public TransportNetNode getFirstNode() {
        return first;
    }

    @Override
    public TransportNetNode getSecondNode() {
        return second;
    }

    @Override
    public int getLength() {
        if (vertical) return size.y;
        return size.x;
    }

    @Override
    public int getCapacity() {
        if (vertical) return size.x;
        return size.y;
    }

    @Override
    public boolean isEdge() {
        return true;
    }
}
