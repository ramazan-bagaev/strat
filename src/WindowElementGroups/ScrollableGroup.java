package WindowElementGroups;

import Foundation.*;
import Utils.Coord;
import WindowElements.ScrollElements.ScrollableRow;

import java.util.ArrayList;

public class ScrollableGroup extends WindowElementGroup{

    protected ArrayList<ScrollableRow> scrollableRows;
    protected ScrollableGroupCameraConfiguration cameraConfiguration;

    protected Coord scrollWindowSize;

    public ScrollableGroup(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        scrollableRows = new ArrayList<>();
        scrollWindowSize = new Coord(size.add(new Coord(-20,0)));
        cameraConfiguration = new ScrollableGroupCameraConfiguration(getShift(), scrollWindowSize,
                getShift(), scrollWindowSize, 0, 0);
        addScrollButton();
    }

    public ScrollableGroup(Coord pos, Coord size, Coord scrollWindowSize, Window parent){
        super(pos, size, parent);
        scrollableRows = new ArrayList<>();
        this.scrollWindowSize = new Coord(scrollWindowSize);
        cameraConfiguration = new ScrollableGroupCameraConfiguration(getShift(), scrollWindowSize,
                getShift(), scrollWindowSize, 0, 0);
        addScrollButton();
    }

    public ScrollableGroup(Coord pos, Coord size, ArrayList<ScrollableRow> scrollableRows, Window parent) {
        super(pos, size, parent);
        this.scrollableRows = new ArrayList<>();
        scrollWindowSize = new Coord(size.add(new Coord(-20,0)));
        cameraConfiguration = new ScrollableGroupCameraConfiguration(getShift(), scrollWindowSize,
                getShift(), scrollWindowSize, 0, scrollableRows.size()*20);
        setScrollableRows(scrollableRows);
        addScrollButton();
    }

    public void addScrollButton(){
        Button buttonUp = new Button(new Coord(getSize().x - 20, 0), new Coord(20, 20), this, getParent(), "u") {
            @Override
            public void click(Coord point) {
                ScrollableGroup scrollableGroup = (ScrollableGroup)getGroupParent();
                scrollableGroup.changePosition(-1);
            }
        };

        Button buttonDown = new Button(new Coord(getSize().x - 20, getSize().y - 20), new Coord(20, 20), this,
                getParent(), "d") {
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
        for (ScrollableRow scrollableRow : scrollableRows){
            scrollableRow.draw(openGLBinder);
        }
        camera.takeCameraView(currentCameraConfiguration);
    }


    public ArrayList<ScrollableRow> getScrollableRows() {
        return scrollableRows;
    }

    public void addScrollableRow(ScrollableRow scrollableRow){
        scrollableRow.shift(new Coord(0, cameraConfiguration.getMaxY()));
        scrollableRows.add(scrollableRow);
        cameraConfiguration.increaseMaxY(scrollableRow.getSize().y + 10);
    }

    public void setScrollableRows(ArrayList<ScrollableRow> scrollableRows) {
        this.scrollableRows = scrollableRows;
        cameraConfiguration.setMaxY(scrollableRows.size()*20);
    }

    public void changePosition(int delta){
           cameraConfiguration.moveCamera(delta*20);
    }

    public void scroll(double delta){
        cameraConfiguration.moveCamera(delta*30);
    }

    public void run(){
        super.run();

        for (ScrollableRow scrollableRow : scrollableRows){
            scrollableRow.run();
        }
    }

    public Coord getScrollWindowSize() {
        return scrollWindowSize;
    }

    //public int getClickedIndex(Coord point){
    //    return (int) (bottomIndex + (point.y - getPos().y)/rowHeight);
   // }
}
