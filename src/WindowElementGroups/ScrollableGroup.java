package WindowElementGroups;

import Foundation.*;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;

public class ScrollableGroup extends WindowElementGroup{

    private ArrayList<WindowElement> scrollableElements;
    private int bottomIndex;
    private int elementOnScreen;
    private int rowHeight;

    public ScrollableGroup(Coord pos, int width, int elementOnScreen, int rowHeight, Window parent){
        super(pos, new Coord(width, elementOnScreen * rowHeight), parent);
        setElementOnScreen(elementOnScreen);
        setRowHeight(rowHeight);
        addScrollButton();
    }

    public ScrollableGroup(Coord pos, int width, int elementOnScreen, int rowHeight, ArrayList<WindowElement> scrollableElements, Window parent) {
        super(pos, new Coord(0, 0), parent);
        setScrollableElements(scrollableElements);
        if (scrollableElements.size() <= elementOnScreen) elementOnScreen = scrollableElements.size();
        setRowHeight(rowHeight);
        setSize(new Coord(width, rowHeight * elementOnScreen));
        setElementOnScreen(elementOnScreen);
        addScrollButton();
    }

    public void addScrollButton(){
        Button buttonUp = new Button(new Coord(getSize().x - 15, 0).add(getPos()), new Coord(15, 20), this, getParent(), "up") {
            @Override
            public void click(Coord point) {
                ScrollableGroup scrollableGroup = (ScrollableGroup)getGroupParent();
                scrollableGroup.bottomChange(-1);
            }
        };

        Button buttonDown = new Button(new Coord(getSize().x - 15, getSize().y - 20).add(getPos()), new Coord(15, 20), this, getParent(), "down") {
            @Override
            public void click(Coord point) {
                ScrollableGroup scrollableGroup = (ScrollableGroup)getGroupParent();
                scrollableGroup.bottomChange(1);
            }
        };
        addWindowElement(buttonDown);
        addWindowElement(buttonUp);
    }

    @Override
    public void draw(OpenGLBinder openGLBinder){
        super.draw(openGLBinder);
        for (int i = bottomIndex; i < bottomIndex + elementOnScreen; i++){
            scrollableElements.get(i).draw(openGLBinder);
        }

    }


    public ArrayList<WindowElement> getScrollableElements() {
        return scrollableElements;
    }

    public void setScrollableElements(ArrayList<WindowElement> scrollableElements) {
        this.scrollableElements = scrollableElements;
        int num = scrollableElements.size();

        if (num < elementOnScreen) elementOnScreen = num;

        for(WindowElement windowElement: scrollableElements){
            windowElement.setGroupParent(this);
        }
    }

    public int getBottomIndex() {
        return bottomIndex;
    }

    public void setBottomIndex(int bottomIndex) {
        this.bottomIndex = bottomIndex;
    }

    public void bottomChange(int delta){
        int newBottomIndex = bottomIndex;
        newBottomIndex += delta;
        if (newBottomIndex + elementOnScreen > scrollableElements.size() - 1) newBottomIndex = scrollableElements.size() - elementOnScreen - 1;
        if (newBottomIndex < 0) newBottomIndex = 0;

        //System.out.println("bottom index " + newBottomIndex);
        int realDelta = bottomIndex - newBottomIndex;
        if (realDelta != 0) {
            bottomIndex = newBottomIndex;
            Coord shift = new Coord(0, realDelta * getRowHeight());
            for (WindowElement windowElement : scrollableElements) {
                windowElement.shift(shift);
            }
        }
    }

    public int getElementOnScreen() {
        return elementOnScreen;
    }

    public void setElementOnScreen(int elementOnScreen) {
        this.elementOnScreen = elementOnScreen;
    }

    public void scroll(int delta){
        bottomChange(delta);
    }

    public void run(){
        super.run();

        for (int i = bottomIndex; i < bottomIndex + elementOnScreen; i++){
            WindowElement windowElement = scrollableElements.get(i);
            if (windowElement != null) scrollableElements.get(i).run();
        }
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }
}
