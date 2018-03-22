package WindowElementGroups;

import Foundation.CameraConfiguration;
import Utils.Coord;

public class ScrollableGroupCameraConfiguration extends CameraConfiguration{

    private double minY;
    private double maxY;

    private double yPos;

    public ScrollableGroupCameraConfiguration(Coord windowCameraPos, Coord windowCameraSize, double minY, double maxY) {
        super(windowCameraPos, windowCameraSize);
        this.minY = minY;
        this.maxY = maxY;
        yPos = worldCameraPos.y;
    }

    public ScrollableGroupCameraConfiguration(Coord windowCameraPos, Coord windowCameraSize, Coord worldCameraPos, Coord worldCameraSize,
                                              double minY, double maxY) {
        super(worldCameraPos, worldCameraSize, windowCameraPos, windowCameraSize);
        this.minY = minY;
        this.maxY = maxY;
        yPos = windowCameraPos.y;
    }

    public void scroll(double delta){
        moveCamera(delta);
    }

    public void moveCamera(double delta){
        if (worldCameraPos.y - yPos + worldCameraSize.y + delta > maxY || worldCameraPos.y - yPos + delta < minY) return;
        worldCameraPos.y += delta;

    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
}
