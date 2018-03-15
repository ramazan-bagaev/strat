package Foundation.Elements;

import Foundation.*;
import Images.ArmyImage;

public class ArmyElement extends Element{

    private Army army;

    public ArmyElement(Time time, Field parent, FieldMap map, Army army) {
        super(Type.Army, time, parent, map);
        this.army = army;
        Coord pos = new Coord(parent.getFieldMapPos());
        int size = parent.getSize();
        pos.x = pos.x * size;
        pos.y = pos.y * size;
        setBasicShapes(new ArmyImage(pos, new Coord(size, size), null).getBasicShapes());
    }

    public Army getArmy() {
        return army;
    }
}
