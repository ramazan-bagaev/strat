package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.WorksP.FarmWork;
import Images.FarmImage;

import java.util.ArrayList;

public class Farm extends Element {

    private Work work;
    private Village village;
    private People people;
    private int efficiency;

    public Farm(Village village, Time time, Field parent, FieldMap map) {
        super(Type.Farm, time, parent, map);
        this.village = village;
        ArrayList<BasicShape> image = new FarmImage(new Coord(0, 0),
                new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack();
        setBasicShapes(image);
        efficiency = 10;
        people = parent.getPeople();
        work = new FarmWork(village, this);
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

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }
}
