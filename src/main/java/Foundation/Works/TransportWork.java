package Foundation.Works;

import Foundation.FieldObjects.BuildingObject.BuildingObject;
import Foundation.FieldObjects.TransportObjects.TransportNetNodeObject;
import Foundation.Person.People;
import Foundation.Products.ProductBundle;
import Foundation.Products.ProductStore;
import Foundation.TransportInfrostructure.TransportNetNode;
import Foundation.TransportInfrostructure.TransportNetPath;
import Foundation.TransportInfrostructure.TransportNetPathFinder;
import Foundation.Works.Occupation.Occupation;

import java.util.ArrayList;

public class TransportWork extends Work{

    protected ProductBundle bundle;
    protected BuildingObject source;
    protected BuildingObject destination;
    protected TransportNetPath path;
    protected ArrayList<TransportNetNode> visitedNodes;

    public TransportWork(People people, Occupation occupation, BuildingObject source,
                         BuildingObject destination, ProductBundle productBundle) {
        super(people, occupation);
        this.source = source;
        this.destination = destination;
        this.bundle = productBundle;
        initPath();
    }

    public void initPath(){
        TransportNetNodeObject begin = source.getTransportNetEntranceNode();
        TransportNetNodeObject end = destination.getTransportNetEntranceNode();
        if (begin == null || end == null){
            endStage = -1;
            return;
        }
        TransportNetPathFinder pathFinder = new TransportNetPathFinder();
        path = pathFinder.getPath(begin, end);
        if (path == null){
            endStage = -1;
            return;
        }
        visitedNodes = path.getVisitedNodes(100); // TODO: magic constant
        endStage = visitedNodes.size() - 1;
    }

    @Override
    protected void doMainWork() {
        if (endStage == -1) return;
        TransportNetNodeObject node = (TransportNetNodeObject)visitedNodes.get(stage);
        node.removeBundle(bundle);
        node.addBundle(bundle);
    }
}
