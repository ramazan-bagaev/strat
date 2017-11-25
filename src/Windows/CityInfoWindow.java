package Windows;

import Foundation.*;
import Images.CityImage;

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
        CloseButton closeButton = new CloseButton(new Coord(getPos().x + getSize().x - 15, getPos().y), new Coord(15, 15), this);
        addWindowElement(closeButton);

        Image cityImage = new CityImage(getPos(), new Coord(100, 100), city.getSizeType(), this);
        addWindowElement(cityImage);

        StaticBroadcastLabel citySizeLabel = new StaticBroadcastLabel(new Coord(10, 120).add(getPos()), new Coord(150, 10), "Type:",
                city, "sizeType", this);
        addWindowElement(citySizeLabel);

        MonitoredBroadcastLabel cityPopulationLabel = new MonitoredBroadcastLabel(new Coord(10, 140).add(getPos()), new Coord(200, 10),
                "Population:", city, "population", this){
            @Override
            public void click(Coord point){
                CityInfoWindow cityInfoWindow = (CityInfoWindow)getParent();
                cityInfoWindow.addPopulationGroupWindow(cityInfoWindow.getCity().getPopulation());
            }
        };
        addWindowElement(cityPopulationLabel);
    }

    public void addPopulationGroupWindow(Population population){
        for(Window window: getParent().getWindows()){
            if (window.getClass() == PopulationInfoWindow.class){
                PopulationInfoWindow populationInfoWindow = (PopulationInfoWindow)window;
                populationInfoWindow.setPopulation(population);
                return;
            }
        }
        addWindow(new PopulationInfoWindow(population, getParent()));
    }
}
