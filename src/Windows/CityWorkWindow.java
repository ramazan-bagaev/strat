package Windows;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.WorksP.GatheringWork;
import Foundation.WorksP.HuntingWork;
import WindowElementGroups.ScrollableGroup;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.SliderElement;

import java.util.ArrayList;

public class CityWorkWindow extends ClosableWindow {

    private City city;
    private Field field;
    private int amount;
    private SliderElement sliderElement;

    public CityWorkWindow(City city, Field field, Windows parent) {
        super(new Coord(400, 400), new Coord(200, 200), parent);
        this.city = city;
        this.field = field;

        ArrayList<WindowElement> scrollableElements = new ArrayList<>();
        Label hunting = new Label(new Coord(0, 30).add(getPos()), new Coord(150, 30), "hunting", this);
        Label gathering = new Label(new Coord(0, 60).add(getPos()), new Coord(150, 30), "gathering", this);
        scrollableElements.add(hunting);
        scrollableElements.add(gathering);
        ScrollableGroup scrollableGroup = new ScrollableGroup(getPos().add(new Coord(0, 30)), getSize().x, 2, 30, scrollableElements, this){

            @Override
            public void click(Coord point){
                int index = getClickedIndex(point);
                if (index == 0){
                    CityWorkWindow cityWorkWindow = (CityWorkWindow)getParent();
                    City city = cityWorkWindow.getCity();
                    HuntingWork work = new HuntingWork(cityWorkWindow.getField());
                    city.getWorks().addWork(work);
                    People hunters = city.getPopulation().getPeopleForWork(cityWorkWindow.getAmount(), work.getId());
                    work.setPeople(hunters);
                    close();
                }
                if (index == 1){
                    CityWorkWindow cityWorkWindow = (CityWorkWindow)getParent();
                    City city = cityWorkWindow.getCity();
                    GatheringWork work = new GatheringWork(city.getResourceStore(), cityWorkWindow.getField());
                    city.getWorks().addWork(work);
                    People gatherers = city.getPopulation().getPeopleForWork(cityWorkWindow.getAmount(), work.getId());
                    work.setPeople(gatherers);
                    close();
                }
            }
        };
        addWindowElementGroup(scrollableGroup);
        int amount = city.getPopulation().amountOfNotWorking();
        sliderElement = new SliderElement(new Coord(10, 100).add(getPos()), new Coord(150, 30), true,
                0, amount, this);
        addWindowElement(sliderElement);

        MonitoredBroadcastLabel label = new MonitoredBroadcastLabel(new Coord(10, 130).add(getPos()), new Coord(100, 30), "people:",
                sliderElement, "number", this);

        addWindowElement(label);

    }

    public City getCity() {
        return city;
    }

    public Field getField() {
        return field;
    }

    @Override
    public void run(){
        amount = sliderElement.getNumber();
    }

    public int getAmount() {
        return amount;
    }
}
