package Foundation.WorksP;

import Foundation.Elements.Farm;
import Foundation.Elements.Village;
import Foundation.Resource;

public class FarmWork extends Work {

    private Farm farm;

    public FarmWork(Village village, Farm farm) {
        super(village.getResourceStore());
        this.farm = farm;
    }

    @Override
    public void doJob() {
        int amount = farm.getPeople().getAmount();
        int coef = farm.getEfficiency();
        Resource resource = new Resource(Resource.Type.Food, "wheat", amount * coef);
        resourceStore.addResource(resource);
    }
}
