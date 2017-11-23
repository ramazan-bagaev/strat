package Foundation;

import java.util.ArrayList;
import java.util.Random;

public class Population {

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
        int localAmount = amount / 120;
        int royal = amount % 120;
        int cityId = city.getId();
        for(int i = 0; i < 60; i++){
            PopulationGroup populationGroup = new PopulationGroup(localAmount);
            populationGroup.sex = PopulationGroup.MALE;
            populationGroup.age = i;
            populationGroup.wealth = PopulationGroup.MIDDLE_CLASS;
            populationGroup.cityId = cityId;
            populationGroups.add(populationGroup);

            populationGroup = new PopulationGroup(localAmount);
            populationGroup.sex = PopulationGroup.FEMALE;
            populationGroup.age = i;
            populationGroup.wealth = PopulationGroup.MIDDLE_CLASS;
            populationGroup.cityId = cityId;
            populationGroups.add(populationGroup);
        }

        Random random = city.getParent().getRandom();
        for (int i = 0; i < royal; i++){
            PopulationGroup populationGroup = new PopulationGroup(1);
            populationGroup.age = random.nextInt(60);
            populationGroup.sex = random.nextBoolean();
            populationGroup.wealth = random.nextInt(3);
            populationGroup.cityId = cityId;
            populationGroups.add(populationGroup);
        }
    }

    public void addNewGeneration(){
        int amountOfChildren = 0;
        int maleAmount = 0;
        int femaleAmount = 0;
        for (PopulationGroup populationGroup: populationGroups){
            if (populationGroup.age < 18 || populationGroup.age > 60) continue;
            if (populationGroup.sex == PopulationGroup.MALE) maleAmount += populationGroup.getAmount();
            if (populationGroup.sex == PopulationGroup.FEMALE) femaleAmount += populationGroup.getAmount();
        }
        if (femaleAmount > maleAmount) amountOfChildren = maleAmount / 10;
        else amountOfChildren = femaleAmount / 10;

        PopulationGroup newPopulationGroup = new PopulationGroup(amountOfChildren/2);
        newPopulationGroup.age = 0;
        newPopulationGroup.sex = PopulationGroup.MALE;
        newPopulationGroup.wealth = PopulationGroup.MIDDLE_CLASS;
        newPopulationGroup.cityId = city.getId();
        addPopulationGroup(newPopulationGroup);

        newPopulationGroup = new PopulationGroup();
        newPopulationGroup.age = 0;
        newPopulationGroup.sex = PopulationGroup.FEMALE;
        newPopulationGroup.wealth = PopulationGroup.MIDDLE_CLASS;
        newPopulationGroup.cityId = city.getId();
        addPopulationGroup(newPopulationGroup);
    }

    public void olderPopulationGroups(){
        for (PopulationGroup populationGroup: populationGroups){
            populationGroup.older();
        }
    }

    public void killOldPopulationGroup(){
        for (PopulationGroup populationGroup: populationGroups){
            if (populationGroup.age > 100) populationGroups.remove(populationGroup);
        }
    }

    public int overAllAmount(){
        int sum = 0;
        for (PopulationGroup populationGroup: populationGroups) {
            sum += populationGroup.getAmount();
        }
        return sum;
    }

    public int request(boolean sex, int age, int wealth, int cityId){
        int sum = 0;
        for (PopulationGroup populationGroup: populationGroups){
            if (populationGroup.age != age) continue;
            if (populationGroup.cityId != cityId) continue;
            if (populationGroup.wealth != wealth) continue;
            if (populationGroup.sex != sex) continue;
            sum += populationGroup.getAmount();
        }
        return sum;
    }

    public void run(){
        olderPopulationGroups();
        killOldPopulationGroup();
        addNewGeneration();
    }

}
