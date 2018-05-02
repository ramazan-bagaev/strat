package Foundation.Elements;

import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;
import Images.TreeImage;
import Utils.Coord;

public class Tree extends FieldElement {


    private FieldMap map;

    public Tree(Time time, FieldMap map, Field parent) {
        super(Type.Tree, time, parent, map);
        this.map = map;
        setShapes();

    }

    public void setShapes(){
        int size = parent.getSize();
        setBasicShapes(new TreeImage(new Coord(0, 0), new Coord(size, size), parent.getRandom(), null).getBasicShapesRemoveAndShiftBack());
    }

}