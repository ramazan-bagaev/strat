package Foundation;

import Utils.Coord;

public class CameraConfiguration {

    protected Coord worldCameraPos;
    protected Coord worldCameraSize;

    private Coord windowCameraSize;
    private Coord windowCameraPos;

    public CameraConfiguration(Coord windowCameraPos, Coord windowCameraSize){
        this.windowCameraPos = new Coord(windowCameraPos);
        this.windowCameraSize = new Coord(windowCameraSize);
        this.worldCameraPos = new Coord(windowCameraPos);
        this.worldCameraSize = new Coord(windowCameraSize);
    }

    public CameraConfiguration(Coord worldCameraPos, Coord worldCameraSize, Coord windowCameraPos, Coord windowCameraSize){
        this.worldCameraPos = new Coord(worldCameraPos);
        this.worldCameraSize = new Coord(worldCameraSize);
        this.windowCameraSize = new Coord(windowCameraSize);
        this.windowCameraPos = new Coord(windowCameraPos);
    }

    public void scroll(double delta, Coord pos){
    }

    public void move(Coord delta){
    }

    public void drag(Coord pos, Coord pressedPos, boolean begin){
    }

    public Coord getWorldCameraPos(){
        return worldCameraPos;
    }

    public Coord getWorldCameraSize(){
        return worldCameraSize;
    }


    public Coord getWindowCameraSize(){
        return windowCameraSize;
    }

    public Coord getWindowCameraPos() {
        return windowCameraPos;
    }

    public Coord transform(Coord pos){
        return pos;
    }
}
