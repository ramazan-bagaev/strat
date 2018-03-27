package Foundation.WorksP;

import Foundation.Elements.Sawmill;
import Foundation.Elements.Village;
import Foundation.Resource;

public class SawmillWork extends Work {


    private Sawmill sawmill;

    public SawmillWork(Village village, Sawmill sawmill) {
        super(village.getResourceStore());
        this.sawmill = sawmill;
    }

    @Override
    public void doJob() {
        int amount = sawmill.getPeople().getAmount();
        int coef = sawmill.getEfficiency();
        Resource resource = new Resource(Resource.Type.Material, "wood", amount * coef);
        resourceStore.addResource(resource);
    }
}
