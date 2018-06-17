package Generation.TerrainGenerator;

import Utils.Geometry.Index;

public class TerrainEdge {

    private TerrainNode first;
    private TerrainNode second;


    public TerrainEdge(TerrainNode first, TerrainNode second) {
        this.first = first;
        this.second = second;
    }

    public TerrainNode getOpposite(TerrainNode node) {
        if (node.equals(first) && !node.equals(second)) return second;
        if (node.equals(second) && !node.equals(first)) return first;
        return null;
    }

    public TerrainNode getFirst() {
        return first;
    }

    public TerrainNode getSecond() {
        return second;
    }

    public boolean contains(TerrainNode node){
        return first.equals(node) || second.equals(node);
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;
        TerrainEdge edge = (TerrainEdge)object;
        return (this.contains(edge.getFirst()) && this.contains(edge.getSecond()));
    }

    public boolean links(TerrainEdge edge){
        if (edge.contains(first) || edge.contains(second)) return true;
        return false;
    }

    public boolean inside(Index pos){
        if (first.getPos().equals(pos) || second.getPos().equals(pos)) return true;
        LineIterator iter = new LineIterator(this);
        while(iter.hasNext()){
            Index index = iter.nextSameIndex();
            if (index.equals(pos)) return true;
        }
        return false;
    }

    public double distance(Index index){
        Index vec = second.getPos().minus(first.getPos());
        Index vec2 = first.getPos().minus(index);
        Index vec3 = second.getPos().minus(index);
        if (vec.scaralMult(vec2) > 0){
            return vec2.norm();
        }
        if (vec.scaralMult(vec3) < 0){
            return vec3.norm();
        }
        return Math.abs(vec.vectorMult(vec2))/vec.norm();
    }

    public boolean intersects(TerrainEdge edge) {
        Index a1 = first.getPos();
        Index b1 = second.getPos();
        Index a2 = edge.first.getPos();
        Index b2 = edge.second.getPos();

        double x1 = a1.x;
        double y1 = a1.y;
        double x2 = a2.x;
        double y2 = a2.y;
        double k1 = b1.x - a1.x;
        double c1 = b1.y - a1.y;
        double k2 = b2.x - a2.x;
        double c2 = b2.y - a2.y;
        double det = k2 * c1 - k1 * c2;
        if (det == 0) {
            double koef = 0;
            double t1 = 0;
            double t2 = 0;
            if (c1 == 0){
                if (y2 - y1 != 0) return false;
                t1 = (x2 - x1)/k1;
                t2 = (b2.x - x1)/k1;
            }
            if (k1 == 0){
                if (x2 - x1 != 0) return false;
                t1 = (y2 - y1)/c1;
                t2 = (b2.y - y1)/c1;
            }
            if (c1 != 0 && k1 != 0){
                koef = c2/k2;
                if ((x2 - x1) != koef * (y2 - y1)) return false;
                t1 = (y2 - y1)/c1;
                t2 = (b2.y - y1)/c1;
            }
            if (t1 > t2){
                double temp = t2;
                t2 = t1;
                t1 = temp;
            }
            if (t2 <= 1 && t1 >= 0){
                return true;
            }
            return false;


        } else {
            double t1 = (-c2 * (x2 - x1) + k2 * (y2 - y1)) / det;
            double t2 = (-c1 * (x2 - x1) + k1 * (y2 - y1)) / det;
            if (t1 >= 0 && t1 <= 1 && t2 >= 0 && t2 <= 1){
                return true;
            }
            return false;
        }
    }
}