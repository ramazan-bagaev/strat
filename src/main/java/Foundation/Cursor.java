package Foundation;

import Utils.Geometry.Coord;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cursor {

    private Coord pos;
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
        pos = new Coord(0, 0);
        this.frame = frame;
        this.input = input;
        pressed = false;
        dragEnd = true;
    }

    public void run(){
        //if (!frame.isSameModeAs(activeWindow)) return;
        if (activeWindowElement != null){
            activeWindowElement.hoover(pos.add(activeWindowElement.getShift().multiply(-1)));
        }
        if (activeWindowElementGroup != null){
            activeWindowElementGroup.hoover(pos.add(activeWindowElementGroup.getShift().multiply(-1)));
        }
        if (activeWindow != null){
            activeWindow.hoover(pos.add(activeWindow.getShift().multiply(-1)));
        }
    }

    public void posRenew(double x, double y){
        if (x != pos.x){
            pos.x = x;
            posChanged = true;
        }
        if (y != pos.y){
            pos.y = y;
            posChanged = true;
        }
        Coord point = new Coord(x, y);
        if (posChanged){
            if (pressed) drag(point);
            renewActive();
        }

        if (activeWindowElement != null){
            activeWindowElement.hoover(pos.add(activeWindowElement.getShift().multiply(-1)));
            return;
        }
        if (activeWindowElementGroup != null){
            activeWindowElementGroup.hoover(pos.add(activeWindowElementGroup.getShift().multiply(-1)));
            return;
        }
        if (activeWindow != null){
            activeWindow.hoover(pos.add(activeWindow.getShift().multiply(-1)));
            return;
        }
    }

    public Window getActiveWindow() {
        return activeWindow;
    }

    public void setActiveWindow(Window activeWindow) {

        if (this.activeWindow != activeWindow) {
            if (this.activeWindow != null) this.activeWindow.deactivate();
            this.activeWindow = activeWindow;
            if (this.activeWindow != null) this.activeWindow.activate();
        }
    }

    public WindowElement getActiveWindowElement() {
        return activeWindowElement;
    }

    public void setActiveWindowElement(WindowElement activeWindowElement) {
        if (this.activeWindowElement != activeWindowElement) {
            if (this.activeWindowElement != null) this.activeWindowElement.deactivate();
            this.activeWindowElement = activeWindowElement;
            if (this.activeWindowElement != null) this.activeWindowElement.activate();
        }
    }

    public void renewActive(){
        LinkedList<Window> windowList = frame.getWindows();
        for (int i = windowList.size() - 1; i >= 0; i--){
            Window window = windowList.get(i);
            if (window.contain(pos)){
                setActiveWindow(window);
                Coord temp = pos.add(window.getShift().multiply(-1));
                ArrayList<WindowElementGroup> windowElementGroups = window.getWindowElementGroups();
                for (WindowElementGroup windowElementGroup: windowElementGroups){
                    if (windowElementGroup.contain(temp)){
                        setActiveWindowElementGroup(windowElementGroup);
                        ArrayList<WindowElement> windowElements = windowElementGroup.getWindowElements();
                        temp = pos.add(windowElementGroup.getShift().multiply(-1));
                        for (WindowElement windowElement: windowElements){
                            if (windowElement.contain(temp)){
                                setActiveWindowElement(windowElement);
                                return;
                            }
                        }
                        setActiveWindowElement(null);
                        return;
                    }
                }
                setActiveWindowElementGroup(null);
                ArrayList<WindowElement> windowElements = window.getWindowElements();
                temp = pos.add(window.getShift().multiply(-1));
                for (int j = windowElements.size() - 1; j >= 0; j--){
                    WindowElement windowElement = windowElements.get(j);
                    if (windowElement.contain(temp)){
                        setActiveWindowElement(windowElement);
                        return;
                    }
                }
                setActiveWindowElement(null);
                return;
            }
        }
    }

    public WindowElementGroup getActiveWindowElementGroup() {
        return activeWindowElementGroup;
    }

    public void setActiveWindowElementGroup(WindowElementGroup activeWindowElementGroup) {
        if (this.activeWindowElementGroup != activeWindowElementGroup) {
            if (this.activeWindowElementGroup != null) this.activeWindowElementGroup.deactivate();
            this.activeWindowElementGroup = activeWindowElementGroup;
            if (this.activeWindowElementGroup != null) this.activeWindowElementGroup.activate();
        }
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
        //if (!frame.isSameModeAs(activeWindow)) return;
        if (activeWindowElementGroup != null){
            activeWindowElementGroup.scroll(delta);
            return;
        }
        if (activeWindow != null){
            activeWindow.scroll(delta, new Coord(pos).add(activeWindow.getShift().multiply(-1)));
        }
    }

    public void actionWindow(Coord point){
        //if (!frame.isSameModeAs(activeWindow)) return;
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
        //if (!frame.isSameModeAs(activeWindow)) return;
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
       // if (!frame.isSameModeAs(activeWindow)) return;
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
