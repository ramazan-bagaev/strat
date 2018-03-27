package Foundation.WorksP;

import Foundation.Elements.Mine;
import Foundation.Elements.Village;
import Foundation.Resource;

public class MineWork extends Work {

    private Mine mine;

    public MineWork(Village village, Mine mine) {
        super(village.getResourceStore());
        this.mine = mine;
    }

    @Override
    public void doJob() {
        int amount = mine.getPeople().getAmount();
        int coef = mine.getEfficiency();
        Resource resource = new Resource(Resource.Type.Material, "stone", amount * coef);
        resourceStore.addResource(resource);
    }
}
