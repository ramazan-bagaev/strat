package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.WorksP.MineWork;
import Images.MineImage;

public class Mine extends Element {

    private Village village;
    private Work work;
    private People people;
    private int efficiency;

    public Mine(Village village, Time time, Field parent, FieldMap map) {
        super(Type.Mine, time, parent, map);

        this.village = village;

        setBasicShapes(new MineImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());
        efficiency = 10;
        people = village.getPeople();
        work = new MineWork(village, this);
    }

    public Village getVillage() {
        return village;
    }

    public People getPeople() {
        return people;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public Work getWork() {
        return work;
    }
}
