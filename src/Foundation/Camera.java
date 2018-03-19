package Foundation;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Camera {

    private CameraConfiguration backgroundConfiguration;
    private Frame frame;

    public Camera(Frame frame){
        Coord pos = new Coord(frame.getPos());
        this.frame = frame;
        backgroundConfiguration = new CameraConfiguration(pos, pos, frame.getSize(),false);
    }

    public void takeCameraView(CameraConfiguration cameraConfiguration){
        glLoadIdentity();
        Coord worldPos = cameraConfiguration.getWorldPos();
        Coord windowSize = cameraConfiguration.getWindowSize();
        float zoom = cameraConfiguration.getZoom();
        glOrtho(worldPos.x,  worldPos.x + zoom*windowSize.x, worldPos.y + zoom*windowSize.y, worldPos.y, 1, -1);
        Coord windowPos = cameraConfiguration.getWindowPos();
        glViewport(windowPos.x, frame.getSize().y - windowPos.y - windowSize.y , windowSize.x, windowSize.y);
    }

    public void takeBackCameraView(){
        glLoadIdentity();
        Coord worldPos = backgroundConfiguration.getWorldPos();
        Coord windowSize = backgroundConfiguration.getWindowSize();
        float zoom = backgroundConfiguration.getZoom();
        glOrtho(worldPos.x,  worldPos.x + zoom*windowSize.x, worldPos.y + zoom*windowSize.y, worldPos.y, 1, -1);
        Coord windowPos = backgroundConfiguration.getWindowPos();
        glViewport(windowPos.x, frame.getSize().y - windowPos.y - windowSize.y, windowSize.x, windowSize.y);
    }

    public CameraConfiguration getBackgroundConfiguration() {
        return backgroundConfiguration;
    }
}
