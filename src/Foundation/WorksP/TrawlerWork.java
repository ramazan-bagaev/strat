package Foundation.WorksP;

import Foundation.Elements.Trawler;
import Foundation.Elements.Village;
import Foundation.Resource;
import Foundation.Work;

public class TrawlerWork extends Work {

    private Trawler trawler;

    public TrawlerWork(Village village, Trawler trawler) {
        super(village.getResourceStore());
        this.trawler = trawler;
    }

    @Override
    public void doJob() {
        int amount = trawler.getPeople().getAmount();
        int coef = trawler.getEfficiency();
        Resource resource = new Resource(Resource.Type.Food, "fish", amount * coef);
        resourceStore.addResource(resource);
    }
}
