package Windows;

import Foundation.Coord;
import Foundation.Population;
import Foundation.Windows;

public class PopulationInfoWindow extends ClosableWindow{

    private Population population;

    public PopulationInfoWindow(Population population, Windows parent) {
        super(new Coord(300, 400), new Coord(500, 500), parent);

        setPopulation(population);
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;

        removeWindowElements();
        CloseButton closeButton = new CloseButton(new Coord(getPos().x + getSize().x - 15, getPos().y), new Coord(15, 15), this);
        addWindowElement(closeButton);


    }
}
