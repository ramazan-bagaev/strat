package Foundation.TransportInfrostructure;

import java.util.ArrayList;
import java.util.LinkedList;

public class TransportNetPath {

    private LinkedList<TransportNetNode> nodes;
    private int length;

    public TransportNetPath(){
        nodes = new LinkedList<>();
        length = 0;
    }

    public TransportNetNode getLastNode(){
        if (nodes.size() > 0) return nodes.get(nodes.size() - 1);
        return null;
    }

    public TransportNetNode getFirstNode(){
        if (nodes.size() > 0) return nodes.get(0);
        return null;
    }

    public TransportNetNode getNode(int index){
        if (index < 0 || index >= nodes.size()) return null;
        return nodes.get(index);
    }

    public ArrayList<TransportNetNode> getVisitedNodes(int maxDistanceBetweenNodes){
        if (nodes.size() == 0) return null;
        ArrayList<TransportNetNode> result = new ArrayList<>();
        int curLength = 0;
        TransportNetNode lastVisitedNode = nodes.get(0);
        result.add(nodes.get(0));
        for(int i = 1; i < nodes.size()-1; i++){
            TransportNetNode node = nodes.get(i);
            curLength += node.getEdge(lastVisitedNode).getLength();
            if (curLength > maxDistanceBetweenNodes){
                result.add(node);
                curLength = 0;
                lastVisitedNode = node;
            }
        }
        if (nodes.size() - 1 == 0) return result;
        result.add(nodes.get(nodes.size() - 1));
        return result;
    }

    public boolean addNodeToTail(TransportNetNode node){
        TransportNetNode lastNode = getLastNode();
        if (lastNode == null){
            nodes.addLast(node);
            length = 0;
            return true;
        }
        TransportNetEdge edge = lastNode.getEdge(node);
        if (edge != null){
            nodes.addLast(node);
            length += edge.getLength();
            return true;
        }
        return false;
    }

    public boolean addNodeToHead(TransportNetNode node){
        TransportNetNode firstNode = getFirstNode();
        if (firstNode == null){
            nodes.addFirst(node);
            length = 0;
            return true;
        }
        TransportNetEdge edge = firstNode.getEdge(node);
        if (edge != null){
            nodes.addFirst(node);
            length += edge.getLength();
            return true;
        }
        return false;
    }

    public LinkedList<TransportNetNode> getNodes() {
        return nodes;
    }

    public int getLength(){
        return length;
    }
}
