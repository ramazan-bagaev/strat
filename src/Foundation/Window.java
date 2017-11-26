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

    private ArrayList<WindowElement> windowElements;

    private ArrayList<WindowElementGroup> windowElementGroups;


    public Window(Coord pos, Coord size, Windows parent){
        setId(-1);
        setPos(pos);
        setSize(size);
        setParent(parent);
        windowElements = new ArrayList<>();
        windowElementGroups = new ArrayList<>();
        basicShapes = new ArrayList<>();
        basicShapes.add(new RectangleShape(pos, size, BasicShape.Color.White, true));
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
        for(WindowElementGroup windowElementGroup: windowElementGroups){
            if (windowElementGroup.contain(point)){
                windowElementGroup.click(point);
                return;
            }
        }
        for(WindowElement windowElement: windowElements) {
            if (windowElement.contain(point)) {
                windowElement.click(point);
                return;
            }
        }
        if (parent.isOnTop(this)) {
            return;
        }
        parent.removeWindow(this);
        parent.addWindow(this);
    }

    public void scroll(int yScroll){
        for(WindowElementGroup windowElementGroup: windowElementGroups){
            if (windowElementGroup.getClass() == ScrollableGroup.class){
                int x = (int)getParent().getCursorPosX();
                int y = (int)getParent().getCursorPosY();
                if (!windowElementGroup.contain(new Coord(x, y))) continue;
                ScrollableGroup scrollableGroup = (ScrollableGroup)windowElementGroup;
                scrollableGroup.scroll(yScroll);
            }
        }
    }

    public void addWindow(Window window){
        getParent().addWindow(window);
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
        for(BasicShape basicShape: basicShapes){
            openGLBinder.draw(basicShape);
        }
        for(WindowElement windowElement: windowElements){
            windowElement.draw(openGLBinder);
        }

        for (WindowElementGroup windowElementGroup: windowElementGroups){
            windowElementGroup.draw(openGLBinder);
        }
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
}
