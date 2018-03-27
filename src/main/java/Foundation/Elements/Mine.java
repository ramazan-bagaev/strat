package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.Person.Society;
import Foundation.WorksP.MineWork;
import Images.MineImage;
import Utils.Coord;

public class Mine extends WorkElement {

    private Village village;

    public Mine(Village village, People people, Time time, Field parent, FieldMap map) {
        super(Type.Mine, people, village, time, parent, map);

        this.village = village;

        setBasicShapes(new MineImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());
        work = new MineWork(village, this);
    }

    public Village getVillage() {
        return village;
    }

}
