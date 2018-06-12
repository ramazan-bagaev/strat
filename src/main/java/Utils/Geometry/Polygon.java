package Utils.Geometry;

import java.util.ArrayList;

public class Polygon extends GeometryShape {

    private ArrayList<Index> nodes;

    private ArrayList<Triangle> triangles;

    public Polygon(ArrayList<Index> nodes) {
        this.nodes = nodes;
        triangles = new ArrayList<>();
    }

    public void trinagulate(){

    }

}
