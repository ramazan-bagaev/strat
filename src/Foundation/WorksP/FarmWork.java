package Foundation.WorksP;

import Foundation.Elements.Farm;
import Foundation.Elements.Manor;
import Foundation.Resource;
import Foundation.Work;

public class FarmWork extends Work {

    private Farm farm;

    public FarmWork(Manor manor, Farm farm) {
        super(manor.getResourceStore());
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
