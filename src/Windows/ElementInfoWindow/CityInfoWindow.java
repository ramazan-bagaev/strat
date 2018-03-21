package Windows.ElementInfoWindow;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.GameWindowHelper.States.AddManorState;
import Foundation.GameWindowHelper.States.HelperState;
import Foundation.GameWindowHelper.States.StandartState;
import Images.CityImage;
import Images.Image;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;
import Windows.*;

import java.util.ArrayList;

public class CityInfoWindow extends ClosableWindow {

    private City city;

    public CityInfoWindow(City city, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        setCity(city);
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;

        removeWindowElements();
        addCloseButton();

        Image cityImage = new CityImage(new Coord(0, 0), new Coord(100, 100), city.getSizeType(), this);
        addWindowElement(cityImage);

        StaticBroadcastLabel citySizeLabel = new StaticBroadcastLabel(new Coord(10, 120), new Coord(150, 10), "Type:",
                city, "sizeType", this);
        addWindowElement(citySizeLabel);

        MonitoredBroadcastLabel cityPopulationLabel = new MonitoredBroadcastLabel(new Coord(10, 140), new Coord(200, 10),
                "Population:", city, "population", this) {
            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("population group window", new PopulationInfoWindow(getCity().getPopulation(), frame));
            }
        };
        addWindowElement(cityPopulationLabel);

        Button addWork = new Button(new Coord(10, 160), new Coord(150, 20), this, "add manor") {


            @Override
            public void click(Coord point) {
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddManorState addManorState = new AddManorState(gameWindowHelperElement, city);
                gameWindowHelperElement.setState(addManorState);
                close();
            }
        };

        addWindowElement(addWork);

        Button addArmy = new Button(new Coord(10, 180), new Coord(150, 20), this, "add army") {
            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("army add window", new ArmyAddWindow(city, frame));
            }
        };

        addWindowElement(addArmy);

        Button openResourceStore = new Button(new Coord(10, 200), new Coord(200, 20), this, "resources") {
            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ResourceStoreWindow(getCity().getResourceStore(), frame));
            }
        };

        addWindowElement(openResourceStore);
    }
}
