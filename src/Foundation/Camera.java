package Foundation;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Camera {

    private CameraConfiguration backgroundConfiguration;

    public Camera(float x, float y, float sizeX, float sizeY){
        backgroundConfiguration = new CameraConfiguration(x, y, sizeX, sizeY, false);
    }

    public void takeCameraView(CameraConfiguration cameraConfiguration){
        glLoadIdentity();
        float x = cameraConfiguration.getX();
        float y = cameraConfiguration.getY();
        float sizeX = cameraConfiguration.getSizeX();
        float sizeY = cameraConfiguration.getSizeY();
        float zoom = cameraConfiguration.getZoom();
        glOrtho(x,  x + zoom*sizeX, y + zoom*sizeY, y, 1, -1);
    }

    public void takeBackCameraView(){
        glLoadIdentity();
        float x = backgroundConfiguration.getX();
        float y = backgroundConfiguration.getY();
        float sizeX = backgroundConfiguration.getSizeX();
        float sizeY = backgroundConfiguration.getSizeY();
        float zoom = backgroundConfiguration.getZoom();
        glOrtho(x,x + zoom*sizeX, y + zoom*sizeY, y, 1, -1);
    }

    public CameraConfiguration getBackgroundConfiguration() {
        return backgroundConfiguration;
    }
}
