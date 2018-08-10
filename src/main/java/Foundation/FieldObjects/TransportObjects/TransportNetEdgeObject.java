package Foundation.FieldObjects.TransportObjects;

import Foundation.Field;
import Foundation.TransportInfrostructure.TransportNetEdge;
import Foundation.TransportInfrostructure.TransportNetNode;
import Windows.Window;
import Utils.Geometry.Index;

public abstract class TransportNetEdgeObject extends TransportNetObject implements TransportNetEdge, RoadType {

    protected boolean vertical; // true -> vertical; false -> horizontal

    protected TransportNetNode first;
    protected TransportNetNode second;

    public TransportNetEdgeObject(Field parent, Index cellPos, Index size, boolean vertical) {
        super(parent, cellPos, size);
        this.vertical = vertical;
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

    public void removeNode(TransportNetNode node){
        if (first == node){
            first = null;
            return;
        }
        if (second == node){
            second = null;
            return;
        }
    }

    public boolean isVertical(){
        return vertical;
    }

    @Override
    public Window getInfoWindow() {
        return null;
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
    public boolean isEdge(){
        return true;
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

    public static TransportNetEdgeObject createRoadWithSameType(TransportNetObject netObject, Field parent, Index pos, Index size, boolean vertical){
        if (netObject.isPavement()){
            return new PavementRoadObject(parent, pos, size, vertical);
        }
        if (netObject.isPriming()){
            return new PrimingRoadObject(parent, pos, size, vertical);
        }
        return null;
    }
}
