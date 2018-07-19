package Foundation.TransportInfrostructure;

import java.util.ArrayList;

public interface TransportNetNode{

    ArrayList<TransportNetElement> getElements();

    TransportNetEdge getEdge(TransportNetNode transportNode);
}
