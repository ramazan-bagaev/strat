package Windows.ElementInfoWindow;

import Foundation.Frame;
import Foundation.Runnable.Country;
import Utils.Coord;
import WindowElements.StaticBroadcastLabel;
import Windows.ClosableWindow;

public class CountryInfoWindow extends ClosableWindow {

    private Country country;

    public CountryInfoWindow(Country country, Frame parent) {
        super(new Coord(400, 0), new Coord(200, 300), parent);
        this.country = country;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        StaticBroadcastLabel name = new StaticBroadcastLabel(new Coord(20, 10), new Coord(150, 10), "name: ",
                country, "name", this);
        addWindowElement(name);

        StaticBroadcastLabel kingName = new StaticBroadcastLabel(new Coord(20, 30), new Coord(150, 10), "king: ",
                country.getKing(), "name", this);
        addWindowElement(kingName);


    }
}
