package Foundation;

import Utils.Coord;

import static org.lwjgl.opengl.GL11.*;

public class Camera {

    private CameraConfiguration currentConfiguration;
    private Frame frame;

    public Camera(Frame frame, CameraConfiguration cameraConfiguration){
        Coord pos = new Coord(frame.getPos());
        this.frame = frame;
        this.currentConfiguration = cameraConfiguration;
        //backgroundConfiguration = new CameraConfiguration(pos, pos, frame.getSize(),false);
    }

    public void takeCameraView(CameraConfiguration cameraConfiguration){
        glLoadIdentity();
        currentConfiguration = cameraConfiguration;
        Coord worldCameraPos = cameraConfiguration.getWorldCameraPos();
        Coord worldCameraSize = cameraConfiguration.getWorldCameraSize();
        Coord windowCameraPos = cameraConfiguration.getWindowCameraPos();
        Coord windowCameraSize = cameraConfiguration.getWindowCameraSize();
        //float zoom = cameraConfiguration.getZoom();
        //glOrtho(worldPos.x,  worldPos.x + zoom*windowSize.x, worldPos.y + zoom*windowSize.y, worldPos.y, 1, -1);
        glOrtho(worldCameraPos.x,worldCameraPos.x + worldCameraSize.x, worldCameraPos.y + worldCameraSize.y, worldCameraPos.y, 1, -1);
        glViewport((int)(windowCameraPos.x), (int)(frame.getSize().y - windowCameraPos.y - windowCameraSize.y) ,
                (int)(windowCameraSize.x), (int)(windowCameraSize.y));
    }

    public CameraConfiguration getCurrentConfiguration() {
        return currentConfiguration;
    }
}
