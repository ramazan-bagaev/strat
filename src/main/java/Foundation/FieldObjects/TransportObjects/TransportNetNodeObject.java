package Foundation.FieldObjects.TransportObjects;

import Foundation.Color;
import Foundation.Field;
import Foundation.FieldObjects.BuildingObject.BuildingObject;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Foundation.GameWindowHelper.Modes.InFieldMode.CoveringObjectMode;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Foundation.Recources.ProductBundle;
import Foundation.TransportInfrostructure.TransportNetEdge;
import Foundation.TransportInfrostructure.TransportNetElement;
import Foundation.TransportInfrostructure.TransportNetNode;
import Foundation.Window;
import Utils.Geometry.Index;

import java.util.ArrayList;

public abstract class TransportNetNodeObject extends TransportNetObject implements TransportNetNode{

    protected ArrayList<Index.Direction> directions;

    protected ArrayList<TransportNetElement> elements;

    protected ArrayList<BuildingObject> linkedBuildings;

    protected ArrayList<ProductBundle> bundles;

    public TransportNetNodeObject(Field parent, Index cellPos, Index size) {
        super(parent, cellPos, size);
        this.directions = new ArrayList<>();
        elements = new ArrayList<>();
        linkedBuildings = new ArrayList<>();
        bundles = new ArrayList<>();
    }

    public void addLinkedBuilding(BuildingObject buildingObject){
        linkedBuildings.add(buildingObject);
    }

    public void addNetElement(TransportNetObject road){
        elements.add(road);
        Index.Direction side = getSameSide(road);
        if (side == Index.Direction.None) return;
        directions.add(side);
        setShapes();
    }

    public void removeNetElement(TransportNetObject road){
        elements.remove(road);
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
    public ArrayList<TransportNetElement> getElements() {
        return elements;
    }

    @Override
    public TransportNetEdge getEdge(TransportNetNode transportNode) {
        for(TransportNetElement element: elements){
            if (element.isEdge()){
                TransportNetEdge edge = (TransportNetEdge)element;
                TransportNetNode node = edge.getOppositeNode(this);
                if (node != null) return edge;
            }
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

    @Override
    public Mode getModeOnClick(GameWindowHelperElement gameWindowHelperElement){
        ArrayList<TransportNetObject> transportNetObjects = new ArrayList<>();
        for(TransportNetElement element: elements){
            transportNetObjects.add((TransportNetObject)element);
        }
        return new CoveringObjectMode(gameWindowHelperElement, transportNetObjects, new Color(Color.Type.Yellow, 0.5f));
    }
}
