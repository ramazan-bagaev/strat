package Generation;

import Foundation.Coord;

import java.util.ArrayList;

public class Polygone {

    private ArrayList<Triangle> triangles;

    private ArrayList<Line> border;

    public Polygone(){
    }

    public void addTriangle(Triangle triangle){
        for (Triangle triangle1: triangles){
            Coord difPoint = triangle.shareSide(triangle1);
            if (difPoint != null){
                if (inPolygone(difPoint)){

                    triangles.add(triangle);
                    return;
                }
            }
        }
    }

    public boolean inPolygone(Coord point){
        for(Triangle triangle: triangles){
            if (triangle.inTrinangle(point)) return true;
        }
        return false;
    }
}
