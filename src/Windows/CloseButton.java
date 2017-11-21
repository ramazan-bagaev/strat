package Windows;

import Foundation.Button;
import Foundation.Coord;
import Foundation.Window;

public class CloseButton extends Button {


    public CloseButton(Coord pos, Coord size, ClosableWindow parent) {
        super(pos, size, parent, "x");
    }

    @Override
    public void click() {
        ClosableWindow closableWindow = (ClosableWindow)getParent();
        closableWindow.close();
    }
}
