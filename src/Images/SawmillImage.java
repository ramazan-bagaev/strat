package Images;

import Foundation.BasicShape;
import Foundation.Coord;
import Foundation.Window;

import java.util.ArrayList;

public class SawmillImage extends Image{

    public SawmillImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        Coord size = getSize();
        ArrayList<Coord> pol1 = new ArrayList<>();
        pol1.add(new Coord(7*size.x/10, size.y/10));
    }
}
