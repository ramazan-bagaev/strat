package Windows.ElementInfoWindow;

import Foundation.BasicShape;
import Foundation.Button;
import Foundation.Coord;
import Foundation.Elements.Village;
import Foundation.Frame;
import Images.Image;
import Windows.ClosableWindow;
import Windows.ResourceStoreWindow;
import Windows.WorkPlaceWindows.VillageWorkWindow;

import java.util.ArrayList;

public class VillageInfoWindow extends ClosableWindow{

    private Village village;

    public VillageInfoWindow(Village village, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.village = village;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        ArrayList<BasicShape> shapes = village.getCopyOfBasicShapesWithoutShift();
        for (BasicShape basicShape: shapes){
            basicShape.changeSize(100/village.getParent().getSize());
        }
        Image villageImage = new Image(new Coord(0, 0), new Coord(100, 100),this, shapes);
        addWindowElement(villageImage);

        Button addWork = new Button(new Coord(10, 120), new Coord(150, 20), this, "add work") {


            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("work list window", new VillageWorkWindow(village, frame));
            }
        };

        addWindowElement(addWork);

        Button showResource = new Button(new Coord(20, 160), new Coord(200, 20), this, "show resources"){

            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ResourceStoreWindow(village.getResourceStore(), frame));
            }
        };

        addWindowElement(showResource);



    }
}
