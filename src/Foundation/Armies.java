package Foundation;

import Foundation.Elements.Army;
import Foundation.Elements.City;

import java.util.ArrayList;

public class Armies {

    private City city;
    private ArrayList<Army> armies;

    public Armies(City city){
        this.city = city;
        armies = new ArrayList<>();
    }

    public void addArmy(Army army){
        armies.add(army);
    }
}
