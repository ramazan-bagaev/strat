package Windows;

import Utils.Geometry.Coord;
import Foundation.Frame;
import WindowElements.WindowElement;
import WindowElements.TimeElement;

import java.util.ArrayList;

public class MainToolbarWindow extends Window{

    public MainToolbarWindow(Frame parent){
        super(new Coord(0, 0), new Coord(1000, 30), parent);

        setElements();
    }

    public void setElements(){
        ArrayList<WindowElement> windowElements = getWindowElements();
        windowElements.clear();

        TimeElement timeElement = new TimeElement(new Coord(800, 0), new Coord(100, 20), getParent().getGameEngine().getTime(), this);
        windowElements.add(timeElement);
    }
}
