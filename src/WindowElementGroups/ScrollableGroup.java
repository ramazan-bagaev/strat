package WindowElementGroups;

import Foundation.*;
import Utils.Coord;
import WindowElements.ScrollableElement;

import java.util.ArrayList;

public class ScrollableGroup extends WindowElementGroup{

    private ArrayList<ScrollableElement> scrollableElements;
    private ScrollableGroupCameraConfiguration cameraConfiguration;

    public ScrollableGroup(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        cameraConfiguration = new ScrollableGroupCameraConfiguration(new Coord(getShift()), getSize(),
                new Coord(getShift()), getSize(), 0, 0);
        addScrollButton();
    }

    public ScrollableGroup(Coord pos, Coord size, ArrayList<ScrollableElement> scrollableElements, Window parent) {
        super(pos, size, parent);
        cameraConfiguration = new ScrollableGroupCameraConfiguration(new Coord(getShift()), getSize(),
                new Coord(getShift()), getSize(), 0, scrollableElements.size()*20);
        setScrollableElements(scrollableElements);
        addScrollButton();
    }

    public void addScrollButton(){
        Button buttonUp = new Button(new Coord(getSize().x - 15, 0), new Coord(15, 20), this, getParent(), "up") {
            @Override
            public void click(Coord point) {
                ScrollableGroup scrollableGroup = (ScrollableGroup)getGroupParent();
                scrollableGroup.changePosition(-1);
            }
        };

        Button buttonDown = new Button(new Coord(getSize().x - 15, getSize().y - 20), new Coord(15, 20), this, getParent(), "down") {
            @Override
            public void click(Coord point) {
                ScrollableGroup scrollableGroup = (ScrollableGroup)getGroupParent();
                scrollableGroup.changePosition(1);
            }
        };
        addWindowElement(buttonDown);
        addWindowElement(buttonUp);
    }

    @Override
    public void draw(OpenGLBinder openGLBinder){
        super.draw(openGLBinder);
        Camera camera = getParent().getParent().getCamera();
        CameraConfiguration currentCameraConfiguration = camera.getCurrentConfiguration();
        camera.takeCameraView(cameraConfiguration);
        for (ScrollableElement scrollableElement: scrollableElements){
            scrollableElement.draw(openGLBinder);
        }
        camera.takeCameraView(currentCameraConfiguration);

    }


    public ArrayList<ScrollableElement> getScrollableElements() {
        return scrollableElements;
    }

    public void setScrollableElements(ArrayList<ScrollableElement> scrollableElements) {
        this.scrollableElements = scrollableElements;
        cameraConfiguration.setMaxY(scrollableElements.size()*20);

        for(WindowElement windowElement: scrollableElements){
            windowElement.setGroupParent(this);
        }
    }

    public void changePosition(int delta){
           cameraConfiguration.moveCamera(delta*20);
    }

    public void scroll(double delta){
        cameraConfiguration.moveCamera(delta*20);
    }

    public void run(){
        super.run();

        for (ScrollableElement scrollableElement: scrollableElements){
            scrollableElement.run();
        }
    }

    //public int getClickedIndex(Coord point){
    //    return (int) (bottomIndex + (point.y - getPos().y)/rowHeight);
   // }
}
