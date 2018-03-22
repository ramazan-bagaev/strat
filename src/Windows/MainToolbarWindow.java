package Windows;

import Utils.Coord;
import Foundation.Frame;
import Foundation.Window;
import Foundation.WindowElement;
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

        TimeElement timeElement = new TimeElement(new Coord(900, 0), new Coord(100, 30), getParent().getGameEngine().getTime(), this);
        windowElements.add(timeElement);
    }
}
