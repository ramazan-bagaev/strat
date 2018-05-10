package Foundation.WorksP;

import Foundation.Elements.Mine;
import Foundation.Elements.Village;
import Foundation.Person.People;
import Foundation.Recources.Resource;

public class MineWork extends ResourceExtractingWork {

    private Mine mine;

    public MineWork(People people, Village village, Mine mine) {
        super(village.getResourceStore(), people);
        this.mine = mine;
    }

    @Override
    public Resource getResource(int amount) {
        return new Resource(Resource.Type.Material, "stone", amount);
    }

    @Override
    public int getEfficiency() {
        return mine.getEfficiency();
    }
}
