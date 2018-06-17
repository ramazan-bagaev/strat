package Generation.TerrainGenerator;

import Utils.Geometry.Index;

import java.util.ArrayList;

public class TerrainNode {

    private Index pos;
    private int height;
    private ArrayList<TerrainEdge> edges;

    public TerrainNode(Index pos){
        this.pos = pos;
        edges = new ArrayList<>();
    }

    public TerrainNode(Index pos, int height){
        this.pos = pos;
        this.height = height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public Index getPos(){
        return pos;
    }

    public void addEdge(TerrainEdge edge){
        edges.add(edge);
    }

    public ArrayList<TerrainEdge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;
        TerrainNode node = (TerrainNode)object;
        return node.pos.equals(pos);
    }

}
