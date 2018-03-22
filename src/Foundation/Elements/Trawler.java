package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.WorksP.TrawlerWork;
import Images.TrawlerImage;
import Utils.Coord;

public class Trawler extends Element {

    private Village village;
    private Work work;
    private People people;
    private int efficiency;

    public Trawler(Village village, Time time, Field parent, FieldMap map) {
        super(Type.Trawler, time, parent, map);

        this.village = village;

        setBasicShapes(new TrawlerImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());
        efficiency = 10;
        people = village.getPeople();
        work = new TrawlerWork(village, this);
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
