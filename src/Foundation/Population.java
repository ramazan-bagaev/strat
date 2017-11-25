package Foundation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Population extends Broadcaster{

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
            populationGroup.workId = PopulationGroup.NO_WORK;
            populationGroups.add(populationGroup);

            populationGroup = new PopulationGroup(localAmount);
            populationGroup.sex = PopulationGroup.FEMALE;
            populationGroup.age = i;
            populationGroup.wealth = PopulationGroup.MIDDLE_CLASS;
            populationGroup.cityId = cityId;
            populationGroup.workId = PopulationGroup.NO_WORK;
            populationGroups.add(populationGroup);
        }

        Random random = city.getParent().getRandom();
        for (int i = 0; i < royal; i++){
            PopulationGroup populationGroup = new PopulationGroup(1);
            populationGroup.age = random.nextInt(60);
            populationGroup.sex = random.nextBoolean();
            populationGroup.wealth = random.nextInt(3);
            populationGroup.cityId = cityId;
            populationGroup.workId = PopulationGroup.NO_WORK;
            populationGroups.add(populationGroup);
        }
    }

    public void addNewGeneration(){
        int amountOfChildren;
        int maleAmount = 0;
        int femaleAmount = 0;
        for (PopulationGroup populationGroup: populationGroups){
            if (populationGroup.age < 18 || populationGroup.age > 60) continue;
            if (populationGroup.sex == PopulationGroup.MALE) maleAmount += populationGroup.getAmount();
            if (populationGroup.sex == PopulationGroup.FEMALE) femaleAmount += populationGroup.getAmount();
        }
        if (femaleAmount > maleAmount) amountOfChildren = maleAmount / 20;
        else amountOfChildren = femaleAmount / 20;

        PopulationGroup newPopulationGroup = new PopulationGroup(amountOfChildren/2);
        newPopulationGroup.age = 0;
        newPopulationGroup.sex = PopulationGroup.FEMALE;
        newPopulationGroup.wealth = PopulationGroup.MIDDLE_CLASS;
        newPopulationGroup.cityId = city.getId();
        newPopulationGroup.workId = PopulationGroup.NO_WORK;
        addPopulationGroup(newPopulationGroup);

        newPopulationGroup = new PopulationGroup(amountOfChildren / 2);
        newPopulationGroup.age = 0;
        newPopulationGroup.sex = PopulationGroup.MALE;
        newPopulationGroup.wealth = PopulationGroup.MIDDLE_CLASS;
        newPopulationGroup.cityId = city.getId();
        newPopulationGroup.workId = PopulationGroup.NO_WORK;
        addPopulationGroup(newPopulationGroup);
    }

    public void olderPopulationGroups(){
        for (PopulationGroup populationGroup: populationGroups){
            populationGroup.older();
        }
    }

    public void killOldPopulationGroup(){
        Iterator<PopulationGroup> it = populationGroups.iterator();
        while (it.hasNext()){
            PopulationGroup populationGroup = it.next();
            if (populationGroup.age > 100){
                populationGroup.setAmount(0);
                it.remove();
            }
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
            if (populationGroup.age > PopulationGroup.MAX_WORKING_AGE || populationGroup.age < PopulationGroup.MIN_WORKING_AGE) continue;
            if (populationGroup.workId == PopulationGroup.NO_WORK)
                sum += populationGroup.getAmount();
        }
        return sum;
    }

    public int amountOfNotWorkingGroup(){
        int sum = 0;
        for(PopulationGroup populationGroup: populationGroups){
            if (populationGroup.age > PopulationGroup.MAX_WORKING_AGE || populationGroup.age < PopulationGroup.MIN_WORKING_AGE) continue;
            if (populationGroup.workId == PopulationGroup.NO_WORK)
                sum += 1;
        }
        return sum;
    }

    public int menAmount(){
        int sum = 0;
        for(PopulationGroup populationGroup: populationGroups){
            if (populationGroup.sex == PopulationGroup.MALE) sum += populationGroup.getAmount();
        }
        return sum;
    }

    public People getPeopleForWork(int amount, int workId){
        int accessablePeople = amountOfNotWorking();
        if (amount > accessablePeople) amount = accessablePeople;
        if (amount < 0) return null;
        int accessableGroup = amountOfNotWorkingGroup();
        float multiplicator = (float) accessableGroup / (float)accessablePeople;
        ArrayList<PopulationGroup> newPopulationGroups = new ArrayList<>();
        Iterator<PopulationGroup> it = populationGroups.iterator();
        while (it.hasNext()){
            PopulationGroup populationGroup = it.next();
            if (populationGroup.age > PopulationGroup.MAX_WORKING_AGE && populationGroup.age < PopulationGroup.MIN_WORKING_AGE) continue;
            if (populationGroup.workId != PopulationGroup.NO_WORK) continue;
            int localAmount = (int) Math.ceil(multiplicator * populationGroup.getAmount());
            int realLocalAmount = populationGroup.decreaseAmount(localAmount);
            amount -= realLocalAmount;
            if (amount < 0) {
                populationGroup.increaseAmount(Math.abs(amount));
                realLocalAmount += Math.abs(amount);
            }
            PopulationGroup newPopulationGroup = new PopulationGroup(realLocalAmount);
            newPopulationGroup.workId = workId;
            newPopulationGroups.add(newPopulationGroup);
            if (amount <= 0) break;
        }
        for (PopulationGroup populationGroup: newPopulationGroups){
            addPopulationGroup(populationGroup);
        }
        relax();
        People people = new People(city);
        people.setPopulationGroups(newPopulationGroups);
        return people;
    }

    public void run(){
        olderPopulationGroups();
        killOldPopulationGroup();
        addNewGeneration();
        feed();
    }

    public void relax(){
        Iterator<PopulationGroup> it = populationGroups.iterator();
        while(it.hasNext()){
            if (it.next().getAmount() == 0){
                it.remove();
            }
        }
    }

    public void feed(){
        int necessaryFoodAmound = overAllAmount();
        int existingFoodAmount = city.getResourceStore().consumeResource(necessaryFoodAmound, Resource.Type.Food);
        int delta = existingFoodAmount - necessaryFoodAmound;
        if (delta < 0){
            delta = delta / 2;
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

    @Override
    public String getValue(String key) {
        switch (key){
            case "overallAmount": return String.valueOf(overAllAmount());
            case "workingAmount": return String.valueOf(overAllAmount() - amountOfNotWorking());
            case "manAmount": return String.valueOf(menAmount());
        }
        return Broadcaster.noResult;
    }
}
