package Foundation.TransportInfrostructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TransportNetPathFinder {

    private LinkedList<TransportNetNode> queue;
    private HashMap<TransportNetNode, Integer> depth;

    public TransportNetPathFinder(){
        queue = new LinkedList<>();
        depth = new HashMap<>();
    }

    public void init(TransportNetNode start){
        depth.clear();
        depth.put(start, 0);
        queue.clear();
        queue.addLast(start);
    }

    public void prolongAnalysis(TransportNetNode node){
        ArrayList<TransportNetEdge> edges = node.getEdges();
        for(TransportNetEdge edge: edges){
            TransportNetNode nextNode = edge.getOppositeNode(node);
            int newDepth = depth.get(node) + edge.getLength();
            int oldDepth = depth.getOrDefault(nextNode, newDepth + 1);
            if (oldDepth > newDepth){
                depth.put(nextNode, newDepth);
                queue.addLast(nextNode);
            }
        }
    }

    public TransportNetPath getConstructedPathTo(TransportNetNode finish){
        TransportNetPath path = new TransportNetPath();
        path.addNodeToTail(finish);
        int curDepth = depth.getOrDefault(finish, -1);
        if (curDepth == -1) return null;
        TransportNetNode curNode = finish;
        while (true){
            if (curDepth == 0) return path;
            ArrayList<TransportNetEdge> edges = curNode.getEdges();
            TransportNetNode nextNode = null;
            int minDepth = curDepth;
            for(TransportNetEdge edge: edges){
                TransportNetNode node = edge.getOppositeNode(curNode);
                int nodeDepth = depth.getOrDefault(node, curDepth);
                if (nodeDepth < minDepth){
                    nextNode = node;
                    minDepth = nodeDepth;
                }
            }
            if (nextNode == null) break;
            path.addNodeToTail(nextNode);
            curNode = nextNode;
            curDepth = minDepth;
        }

        return null;
    }

    public TransportNetPath getPath(TransportNetNode start, TransportNetNode finish){
        init(start);
        while(true){
            if (queue.size() == 0) break;
            TransportNetNode node = queue.getFirst();
            queue.remove(node);
            if (node == finish) return getConstructedPathTo(finish);
            prolongAnalysis(node);
        }
        return null;
    }

}
