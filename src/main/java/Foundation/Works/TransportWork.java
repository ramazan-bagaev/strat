package Foundation.Works;

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
    protected ProductStore source;
    protected ProductStore destination;
    protected TransportNetPath path;
    protected ArrayList<TransportNetNode> visitedNodes;
    protected int position; // from 0 to visitedNodes.size()

    public TransportWork(People people, Occupation occupation, ProductStore source, ProductStore destination) {
        super(people, occupation);
        this.source = source;
        this.destination = destination;
        bundle = new ProductBundle(100);
        initPath();
    }

    public void initPath(){
        TransportNetNodeObject begin = source.getStorePlace().getTransportNetEntranceNode();
        TransportNetNodeObject end = source.getStorePlace().getTransportNetEntranceNode();
        TransportNetPathFinder pathFinder = new TransportNetPathFinder();
        path = pathFinder.getPath(begin, end);
        visitedNodes = path.getVisitedNodes(100);
        position = 0;
    }

    @Override
    public int getDuration(){
        return visitedNodes.size() - 1;
    }

    @Override
    public boolean isFinished(){
        if (getDuration() == 0) return true;
        return (position == getDuration());
    }

    @Override
    protected void doMainWork() {
        TransportNetNodeObject node = (TransportNetNodeObject)visitedNodes.get(position);
        node.removeBundle(bundle);
        position++;
        node.addBundle(bundle);
    }
}
