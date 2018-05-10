package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Foundation.WorksP.TrawlerWork;
import Images.TrawlerImage;
import Utils.Coord;

public class Trawler extends WorkFieldElement {

    private Village village;


    public Trawler(Village village, People people, Time time, Field parent, FieldMap map) {
        super(Type.Trawler, people, village, time, parent, map);

        this.village = village;

        setBasicShapes(new TrawlerImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());
        work = new TrawlerWork(people, village, this);
    }

    public Village getVillage() {
        return village;
    }
}
