package Utils.BypassIterator;

import Utils.Boundary.Boundary;
import Utils.Geometry.Index;

public interface BypassIterator {

    boolean hasNext();
    Index next();

    void addBoundary(Boundary boundary);

}
