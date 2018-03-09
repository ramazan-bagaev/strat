package Foundation;

import CharacterShape.Font;
import WindowElementGroups.ScrollableGroup;

import java.util.ArrayList;

public class Window {

    private Coord pos;
    private Coord size;
    private Windows parent;
    private int id;
    private ArrayList<BasicShape> basicShapes;

    private CameraConfiguration cameraConfiguration;

    private ArrayList<WindowElement> windowElements;

    private ArrayList<WindowElementGroup> windowElementGroups;

    private ArrayList<Integer> windowId;


    public Window(Coord pos, Coord size, Windows parent){
        setId(-1);
        setPos(pos);
        setSize(size);
        setParent(parent);
        windowElements = new ArrayList<>();
        windowElementGroups = new ArrayList<>();
        basicShapes = new ArrayList<>();
        basicShapes.add(new RectangleShape(pos, size, BasicShape.Color.White, true));
        cameraConfiguration = new CameraConfiguration(0, 0, 1000, 1000, false);
        windowId = new ArrayList<>();
    }

    public Windows getParent() {
        return parent;
    }

    public void setParent(Windows parent) {
        this.parent = parent;
    }

    public ArrayList<WindowElement> getWindowElements() {
        return windowElements;
    }

    public void setWindowElements(ArrayList<WindowElement> windowElements) {
        this.windowElements = windowElements;
    }

    public void addWindowElement(WindowElement windowElement){
        windowElements.add(windowElement);
    }


    public Coord getPos() {
        return pos;
    }

    public void setPos(Coord pos) {
        this.pos = pos;
    }

    public Coord getSize() {
        return size;
    }

    public void setSize(Coord size) {
        this.size = size;
    }

    public Font getFont(String name){
        return parent.getUsedFont(name);
    }

    public boolean contain(Coord point){
        return point.inRectangle(pos, size);
    }

    public void click(Coord point){
        if (parent.isOnTop(this)) return;
        parent.takeOnTop(this);
    }

    public void click2(Coord point){
    }

    public void scroll(double delta, double x, double y){
        if (cameraConfiguration.isScrollable()){
            cameraConfiguration.scroll((int)delta, x - pos.x, y - pos.y);
            if (getClass() == MainWindow.class){
                MainWindow mainWindow = (MainWindow)this;
                mainWindow.getGameWindowElement().setShapes(); // TODO: you know (no)
            }
        }
    }

    public void drag(Coord point){
    }

    public void addWindow(Window window){
        getParent().addWindow(window);
        windowId.add(window.getId());
    }

    public void removeWindowElements(){
        windowElements.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void run(){
        for(WindowElement windowElement: windowElements){
            windowElement.run();
        }

        for (WindowElementGroup windowElementGroup: windowElementGroups){
            windowElementGroup.run();
        }
    }

    public void draw(OpenGLBinder openGLBinder){
        Camera camera = getParent().getCamera();
        camera.takeCameraView(cameraConfiguration);
        for(BasicShape basicShape: basicShapes){
            openGLBinder.draw(basicShape);
        }
        for(WindowElement windowElement: windowElements){
            windowElement.draw(openGLBinder);
        }

        for (WindowElementGroup windowElementGroup: windowElementGroups){
            windowElementGroup.draw(openGLBinder);
        }
        camera.takeBackCameraView();
    }

    public ArrayList<BasicShape> getBasicShapes() {
        return basicShapes;
    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        this.basicShapes = basicShapes;
    }

    public ArrayList<WindowElementGroup> getWindowElementGroups() {
        return windowElementGroups;
    }

    public void setWindowElementGroups(ArrayList<WindowElementGroup> windowElementGroups) {
        this.windowElementGroups = windowElementGroups;
    }

    public void addWindowElementGroup(WindowElementGroup windowElementGroup){
        windowElementGroups.add(windowElementGroup);
    }

    public void setCameraConfiguration(CameraConfiguration cameraConfiguration){
        this.cameraConfiguration = cameraConfiguration;
    }

    public CameraConfiguration getCameraConfiguration(){
        return cameraConfiguration;
    }

    public void handleAction(Controller.Action action){
    }

    public ArrayList<Integer> getWindowId() {
        return windowId;
    }
}
