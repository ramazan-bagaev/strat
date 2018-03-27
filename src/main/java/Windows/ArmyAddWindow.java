package Windows;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Person.Society;
import Foundation.Runnable.Army;
import Foundation.WorksP.ArmyWork;
import Foundation.WorksP.Work;
import Utils.Coord;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.SliderElement;

public class ArmyAddWindow extends ClosableWindow{

    private City city;
    private int amount;

    private SliderElement sliderElement;

    public ArmyAddWindow(City city, Frame parent) {
        super(new Coord(400, 400), new Coord(200, 200), parent);
        this.city = city;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();
        Society society = city.getSociety();
        int amount = society.getAmount();

        sliderElement = new SliderElement(new Coord(20, 20), new Coord(160, 20), true, 0, amount, this);
        addWindowElement(sliderElement);

        MonitoredBroadcastLabel label = new MonitoredBroadcastLabel(new Coord(10, 50), new Coord(160, 30), "society:",
                sliderElement, "number", this);

        addWindowElement(label);

        Button button = new Button(new Coord(20, 80), new Coord(50, 20), "add", this) {

            @Override
            public void click(Coord point) {
                ArmyAddWindow window = (ArmyAddWindow)getParent();
                City city = window.getCity();
                Field field = city.getParent();
                if (field.getArmyElement() != null) return;
                Work work = new ArmyWork();
                //Society warriors = city.getPopulation().getPeopleForWork(window.getAmount(), work.getId());
                //work.setSociety(warriors);
                Army army = new Army(field.getFieldMapPos(), null, city.getMap(), city.getTime());
                city.getMap().getGameEngine().addRunEntity(army);
                city.getArmies().addArmy(army);
                window.close();
            }
        };

        addWindowElement(button);
    }

    @Override
    public void run(){
        amount = sliderElement.getNumber();
    }

    public City getCity(){
        return city;
    }

    public int getAmount(){
        return amount;
    }
}
