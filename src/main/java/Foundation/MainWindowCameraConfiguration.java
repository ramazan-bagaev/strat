package Foundation;

import Utils.Coord;

import java.util.ArrayList;

public class MainWindowCameraConfiguration extends CameraConfiguration {

    private boolean applied;
    private Coord draggedPos;

    private ArrayList<Float> zooms;
    private int zoomIndex;

    public MainWindowCameraConfiguration(Coord worldCameraPos, Coord worldCameraSize, Coord windowCameraPos, Coord windowCameraSize){
        super(worldCameraPos, worldCameraSize, windowCameraPos, windowCameraSize);
        applied = false;
        zoomInit();
    }

    public MainWindowCameraConfiguration(Coord windowPos, Coord windowSize){
        super(windowPos, windowSize);
        applied = false;
        zoomInit();
    }

    public void zoomInit(){
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

    public void scroll(double delta, Coord pos){
        if (applied) return;
        if (delta < 0){
            if (zoomIndex != 0){
                Coord oldCurs = transform(pos);
                zoomIndex--;
                Coord newCurs = transform(pos);
                worldCameraPos.x += (oldCurs.x - newCurs.x);
                worldCameraPos.y += (oldCurs.y - newCurs.y);
            }
        }
        if (delta > 0){
            if (zoomIndex != zooms.size() - 1){
                Coord oldCurs = transform(pos);
                zoomIndex++;
                Coord newCurs = transform(pos);
                worldCameraPos.x += (oldCurs.x - newCurs.x);
                worldCameraPos.y += (oldCurs.y - newCurs.y);
            }
        }
    }

    public void move(Coord delta){
        if (applied) return;
        worldCameraPos.x += delta.x * getZoom();
        worldCameraPos.y += delta.y * getZoom();
    }

    public void drag(Coord pos, Coord pressedPos, boolean begin){
        if (begin){
            draggedPos = new Coord(worldCameraPos);
            return;
        }
        worldCameraPos.x =  (draggedPos.x - (pos.x - pressedPos.x) * getZoom());
        worldCameraPos.y =  (draggedPos.y - (pos.y - pressedPos.y) * getZoom());
    }

    public float getZoom(){
        return zooms.get(zoomIndex);
    }

    public Coord transform(Coord coord) { // TODO: should be (coord - cameraWindowPos)*getZoom() + worldCameraPos
        double fx = (coord.x * getZoom() + worldCameraPos.x);
        double fy = (coord.y * getZoom() + worldCameraPos.y);
        return new Coord(fx, fy);
    }

    @Override
    public Coord getWorldCameraSize(){
        return worldCameraSize.multiply(getZoom());
    }
}
