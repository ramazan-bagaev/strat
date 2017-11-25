package WindowElementGroups;

import Foundation.*;

import java.util.ArrayList;

public class ScrollableGroup extends WindowElementGroup{

    private ArrayList<WindowElement> scrollableElements;
    private int bottom;
    private int top;
    private int size;


    public ScrollableGroup(Coord pos, Coord size, ArrayList<WindowElement> scrollableElements, Window parent) {
        super(pos, size, parent);
        setScrollableElements(scrollableElements);
    }

    @Override
    public void draw(OpenGLBinder openGLBinder){

    }


    public ArrayList<WindowElement> getScrollableElements() {
        return scrollableElements;
    }

    public void setScrollableElements(ArrayList<WindowElement> scrollableElements) {
        this.scrollableElements = scrollableElements;
    }
}
