package Windows;

import Foundation.Coord;
import Foundation.Window;
import Foundation.Windows;

public class ClosableWindow extends Window {

    public ClosableWindow(Coord pos, Coord size, Windows parent) {
        super(pos, size, parent);
        CloseButton closeButton = new CloseButton(new Coord(pos.x + size.x - 15, pos.y), new Coord(15, 15), this);
        addWindowElement(closeButton);
    }

    public void close(){
        getParent().removeWindow(this);
    }




}
