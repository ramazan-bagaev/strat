package Generation.TerrainGenerator;

import Utils.Geometry.Index;

public class LineIterator {

    private int x0;
    private int y0;
    private double dx;
    private double dy;
    private double deltaX;
    private double deltaY;
    private int step;
    private Index iterIndex;

    private Index posA;
    private Index posB;

    public LineIterator(TerrainEdge edge){
        this.posA = edge.getFirst().getPos();
        this.posB = edge.getSecond().getPos();
        init();
    }

    public LineIterator(Index posA, Index posB) {
        this.posA = posA;
        this.posB = posB;
        init();
    }

    private void init(){
        x0 = posA.x;
        y0 = posA.y;
        dy = posB.y - posA.y;
        dx = posB.x - posA.x;
        deltaX = dx/Math.max(Math.abs(dx), Math.abs(dy));
        deltaY = dy/Math.max(Math.abs(dx), Math.abs(dy));
        step = 0;
        iterIndex = new Index(0, 0);
    }

    public boolean hasNext() {
        if (Math.abs(deltaX * step) > Math.abs(dx)) return false;
        if (Math.abs(deltaY * step) > Math.abs(dy)) return false;
        if (deltaX != 0 && Math.abs(deltaX * step) == Math.abs(dx)) return false;
        if (deltaY != 0 && Math.abs(deltaY * step) == Math.abs(dy)) return false;
        return true;
    }

    public Index nextSameIndex() {
        iterIndex.x = (int)Math.round(deltaX * step) + x0;
        iterIndex.y = (int)Math.round(deltaY * step) + y0;
        step++;
        return iterIndex;
    }
}
