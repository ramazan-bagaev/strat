package WindowElementGroups;

import Foundation.Coord;
import Foundation.Window;
import Foundation.WindowElement;

import java.util.ArrayList;

public class PopulationAllGroup extends ScrollableGroup {


    public PopulationAllGroup(Coord pos, int width, int elementOnScreen, int rowHeight, ArrayList<WindowElement> scrollableElements, Window parent) {
        super(pos, width, elementOnScreen, rowHeight, parent);
    }
}
