package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.WorksP.SawmillWork;
import Images.SawmillImage;
import Utils.Coord;

public class Sawmill extends Element{

    private Village village;
    private Work work;
    private People people;
    private int efficiency;

    public Sawmill(Village village, Time time, Field parent, FieldMap map) {
        super(Type.Sawmill, time, parent, map);
        this.village = village;

        setBasicShapes(new SawmillImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());
        efficiency = 10;
        people = village.getPeople();
        work = new SawmillWork(village, this);
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
