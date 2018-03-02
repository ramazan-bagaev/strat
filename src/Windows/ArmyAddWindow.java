package Windows;

import Foundation.*;
import Foundation.WorksP.ArmyWork;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.SliderElement;

public class ArmyAddWindow extends ClosableWindow{

    private City city;
    private int amount;

    private SliderElement sliderElement;

    public ArmyAddWindow(City city, Windows parent) {
        super(new Coord(400, 400), new Coord(200, 200), parent);
        this.city = city;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();
        Population population = city.getPopulation();
        int amount = population.amountOfNotWorking();

        sliderElement = new SliderElement(new Coord(20, 20).add(getPos()), new Coord(160, 20), true, 0, amount, this);
        addWindowElement(sliderElement);

        MonitoredBroadcastLabel label = new MonitoredBroadcastLabel(new Coord(10, 50).add(getPos()), new Coord(160, 30), "people:",
                sliderElement, "number", this);

        addWindowElement(label);

        Button button = new Button(new Coord(20, 80).add(getPos()), new Coord(50, 20), this, "add") {

            @Override
            public void click(Coord point) {
                ArmyAddWindow window = (ArmyAddWindow)getParent();
                City city = window.getCity();
                Field field = city.getParent();
                if (field.getArmy() != null) return;
                ArmyWork work = new ArmyWork(city.getResourceStore());
                city.getWorks().addWork(work);
                People warriors = city.getPopulation().getPeopleForWork(window.getAmount(), work.getId());
                work.setPeople(warriors);
                Army army = new Army(warriors, city.getTime(), field);
                city.getArmies().addArmy(army);
                field.setArmy(army);
                field.setChanged(true);
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
