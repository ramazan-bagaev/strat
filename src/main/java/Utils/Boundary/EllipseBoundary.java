package Utils.Boundary;

import Utils.Geometry.Coord;
import Utils.Geometry.Index;

public class EllipseBoundary implements Boundary{

    private Coord center;
    private Coord dir1;
    private Coord dir2;



    private Coord local;

    public EllipseBoundary(Coord center, Coord dir1, Coord dir2){
        this.center = center;
        this.dir1 = dir1;
        this.dir2 = dir2;
        local = new Coord();
    }

    public Coord getCenter() {
        return center;
    }

    public Coord getDir1() {
        return dir1;
    }

    public Coord getDir2() {
        return dir2;
    }

    @Override
    public boolean contains(Index index) {
        local.x = index.x;
        local.y = index.y;
        Coord rel = local.minus(center);
        double x = dir1.projection(rel).norm();
        double y = dir2.projection(rel).norm();
        return ((x*x)/Math.pow(dir1.norm(), 2) + (y*y)/Math.pow(dir2.norm(), 2) <= 1);
    }
}
