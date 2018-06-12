package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Images.SawmillImage;
import Utils.Geometry.Coord;

public class Sawmill extends WorkFieldElement {

    private Village village;

    public Sawmill(Village village, People people, Field parent) {
        super(Type.Sawmill, people, village, parent);
        this.village = village;

        setBasicShapes(new SawmillImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());

        //work = new SawmillWork(people, village, this);
    }

    public Village getVillage() {
        return village;
    }
}
