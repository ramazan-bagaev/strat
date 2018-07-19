package Utils.Boundary;

import Utils.Geometry.Index;

public class RectangleBoundary implements Boundary {

    protected Index pos;
    protected Index size;

    public RectangleBoundary(Index pos, Index size){
        this.pos = pos;
        this.size = size;
    }

    public Index getPos() {
        return pos;
    }

    public Index getSize() {
        return size;
    }

    @Override
    public boolean contains(Index index) {
        if (index.x < pos.x || index.x >= pos.x + size.x) return false;
        if (index.y < pos.y || index.y >= pos.y + size.y) return false;
        return true;
    }
}
