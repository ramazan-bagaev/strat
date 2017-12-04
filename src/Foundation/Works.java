package Foundation;

import java.util.ArrayList;

public class Works {

    private City city;
    private Population population;
    private ArrayList<Work> works;
    private int currentId;

    public Works(City city){
        works = new ArrayList<>();
        currentId = 0;
        this.city = city;
        this.population = city.getPopulation();
    }

    public void addWork(ResourceConvertor resourceConvertor, int peopleNumber){
        People people = population.getPeopleForWork(peopleNumber, currentId);
        resourceConvertor.setPeople(people);
        Work work = new Work(people, resourceConvertor, currentId);
        currentId++;
        works.add(work);
        city.getProduction().addResourceConvertor(resourceConvertor);
    }
}
