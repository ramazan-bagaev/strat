package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.WorksP.FarmWork;
import Images.FarmImage;

import java.util.ArrayList;

public class Farm extends Element {

    private Work work;
    private Manor manor;
    private People people;
    private int efficiency;

    public Farm(Manor manor, Time time, Field parent, FieldMap map) {
        super(Type.Farm, time, parent, map);
        this.manor = manor;
        ArrayList<BasicShape> image = new FarmImage(new Coord(0, 0),
                new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack();
        setBasicShapes(image);
        efficiency = 10;
        people = parent.getPeople();
        work = new FarmWork(manor, this);
    }

    public Manor getManor() {
        return manor;
    }

    public void setManor(Manor manor) {
        this.manor = manor;
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
