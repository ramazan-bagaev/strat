package Foundation.FieldObjects.TransportObjects;

import Foundation.Field;
import Foundation.FieldObjects.BuildingObject;
import Foundation.Recources.ProductBundle;
import Foundation.TransportInfrostructure.TransportNetEdge;
import Foundation.TransportInfrostructure.TransportNetNode;
import Foundation.Window;
import Utils.Geometry.Index;

import java.util.ArrayList;

public abstract class TransportNetNodeObject extends TransportNetObject implements TransportNetNode{

    protected ArrayList<Index.Direction> directions;

    protected ArrayList<TransportNetEdge> edges;

    protected ArrayList<BuildingObject> linkedBuildings;

    protected ArrayList<ProductBundle> bundles;

    public TransportNetNodeObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        this.directions = new ArrayList<>();
        edges = new ArrayList<>();
        linkedBuildings = new ArrayList<>();
        bundles = new ArrayList<>();
    }

    public void addLinkedBuilding(BuildingObject buildingObject){
        linkedBuildings.add(buildingObject);
    }

    public void addEdge(TransportNetEdgeObject road){
        edges.add(road);
        Index.Direction side = getSameSide(road);
        if (side == Index.Direction.None) return;
        directions.add(side);
        setShapes();
    }

    public void removeEdge(TransportNetEdgeObject road){
        edges.remove(road);
        Index.Direction side = getSameSide(road);
        if (side == Index.Direction.None) return;
        directions.remove(side);
        setShapes();
    }

    public ArrayList<Index.Direction> getDirections() {
        return directions;
    }

    public ArrayList<ProductBundle> getBundles() {
        return bundles;
    }

    public void addBundle(ProductBundle bundle){
        bundles.add(bundle);
    }

    public void removeBundle(ProductBundle bundle){
        bundles.remove(bundle);
    }

    @Override
    public Window getInfoWindow() {
        return null;
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
    public boolean isNode(){
        return true;
    }

    public static TransportNetNodeObject createRoadCrossWithSameType(TransportNetObject netObject, Field parent, Index pos, Index size){
        if (netObject.isPavement()){
            return new PavementRoadCrossObject(parent, pos, size);
        }
        if (netObject.isPriming()){
            return new PrimingRoadCrossObject(parent, pos, size);
        }
        return null;
    }
}
