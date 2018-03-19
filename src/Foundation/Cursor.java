package Foundation;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cursor {

    private double posX;
    private double posY;
    private boolean posChanged;
    private Coord pressedPos;
    private boolean dragEnd;

    private boolean pressed;

    private Input input;
    private Frame frame;

    private Window activeWindow;
    private WindowElement activeWindowElement;
    private WindowElementGroup activeWindowElementGroup;

    private WindowElement clickedWindowElement;

    public Cursor(Frame frame, Input input){
        this.frame = frame;
        this.input = input;
        pressed = false;
        dragEnd = true;
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
        LinkedList<Window> windowList = frame.getWindows();
        for (int i = windowList.size() - 1; i >= 0; i--){
            Window window = windowList.get(i);
            if (window.contain(point)){
                activeWindow = window;
                Coord temp = point.add(window.getShift().multiply(-1));
                ArrayList<WindowElementGroup> windowElementGroups = window.getWindowElementGroups();
                for (WindowElementGroup windowElementGroup: windowElementGroups){
                    if (windowElementGroup.contain(temp)){
                        activeWindowElementGroup = windowElementGroup;
                        ArrayList<WindowElement> windowElements = windowElementGroup.getWindowElements();
                        temp = point.add(windowElementGroup.getShift().multiply(-1));
                        for (WindowElement windowElement: windowElements){
                            if (windowElement.contain(temp)){
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
                temp = point.add(window.getShift().multiply(-1));
                for (int j = windowElements.size() - 1; j >= 0; j--){
                    WindowElement windowElement = windowElements.get(j);
                    if (windowElement.contain(temp)){
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
        if (button == 0) {
            dragEnd = true;
            action(point, pressed);
        }
        if (button == 1) additionalAction(point, pressed);
    }

    public void action(Coord point, boolean pressed){
        this.pressed = pressed;
        if (!pressed) {
            actionWindow(point);
            pressedPos = null;
        }
        else{
            pressedPos = point;
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
            activeWindow.scroll(delta, new Coord((int)posX, (int)posY).add(activeWindow.getShift().multiply(-1)));
        }
    }

    public void actionWindow(Coord point){
        if (activeWindowElement != null) {
            activeWindowElement.click(point.add(activeWindowElement.getShift().multiply(-1)));
            clickedWindowElement = activeWindowElement;
            return;
        }
        if (activeWindowElementGroup != null) {
            activeWindowElementGroup.click(point.add(activeWindowElementGroup.getShift().multiply(-1)));
            return;
        }
        if (activeWindow != null) {
            activeWindow.click(point.add(activeWindow.getShift().multiply(-1)));
            return;
        }
    }

    public void additionalActionWindow(Coord point){
        if (activeWindowElement != null) {
            activeWindowElement.click2(point.add(activeWindowElement.getShift().multiply(-1)));
            clickedWindowElement = activeWindowElement;
            return;
        }
        if (activeWindowElementGroup != null) {
            activeWindowElementGroup.click2(point.add(activeWindowElementGroup.getShift().multiply(-1)));
            return;
        }
        if (activeWindow != null) {
            activeWindow.click2(point.add(activeWindow.getShift().multiply(-1)));
            return;
        }
    }

    public void drag(Coord point){
        if (activeWindowElement != null) {
            boolean dragged = activeWindowElement.drag(point.add(activeWindowElement.getShift().multiply(-1)),
                    pressedPos.add(activeWindowElement.getShift().multiply(-1)), dragEnd);
            if (dragged) {
                dragEnd = false;
                clickedWindowElement = activeWindowElement;
                return;
            }
        }
        if (activeWindowElementGroup != null) {
            boolean dragged = activeWindowElementGroup.drag(point.add(activeWindowElementGroup.getShift().multiply(-1)),
                    pressedPos.add(activeWindowElementGroup.getShift().multiply(-1)), dragEnd);
            if (dragged) {
                dragEnd = false;
                return;
            }
        }
        if (activeWindow != null) {
            boolean dragged = activeWindow.drag(point.add(activeWindow.getShift().multiply(-1)),
                    pressedPos.add(activeWindow.getShift().multiply(-1)), dragEnd);
            if (dragged) {
                dragEnd = false;
                return;
            }
        }
    }

    public WindowElement getClickedWindowElement() {
        return clickedWindowElement;
    }
}
