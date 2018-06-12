package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Images.MineImage;
import Utils.Geometry.Coord;

public class Mine extends WorkFieldElement {

    private Village village;

    public Mine(Village village, People people, Field parent) {
        super(Type.Mine, people, village, parent);

        this.village = village;

        setBasicShapes(new MineImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());
        //work = new MineWork(people, village, this);
    }

    public Village getVillage() {
        return village;
    }

}
