package Windows;

import Foundation.Coord;
import Foundation.Window;
import Foundation.Windows;

public class ClosableWindow extends Window {

    public ClosableWindow(Coord pos, Coord size, Windows parent) {
        super(pos, size, parent);
        addCloseButton();
    }

    public void close(){
        getParent().removeWindow(getId());
    }

    public void addCloseButton(){
        CloseButton closeButton = new CloseButton(new Coord(getPos().x + getSize().x - 15, getPos().y), new Coord(15, 15), this);
        addWindowElement(closeButton);
    }



}
