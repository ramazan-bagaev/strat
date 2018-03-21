package Foundation.Elements;

import Foundation.Coord;
import Foundation.Elements.Element;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;
import Images.TreeImage;

public class Tree extends Element {


    private FieldMap map;

    public Tree(Time time, FieldMap map, Field parent) {
        super(Type.Tree, time, parent, map);
        this.map = map;
        setShapes();

    }

    public void setShapes(){
        Coord pos = new Coord(getParent().getFieldMapPos());
        int size = parent.getSize();
        setBasicShapes(new TreeImage(new Coord(0, 0), new Coord(size, size), parent.getRandom(), null).getBasicShapesRemoveAndShiftBack());
    }

}