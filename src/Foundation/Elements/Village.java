package Foundation.Elements;

import Foundation.Coord;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;
import Images.VillageImage;

public class Village extends Element{

    private Manor manor;

    public Village(Time time, Field parent, FieldMap map, Manor manor) {
        super(Type.Village, time, parent, map);
        this.manor = manor;

        int size = parent.getSize();
        setBasicShapes(new VillageImage(new Coord(0, 0), new Coord(size, size), null).getBasicShapesRemoveAndShiftBack());
    }
}
