package Foundation.Elements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.Person.People;
import Images.FarmImage;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class Farm extends WorkFieldElement {

    private Village village;


    public Farm(Village village, People people, Field parent) {
        super(Type.Farm, people, village, parent);
        this.village = village;
        ArrayList<BasicShape> image = new FarmImage(new Coord(0, 0),
                new Coord(parent.getSize(), parent.getSize()), null).getBasicShapesRemoveAndShiftBack();
        setBasicShapes(image);
        //work = new FarmWork(people, village, this);
    }


    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }
}
