package Foundation;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cursor {

    private double posX;
    private double posY;
    private boolean posChanged;

    private boolean pressed;

    private Input input;
    private Windows windows;

    private Window activeWindow;
    private WindowElement activeWindowElement;
    private WindowElementGroup activeWindowElementGroup;

    private WindowElement clickedWindowElement;

    public Cursor(Windows windows, Input input){
        this.windows = windows;
        this.input = input;
        pressed = false;
    }

    public void posRenew(double x, double y){
        if (x != posX){
            posX = x;
            posChanged = true;
        }
        if (y != posY){
            posY = y;
            posChanged = true;
        }
        Coord point = new Coord((int)x, (int)y);
        if (posChanged){
            if (pressed) drag(point);
            renewActive();
        }
        if (activeWindow != null) activeWindow.hoover(point);
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public Window getActiveWindow() {
        return activeWindow;
    }

    public void setActiveWindow(Window activeWindow) {
        this.activeWindow = activeWindow;
    }

    public WindowElement getActiveWindowElement() {
        return activeWindowElement;
    }

    public void setActiveWindowElement(WindowElement activeWindowElement) {
        this.activeWindowElement = activeWindowElement;
    }

    public void renewActive(){
        Coord point = new Coord((int)posX, (int)posY);
        LinkedList<Window> windowList = windows.getWindows();
        for (int i = windowList.size() - 1; i >= 0; i--){
            Window window = windowList.get(i);
            if (window.contain(point)){
                activeWindow = window;
                ArrayList<WindowElementGroup> windowElementGroups = window.getWindowElementGroups();
                for (WindowElementGroup windowElementGroup: windowElementGroups){
                    if (windowElementGroup.contain(point)){
                        activeWindowElementGroup = windowElementGroup;
                        ArrayList<WindowElement> windowElements = windowElementGroup.getWindowElements();
                        for (WindowElement windowElement: windowElements){
                            if (windowElement.contain(point)){
                                activeWindowElement = windowElement;
                                return;
                            }
                        }
                        activeWindowElement = null;
                        return;
                    }
                }
                activeWindowElementGroup = null;
                ArrayList<WindowElement> windowElements = window.getWindowElements();
                for (int j = windowElements.size() - 1; j >= 0; j--){
                    WindowElement windowElement = windowElements.get(j);
                    if (windowElement.contain(point)){
                        activeWindowElement = windowElement;
                        return;
                    }
                }
                activeWindowElement = null;
                return;
            }
        }
    }

    public WindowElementGroup getActiveWindowElementGroup() {
        return activeWindowElementGroup;
    }

    public void setActiveWindowElementGroup(WindowElementGroup activeWindowElementGroup) {
        this.activeWindowElementGroup = activeWindowElementGroup;
    }

    public void click(Coord point, boolean pressed, int button){
        if (button == 0) action(point, pressed);
        if (button == 1) additionalAction(point, pressed);
    }

    public void action(Coord point, boolean pressed){
        this.pressed = pressed;
        if (!pressed) {
            actionWindow(point);
        }
        else{
            if (clickedWindowElement != null) input.getKeyboard().check(clickedWindowElement);
            else input.getKeyboard().setActiveElement(null);
            return;
        }
    }

    public void additionalAction(Coord point, boolean pressed){
        if (!pressed){
            additionalActionWindow(point);
        }
    }

    public void scroll(double delta){
        if (activeWindowElementGroup != null){
            activeWindowElementGroup.scroll(delta);
            return;
        }
        if (activeWindow != null){
            activeWindow.scroll(delta, posX, posY);
        }
    }

    public void actionWindow(Coord point){
        if (activeWindowElement != null) {
            activeWindowElement.click(point);
            clickedWindowElement = activeWindowElement;
            return;
        }
        if (activeWindowElementGroup != null) {
            activeWindowElementGroup.click(point);
            return;
        }
        if (activeWindow != null) {
            activeWindow.click(point);
            return;
        }
    }

    public void additionalActionWindow(Coord point){
        if (activeWindowElement != null) {
            activeWindowElement.click2(point);
            clickedWindowElement = activeWindowElement;
            return;
        }
        if (activeWindowElementGroup != null) {
            activeWindowElementGroup.click2(point);
            return;
        }
        if (activeWindow != null) {
            activeWindow.click2(point);
            return;
        }
    }

    public void drag(Coord point){
        if (activeWindowElement != null) {
            activeWindowElement.drag(point);
            clickedWindowElement = activeWindowElement;
            return;
        }
        if (activeWindowElementGroup != null) {
            activeWindowElementGroup.drag(point);
            return;
        }
        if (activeWindow != null) {
            activeWindow.drag(point);
            return;
        }
    }

    public WindowElement getClickedWindowElement() {
        return clickedWindowElement;
    }
}
