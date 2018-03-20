package Foundation;

import java.util.ArrayList;

public class CameraConfiguration {

    private boolean applied;

    private float draggedX;
    private float draggedY;
    private boolean dragged;

    private Coord worldPos;
    private Coord windowSize;

    private boolean scrollable;

    private ArrayList<Float> zooms;
    private int zoomIndex;

    private Coord windowPos;

    public CameraConfiguration(Coord worldPos, Coord windowPos, Coord windowSize, boolean scrollable){
        this.scrollable = scrollable;
        this.worldPos = new Coord(worldPos);
        this.windowSize = new Coord(windowSize);
        this.windowPos = new Coord(windowPos);
        dragged = false;
        applied = false;
        zooms = new ArrayList<>();
        zooms.add(0.1f);
        zooms.add(0.2f);
        zooms.add(0.4f);
        zooms.add(0.5f);
        zooms.add(0.6f);
        zooms.add(0.7f);
        zooms.add(0.8f);
        zooms.add(1f);
        zoomIndex = 7;
        zooms.add(1.1f);
        zooms.add(1.2f);
        zooms.add(1.3f);
        zooms.add(1.5f);
        zooms.add(1.8f);
        zooms.add(2f);
        zooms.add(3f);
        zooms.add(4f);
        zooms.add(5f);
        zooms.add(6f);
        zooms.add(7f);
        zooms.add(8f);
        zooms.add(10f);
        zooms.add(12f);
        zooms.add(15f);
        zooms.add(20f);
        zooms.add(30f);
        zooms.add(40f);
        zooms.add(50f);

    }

    public void scroll(int delta, double x, double y){
        if (!scrollable) return;
        if (applied) return;
        if (delta < 0){
            if (zoomIndex != 0){
                Coord oldCurs = transform(new Coord((int)x, (int)y));
                zoomIndex--;
                Coord newCurs = transform(new Coord((int)x, (int)y));
                this.worldPos.x += (oldCurs.x - newCurs.x);
                this.worldPos.y += (oldCurs.y - newCurs.y);
            }
        }
        if (delta > 0){
            if (zoomIndex != zooms.size() - 1){
                Coord oldCurs = transform(new Coord((int)x, (int)y));
                zoomIndex++;
                Coord newCurs = transform(new Coord((int)x, (int)y));
                this.worldPos.x += (oldCurs.x - newCurs.x);
                this.worldPos.y += (oldCurs.y - newCurs.y);
            }
        }
    }

    public void move(Coord delta){
        if (!scrollable) return;
        if (applied) return;
        worldPos.x += delta.x * getZoom();
        worldPos.y += delta.y * getZoom();
    }

    public void drag(Coord pos, Coord pressedPos, boolean begin){
        if (begin){
            draggedX = worldPos.x;
            draggedY = worldPos.y;
        }
        worldPos.x = (int) (draggedX - (pos.x - pressedPos.x) * getZoom());
        worldPos.y = (int) (draggedY - (pos.y - pressedPos.y) * getZoom());
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public Coord getWorldPos(){
        return worldPos;
    }

    public float getZoom(){
        return zooms.get(zoomIndex);
    }

    public Coord transform(Coord coord){
        Coord result = new Coord(0, 0);
        float fx = (coord.x * getZoom() + worldPos.x);
        float fy = (coord.y * getZoom() + worldPos.y);
        //if (fx < 0 || fy < 0) return new Coord(-1000, -1000);
        result.x = (int)fx;
        result.y = (int)fy;
        return result;
    }

    public Coord getWindowSize() {
        return windowSize;
    }

    public Coord getWindowPos() {
        return windowPos;
    }
}
