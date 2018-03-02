package Foundation;

import java.util.ArrayList;

public class CameraConfiguration {

    private boolean applied;

    private float x;
    private float y;

    private float sizeX;
    private float sizeY;

    private boolean scrollable;

    private ArrayList<Float> zooms;
    private int zoomIndex;

    public CameraConfiguration(float x, float y, float sizeX, float sizeY, boolean scrollable){
        this.scrollable = scrollable;
        this.x = x;
        this.y = y;
        this.sizeY = sizeY;
        this.sizeX = sizeX;
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
        zooms.add(4f);
        zooms.add(8f);


    }

    public void scroll(int delta){
        if (!scrollable) return;
        if (applied) return;
        if (delta < 0){
            if (zoomIndex != 0){
                zoomIndex--;
            }
        }
        if (delta > 0){
            if (zoomIndex != zooms.size() - 1){
                zoomIndex++;
            }
        }
    }

    public void move(Coord delta){
        if (!scrollable) return;
        if (applied) return;
        x += delta.x;
        y += delta.y;
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }

    public float getZoom(){
        return zooms.get(zoomIndex);
    }

    public boolean same(CameraConfiguration cameraConfiguration){
        if (x != cameraConfiguration.getX()) return false;
        if (y != cameraConfiguration.getY()) return false;
        if (sizeX != cameraConfiguration.getSizeX()) return false;
        if (sizeY != cameraConfiguration.getSizeY()) return false;
        if (getZoom() != cameraConfiguration.getZoom()) return false;
        return true;
    }

    public Coord transform(Coord coord){
        Coord result = new Coord(0, 0);
        float fx = (coord.x * getZoom() + x);
        float fy = (coord.y * getZoom() + y);
        if (fx < 0 || fy < 0) return new Coord(-1000, -1000);
        result.x = (int)fx;
        result.y = (int)fy;
        return result;
    }
}
