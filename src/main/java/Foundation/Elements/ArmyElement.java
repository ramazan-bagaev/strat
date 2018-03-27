package Foundation.Elements;

import Foundation.*;
import Foundation.Runnable.Army;
import Images.ArmyImage;
import Utils.Coord;

public class ArmyElement extends Element{

    private Army army;

    public ArmyElement(Time time, Field parent, FieldMap map, Army army) {
        super(Type.Army, time, parent, map);
        this.army = army;
        int size = parent.getSize();
        setBasicShapes(new ArmyImage(new Coord(), new Coord(size, size), null).getBasicShapesRemoveAndShiftBack());
    }

    public Army getArmy() {
        return army;
    }
}