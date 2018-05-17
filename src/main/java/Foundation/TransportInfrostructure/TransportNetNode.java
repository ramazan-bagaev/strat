package Foundation.TransportInfrostructure;

import java.util.ArrayList;

public interface TransportNetNode{

    ArrayList<TransportNetEdge> getEdges();

    TransportNetEdge getEdge(TransportNetNode transportNode);
}
