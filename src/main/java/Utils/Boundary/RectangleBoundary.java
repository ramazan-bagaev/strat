package Utils.Boundary;

import Utils.Geometry.Index;

public class RectangleBoundary implements Boundary {

    private Index pos;
    private Index size;

    public RectangleBoundary(Index pos, Index size){
        this.pos = pos;
        this.size = size;
    }

    @Override
    public boolean contains(Index index) {
        if (index.x < pos.x || index.x >= pos.x + size.x) return false;
        if (index.y < pos.y || index.y >= pos.y + size.y) return false;
        return true;
    }
}
