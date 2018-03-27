package Windows;

import Utils.Coord;
import Foundation.Frame;
import Foundation.Window;
import WindowElements.CloseButton;

public class ClosableWindow extends Window {

    public ClosableWindow(Coord pos, Coord size, Frame parent) {
        super(pos, size, parent);
        addCloseButton();
    }

    public void close(){
        getParent().removeWindow(getId());
    }

    public void addCloseButton(){
        CloseButton closeButton = new CloseButton(new Coord(getSize().x - 15, 0), new Coord(15, 15), this);
        addWindowElement(closeButton);
    }



}
