package Foundation.Person;

import Foundation.Broadcaster;
import Foundation.Elements.City;
import Foundation.Time;

import java.util.ArrayList;
import java.util.Iterator;

public class Population extends Broadcaster {

    private City city;
    private ArrayList<PopulationGroup> populationGroups;

    public Population(City city, int overallAmount){
        populationGroups = new ArrayList<>();
        this.city = city;
        generatePopulationGroups(overallAmount);
    }

    public void addPopulationGroup(PopulationGroup populationGroup){
        for (PopulationGroup group: populationGroups){
            if (group.sameAs(populationGroup)){
                int delta = populationGroup.getAll();
                group.increaseAmount(delta);
                return;
            }
        }
        populationGroups.add(populationGroup);
    }

    public void generatePopulationGroups(int amount){
        int cityId = city.getId();
        PopulationGroup populationGroupM = new PopulationGroup(amount);

        populationGroupM.wealth = PopulationGroup.MIDDLE_CLASS;
        populationGroupM.cityId = cityId;
        populationGroupM.workId = PopulationGroup.NO_WORK;
        populationGroups.add(populationGroupM);
    }

    public void increasePopulation(float factor){
        for(PopulationGroup populationGroup: populationGroups){
            int amount = (int) (factor * populationGroup.getAmount());
            populationGroup.increaseAmount(amount);
        }
    }


    public int overAllAmount(){
        int sum = 0;
        for (PopulationGroup populationGroup: populationGroups) {
            sum += populationGroup.getAmount();
        }
        return sum;
    }

    // amount of people who are not working but could
    public int amountOfNotWorking(){
        int sum = 0;
        for(PopulationGroup populationGroup: populationGroups){
            if (populationGroup.workId == PopulationGroup.NO_WORK)
                sum += populationGroup.getAmount();
        }
        return sum;
    }

    public int amountOfNotWorkingGroup(){
        int sum = 0;
        for(PopulationGroup populationGroup: populationGroups){
            if (populationGroup.workId == PopulationGroup.NO_WORK)
                sum += 1;
        }
        return sum;
    }


    public void run(){
        Time time = city.getTime();
        if (time.isNewWeek()){
            increasePopulation(0.01f);
        }
        feed();
        relax();
    }

    public void relax(){
        for(PopulationGroup populationGroup: populationGroups){
            if (populationGroup.getAmount() == 0) continue;
            for (PopulationGroup otherPopulationGroup: populationGroups){
                if (populationGroup.equals(otherPopulationGroup)) continue;
                if (populationGroup.sameAs(otherPopulationGroup)){
                    int amount = otherPopulationGroup.getAll();
                    populationGroup.increaseAmount(amount);
                }
            }
        }

        Iterator<PopulationGroup> it = populationGroups.iterator();
        while(it.hasNext()){
            if (it.next().getAmount() == 0){
                it.remove();
            }
        }
    }

    public void feed(){
        int necessaryFoodAmound = 8 * overAllAmount();
        int existingFoodAmount = city.getResourceStore().consumeFood(necessaryFoodAmound);
        int delta = existingFoodAmount - necessaryFoodAmound;
        if (delta < 0){
            delta = delta / 10;
            killPeople(Math.abs(delta));
        }
    }

    public void killPeople(int delta){
        int overAllAmount = overAllAmount();
        float multiplicator = (float) delta / (float) overAllAmount;
        for(PopulationGroup populationGroup: populationGroups){
            int amountToKill = (int)Math.ceil(multiplicator*populationGroup.getAmount());
            populationGroup.decreaseAmount(amountToKill);
        }
        relax();
    }

    public ArrayList<PopulationGroup> getPopulationGroups(){
        return populationGroups;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "overallAmount": return String.valueOf(overAllAmount());
            case "workingAmount": return String.valueOf(overAllAmount() - amountOfNotWorking());
        }
        return Broadcaster.noResult;
    }
}
