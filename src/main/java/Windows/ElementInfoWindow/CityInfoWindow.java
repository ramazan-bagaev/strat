package Windows.ElementInfoWindow;

import Foundation.*;
import Foundation.Elements.City;
import Images.CityImage;
import Images.Image;
import Utils.Geometry.Coord;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;
import Windows.*;
import Windows.IntermediatWindows.ChooseOnePerson.ChooseManorLordWindow;

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

        Image cityImage = new CityImage(new Coord(0, 0), new Coord(100, 100), this);
        addWindowElement(cityImage);

        StaticBroadcastLabel citySizeLabel = new StaticBroadcastLabel(new Coord(10, 120), new Coord(150, 10), "Type:",
                city, "sizeType", this);
        addWindowElement(citySizeLabel);

        MonitoredBroadcastLabel cityPopulationLabel = new MonitoredBroadcastLabel(new Coord(10, 140), new Coord(200, 10),
                "Population:", city.getSociety().getPeople(), "amount", this) {
            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("population group window", new PopulationInfoWindow(getCity().getSociety(), frame));
            }
        };
        addWindowElement(cityPopulationLabel);

        Button addWork = new Button(new Coord(10, 160), new Coord(150, 20), "add manor", this) {


            @Override
            public void click(Coord point) {
                getParent().getParent().addSpecialWindow("population group window", new ChooseManorLordWindow(city, getParent().getParent()));
            }
        };

        addWindowElement(addWork);

        Button addArmy = new Button(new Coord(10, 180), new Coord(150, 20),"add army", this) {
            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("army add window", new ArmyAddWindow(city, frame));
            }
        };

        addWindowElement(addArmy);

        Button openResourceStore = new Button(new Coord(10, 200), new Coord(200, 20),"resources", this) {
            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ResourceStoreWindow(getCity().getResourceStore(), frame));
            }
        };

        addWindowElement(openResourceStore);
        if (city.getCountry() != null) {
            StaticBroadcastLabel countryName = new StaticBroadcastLabel(new Coord(10, 240), new Coord(200, 10), "country: ", city.getCountry(),
                    "name", this){

                @Override
                public void click(Coord point){
                    Frame frame = getParent().getParent();
                    frame.addSpecialWindow("country info window", new CountryInfoWindow(city.getCountry(), frame));
                }
            };
            addWindowElement(countryName);
        }
        else{
            Label noCountry = new Label(new Coord(10, 240), new Coord(200, 10), "no country", this);
            addWindowElement(noCountry);
        }
    }
}
