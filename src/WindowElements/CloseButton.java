package WindowElements;

import Foundation.Button;
import Utils.Coord;
import Windows.ClosableWindow;

public class CloseButton extends Button {


    public CloseButton(Coord pos, Coord size, ClosableWindow parent) {
        super(pos, size, parent, "x");
    }

    @Override
    public void click(Coord point) {
        ClosableWindow closableWindow = (ClosableWindow)getParent();
        closableWindow.close();
    }
}
