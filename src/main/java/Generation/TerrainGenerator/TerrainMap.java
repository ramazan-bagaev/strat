package Generation.TerrainGenerator;

import Utils.Geometry.Index;

import java.util.ArrayList;

public class TerrainMap {

    private Index size;
    private ArrayList<TerrainNode> nodes;
    private ArrayList<TerrainEdge> edges;

    private ArrayList<TerrainNode> borderNodes;

    private ArrayList<Plate> plates;

    public TerrainMap(Index size){
        this.size = size;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        borderNodes = new ArrayList<>();
    }

    public ArrayList<TerrainNode> getNodes() {
        return nodes;
    }

    public ArrayList<TerrainEdge> getEdges() {
        return edges;
    }

    public void setNodes(ArrayList<TerrainNode> nodes){
        this.nodes = nodes;
    }

    public void setEdges(ArrayList<TerrainEdge> edges){
        this.edges = edges;
    }

    public Index getSize() {
        return size;
    }

    public void addNode(TerrainNode node){
        nodes.add(node);
    }

    public void addEdge(TerrainEdge edge){
        edges.add(edge);
    }

    public ArrayList<Plate> getPlates() {
        return plates;
    }

    public void setPlates(ArrayList<Plate> plates) {
        this.plates = plates;
    }

    public ArrayList<TerrainNode> getBorderNodes() {
        return borderNodes;
    }

    public void addBorderNode(TerrainNode node){
        borderNodes.add(node);
        nodes.add(node);
    }
}
