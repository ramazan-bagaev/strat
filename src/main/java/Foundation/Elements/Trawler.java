package Foundation.Elements;

import Foundation.*;
import Foundation.Person.People;
import Images.TrawlerImage;
import Utils.Geometry.Coord;

public class Trawler extends WorkFieldElement {

    private Village village;


    public Trawler(Village village, People people, Field parent) {
        super(Type.Trawler, people, village, parent);

        this.village = village;

        setBasicShapes(new TrawlerImage(new Coord(0, 0), new Coord(parent.getSize(), parent.getSize()), null)
                .getBasicShapesRemoveAndShiftBack());
        //work = new TrawlerWork(people, village, this);
    }

    public Village getVillage() {
        return village;
    }
}
