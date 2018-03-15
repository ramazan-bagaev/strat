package Foundation.Elements;

import Foundation.*;
import Images.FarmImage;

import java.util.ArrayList;

public class Farm extends Element {

    private Manor manor;

    public Farm(Manor manor, Time time, Field parent, FieldMap map) {
        super(Type.Farm, time, parent, map);
        this.manor = manor;
        Coord pos = new Coord(parent.getFieldMapPos());
        pos.x = pos.x * parent.getSize();
        pos.y = pos.y * parent.getSize();
        ArrayList<BasicShape> image = new FarmImage(pos,
                new Coord(parent.getSize(), parent.getSize()), null).getBasicShapes();
        setBasicShapes(image);
    }

    public Manor getManor() {
        return manor;
    }

    public void setManor(Manor manor) {
        this.manor = manor;
    }
}
