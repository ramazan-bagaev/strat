package Foundation.TransportInfrostructure;

public interface TransportNetEdge{

    TransportNetNode getOppositeNode(TransportNetNode node);

    TransportNetNode getFirstNode();

    TransportNetNode getSecondNode();

    int getLength();

    int getCapacity();
}
