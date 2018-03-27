package WindowElements;

import Foundation.Button;
import Utils.Coord;
import Windows.ClosableWindow;

public class CloseButton extends Button {


    public CloseButton(Coord pos, Coord size, String text, ClosableWindow parent) {
        super(pos, size, text, parent);
    }

    public CloseButton(Coord pos, Coord size, ClosableWindow parent) {
        super(pos, size, "x", parent);
    }

    @Override
    public void click(Coord point) {
        ClosableWindow closableWindow = (ClosableWindow)getParent();
        closableWindow.close();
    }
}
