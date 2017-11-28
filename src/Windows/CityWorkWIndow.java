package Windows;

import Foundation.*;
import WindowElements.SliderElement;

public class CityWorkWIndow extends ClosableWindow {

    private City city;
    private SliderElement slider;

    public CityWorkWIndow(City city, Windows parent) {
        super(new Coord(400, 400), new Coord(200, 200), parent);
        this.city = city;

        InputWindowElement inputWindowElement = new InputWindowElement(new Coord(10, 30).add(getPos()), new Coord(170, 20), this);
        addWindowElement(inputWindowElement);

        SliderElement slider = new SliderElement(new Coord(10, 70).add(getPos()), new Coord(170, 20), true, 0, 100, this);
        addWindowElement(slider);
        this.slider = slider;
    }

    @Override
    public void click(Coord point){
        System.out.println(slider.getValue());
    }
}
