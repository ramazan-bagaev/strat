package Windows;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.GameWindowHelper.States.HelperState;
import Foundation.GameWindowHelper.States.StandartState;
import Images.CityImage;
import Images.Image;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;

public class CityInfoWindow extends ClosableWindow {

    private City city;

    public CityInfoWindow(City city, Windows parent) {
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

        Image cityImage = new CityImage(getPos(), new Coord(100, 100), city.getSizeType(), this);
        addWindowElement(cityImage);

        StaticBroadcastLabel citySizeLabel = new StaticBroadcastLabel(new Coord(10, 120).add(getPos()), new Coord(150, 10), "Type:",
                city, "sizeType", this);
        addWindowElement(citySizeLabel);

        MonitoredBroadcastLabel cityPopulationLabel = new MonitoredBroadcastLabel(new Coord(10, 140).add(getPos()), new Coord(200, 10),
                "Population:", city, "population", this){
            @Override
            public void click(Coord point){
                Windows windows = getParent().getParent();
                windows.addSpecialWindow("population group window", new PopulationInfoWindow(getCity().getPopulation(), windows));
            }
        };
        addWindowElement(cityPopulationLabel);

        Button addWork = new Button(new Coord(10, 160).add(getPos()), new Coord(150, 20), this, "add work") {


            @Override
            public void click(Coord point) {
                Windows windows = getParent().getParent();
                windows.addSpecialWindow("work list window", new WorkListWindow(city, windows));
            }
        };

        addWindowElement(addWork);

        Button addArmy = new Button(new Coord(10, 180).add(getPos()), new Coord(150, 20), this, "add army") {
            @Override
            public void click(Coord point){
                Windows windows = getParent().getParent();
                windows.addSpecialWindow("army add window", new ArmyAddWindow(city, windows));
            }
        };

        addWindowElement(addArmy);

        Button openResourceStore = new Button(new Coord(10, 200).add(getPos()), new Coord(200, 20), this, "resources") {
            @Override
            public void click(Coord point) {
                Windows windows = getParent().getParent();
                windows.addSpecialWindow("resource store window", new ResourceStoreWindow(getCity().getResourceStore(), windows));
            }
        };

        addWindowElement(openResourceStore);
    }
}
