package Foundation.Elements;

import Foundation.Field;
import Images.TreeImage;
import Utils.Geometry.Coord;

public class Tree extends FieldElement {



    public Tree(Field parent) {
        super(Type.Tree, parent);
        setShapes();

    }

    public void setShapes(){
        int size = parent.getSize();
        setBasicShapes(new TreeImage(new Coord(0, 0), new Coord(size, size), parent.getRandom(), null).getBasicShapesRemoveAndShiftBack());
    }

}