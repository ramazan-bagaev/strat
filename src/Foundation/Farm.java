package Foundation;

import Images.FarmImage;

import java.util.ArrayList;

public class Farm extends Element{

    private ArrayList<Coord> activeFieldPos;

    public Farm(Time time, Field parent, FieldMap map) {
        super(Type.WorkPlace, time, parent, map);
        activeFieldPos = new ArrayList<>();
        activeFieldPos.add(parent.getFieldMapPos());
        setBasicShapes(new FarmImage(parent.getFieldMapPos(), new Coord(parent.getSize(), parent.getSize()), null).getBasicShapes());
    }
}
