package WindowElements.ScrollElements;

import Foundation.*;
import Utils.Geometry.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.WindowElement;

import java.util.ArrayList;

public class ScrollableRow {

    protected ArrayList<WindowElement> rowElements;

    protected Coord shift;
    protected Coord size;
    protected ScrollableGroup parent;

    public ScrollableRow(Coord size, ScrollableGroup parent) {
        this.size = new Coord(size);
        this.parent = parent;
        this.shift = new Coord();
        rowElements = new ArrayList<>();
    }


    public Coord getSize() {
        return size;
    }


    public void setRow(ArrayList<WindowElement> row){
        rowElements = row;
    }

    public void run(){
    }

    public void draw(OpenGLBinder openGLBinder){
        for(WindowElement windowElement: rowElements){
            windowElement.draw(openGLBinder);
        }
    }

    public void shift(Coord shift){
        for(WindowElement windowElement: rowElements){
            windowElement.shift(shift);
        }
        this.shift = this.shift.add(shift);
    }

    public boolean contain(Coord point){
        for(WindowElement windowElement: rowElements){
            if (windowElement.contain(point)) return true;
        }
        return false;
    }

    public Coord getShift(){
        return shift;
    }


}
