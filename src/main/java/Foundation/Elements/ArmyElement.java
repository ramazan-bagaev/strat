package Foundation.Elements;

import Foundation.*;
import Foundation.Army.Army;
import Foundation.BasicShapes.BasicShape;
import Images.ArmyImage;
import Utils.Coord;

import java.util.ArrayList;

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
