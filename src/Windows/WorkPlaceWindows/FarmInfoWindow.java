package Windows.WorkPlaceWindows;

import Foundation.*;
import Images.FarmImage;
import Windows.ClosableWindow;

public class FarmInfoWindow extends ClosableWindow {

    private Farm farm;

    public FarmInfoWindow(Farm farm, Windows parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.farm = farm;

        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        Image farmImage = new FarmImage(getPos(), new Coord(100, 100), this);
        addWindowElement(farmImage);
        
    }

    public void setFarm(Farm farm){
        this.farm = farm;
        setElements();
    }
}
