package Generation.TerrainGenerator;

import java.util.ArrayList;

public class Plate {

    protected ArrayList<TerrainNode> nodes;
    protected ArrayList<TerrainEdge> edges;

    public Plate(){

    }

    public Plate(Plate plate){
        this.nodes = plate.getNodes();
        this.edges = plate.getEdges();
    }

    public boolean setNodes(ArrayList<TerrainNode> nodes){
        if (isNodesClosed(nodes)){
            this.nodes = nodes;
            return true;
        }
        return false;
    }

    public boolean setEdges(ArrayList<TerrainEdge> edges){
        if (isEdgesClosed(edges)){
            this.edges = edges;
            return true;
        }
        return false;
    }

    public ArrayList<TerrainNode> getNodes() {
        return nodes;
    }

    public boolean isNeighbor(Plate plate){
        for(TerrainEdge edge: plate.getEdges()){
            if (edges.contains(edge)) return true;
        }
        return false;
    }

    private boolean isNodesClosed(ArrayList<TerrainNode> nodes){
        if (nodes.size() == 0) return false;
        if (nodes.size() == 2) return false;
        for(int i = 0; i < nodes.size() - 1; i++){
            if (!shareEdge(nodes.get(i), nodes.get(i+1))) return false;
        }
        if (!shareEdge(nodes.get(nodes.size() - 1), nodes.get(0))) return false;
        return true;
    }

    private boolean isEdgesClosed(ArrayList<TerrainEdge> edges){
        if (edges.size() == 0) return false;
        if (edges.size() == 1) return false;
        for(int i = 0; i < edges.size() - 1; i++){
            if (!edges.get(i).links(edges.get(i+1))) return false;
            if (edges.get(i).equals(edges.get(i+1))) return false;

        }
        if (!edges.get(edges.size() - 1).links(edges.get(0))) return false;
        if (edges.get(edges.size() - 1).equals(edges.get(0))) return false;
        return true;
    }

    public boolean shareEdge(TerrainNode node1, TerrainNode node2){
        for(TerrainEdge edge: node1.getEdges()){
            if (edge.getOpposite(node1).equals(node2)) return true;
        }
        return false;
    }

    public ArrayList<TerrainEdge> getEdges() {
        return edges;
    }

    public boolean onLeft(TerrainEdge edge){
        for(TerrainNode node: nodes){
            if (edge.getOpposite(node) != null){
                if (edge.getOpposite(node) == edge.getFirst()) return true;
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object object){
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;
        Plate plate = (Plate)object;
        ArrayList<TerrainNode> otherNodes = plate.getNodes();
        if (otherNodes == null) return false;
        for(TerrainNode node: plate.getNodes()){
            if (!nodes.contains(node)) return false;
        }
        return true;
    }
}
