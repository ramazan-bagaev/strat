package Foundation;

import Foundation.BasicShapes.CharacterShape.Font;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Utils.Coord;

import java.util.ArrayList;

public class Window {

    protected boolean active;

    protected Coord pos;
    protected Coord size;
    protected Frame parent;
    private int id;
    private ArrayList<BasicShape> basicShapes;

    private ArrayList<WindowElement> windowElements;

    private ArrayList<WindowElementGroup> windowElementGroups;

    private ArrayList<Integer> windowId;


    public Window(Coord pos, Coord size, Frame parent){
        setId(-1);
        this.pos = new Coord(pos);
        this.size = new Coord(size);
        this.active = false;
        setParent(parent);
        windowElements = new ArrayList<>();
        windowElementGroups = new ArrayList<>();
        basicShapes = new ArrayList<>();
        RectangleShape background = new RectangleShape(new Coord(0, 0), size, new Color(Color.Type.White), true);
        addBasicShape(background);
        windowId = new ArrayList<>();
    }

    public Frame getParent() {
        return parent;
    }

    public void setParent(Frame parent) {
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
        /*System.out.println(point.x + " point " + point.y);
        System.out.println(pos.x + " pos " + pos.y);
        System.out.println(size.x + " size " + size.y);*/
        return point.inRectangle(pos, size);
    }

    public void click(Coord point){
        if (parent.isOnTop(this)) return;
        parent.takeOnTop(this);
    }

    public void click2(Coord point){
    }

    public void scroll(double delta, Coord scrollPos){
    }

    public boolean drag(Coord point, Coord pressedPos, boolean dragBegin){
        return false;
    }

    public void hoover(Coord point){
    }

    public void activate(){
        active = true;
    }

    public void deactivate(){
        active = false;
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
        //    windowElement.run();
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

    //public ArrayList<BasicShape> getBasicShapes() {
    //    return basicShapes;
    //}

    public void addBasicShape(BasicShape basicShape){
        basicShape.shift(getShift());
        basicShapes.add(basicShape);
    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        for(BasicShape basicShape: basicShapes){
            addBasicShape(basicShape);
        }
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

    public void handleAction(Controller.Action action){
    }

    public ArrayList<Integer> getWindowId() {
        return windowId;
    }

    public Coord getShift(){
        return new Coord(pos);
    }

    public void setShapes(){

    }
}
