package Foundation.Elements;

import Foundation.Coord;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Person.People;
import Foundation.Time;
import Images.VillageImage;

public class Village extends Element{

    private Manor manor;
    private People people;

    public Village(Time time, Field parent, FieldMap map, Manor manor) {
        super(Type.Village, time, parent, map);
        this.manor = manor;
        this.people = parent.getPeople();

        int size = parent.getSize();
        setBasicShapes(new VillageImage(new Coord(0, 0), new Coord(size, size), parent.getRandom(), null).getBasicShapesRemoveAndShiftBack());
    }

    public Manor getManor() {
        return manor;
    }

    public People getPeople() {
        return people;
    }
}
