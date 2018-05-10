package Foundation.WorksP;

import Foundation.Elements.Trawler;
import Foundation.Elements.Village;
import Foundation.Person.People;
import Foundation.Recources.Resource;

public class TrawlerWork extends ResourceExtractingWork {

    private Trawler trawler;

    public TrawlerWork(People people, Village village, Trawler trawler) {
        super(village.getResourceStore(), people);
        this.trawler = trawler;
    }

    @Override
    public Resource getResource(int amount) {
        return new Resource(Resource.Type.Food, "fish", amount);
    }

    @Override
    public int getEfficiency() {
        return trawler.getEfficiency();
    }
}
