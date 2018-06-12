package Utils.Distribution;

import Utils.Boundary.EllipseBoundary;
import Utils.BypassIterator.BypassIterator;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;

import java.util.Random;

public class EllipseDistribution extends IndexDistribution {

    private EllipseBoundary boundary;
    private Coord local;

    public EllipseDistribution(BypassIterator iterator, Random random, EllipseBoundary boundary) {
        super(iterator, random);
        this.boundary = boundary;
        iterator.addBoundary(boundary);
        local = new Coord();
    }

    @Override
    public double getProbability(Index index) {
        if (!boundary.contains(index)) return 0;
        local.x = index.x;
        local.y = index.y;
        Coord rel = local.minus(boundary.getCenter());
        double x = boundary.getDir1().projection(rel).norm();
        double y = boundary.getDir2().projection(rel).norm();
        return (1 - (x*x)/Math.pow(boundary.getDir1().norm(), 2) - (y*y)/Math.pow(boundary.getDir2().norm(), 2));
    }
}
