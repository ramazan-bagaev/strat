package Foundation;

import Images.TreeImage;

public class Tree extends Element{


    private FieldMap map;

    public Tree(Time time, FieldMap map, Field parent) {
        super(Type.Tree, time, parent, map);
        this.map = map;
        setShapes();

    }

    public void setShapes(){
        Coord pos = new Coord(getParent().getFieldMapPos());
        int size = map.getFieldSize();
        pos.x = pos.x * size;
        pos.y = pos.y * size;
        setBasicShapes(new TreeImage(pos, new Coord(size, size), null).getBasicShapes());
    }

}