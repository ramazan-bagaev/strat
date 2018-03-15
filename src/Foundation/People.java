package Foundation;

import Foundation.Elements.City;

import java.util.ArrayList;

public class People extends Broadcaster{

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

    public void setWorkId(int id){
        for (PopulationGroup populationGroup: populationGroups){
            populationGroup.workId = id;
        }
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return String.valueOf(overAllAmount());
        }
        return Broadcaster.noResult;
    }
}
