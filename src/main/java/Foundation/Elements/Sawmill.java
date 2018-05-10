package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.WorksP.SawmillWork;
import Images.SawmillImage;
import Utils.Coord;

public class Sawmill extends WorkFieldElement {

    private Village village;

    public Sawmill(Village village, People people, Time time, Field parent, FieldMap map) {
        super(Type.Sawmill, people, village, time, parent, map);
        this.village = village;

        setBasicShapes(new SawmillImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());

        work = new SawmillWork(people, village, this);
    }

    public Village getVillage() {
        return village;
    }
}
