package Foundation;

import java.util.ArrayList;

public class People {

    private ArrayList<PopulationGroup> populationGroups;
    private City city;

    public People(City city){
        setCity(city);
    }


    public ArrayList<PopulationGroup> getPopulationGroups() {
        return populationGroups;
    }

    public void setPopulationGroups(ArrayList<PopulationGroup> populationGroups) {
        this.populationGroups = populationGroups;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int overAllAmount(){
        int sum = 0;
        for(PopulationGroup populationGroup: populationGroups){
            sum += populationGroup.getAmount();
        }
        return sum;
    }

    public int getAmount() {
        return overAllAmount();
    }
}
