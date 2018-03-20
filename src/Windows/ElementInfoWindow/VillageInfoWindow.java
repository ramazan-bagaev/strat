package Windows.ElementInfoWindow;

import Foundation.BasicShape;
import Foundation.Coord;
import Foundation.Elements.Village;
import Foundation.Frame;
import Images.Image;
import Windows.ClosableWindow;

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
            basicShape.changeSize(2);
        }
        Image villageImage = new Image(new Coord(0, 0), new Coord(100, 100),this, shapes);
        addWindowElement(villageImage);


    }
}
