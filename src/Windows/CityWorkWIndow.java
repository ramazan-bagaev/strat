package Windows;

import Foundation.*;

public class CityWorkWIndow extends ClosableWindow {

    private City city;

    public CityWorkWIndow(City city, Windows parent) {
        super(new Coord(400, 400), new Coord(200, 200), parent);
        this.city = city;

        InputWindowElement inputWindowElement = new InputWindowElement(new Coord(10, 30).add(getPos()), new Coord(170, 20), this);
        addWindowElement(inputWindowElement);
    }
}
