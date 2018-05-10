package Foundation.WorksP;

import Foundation.Elements.Sawmill;
import Foundation.Elements.Village;
import Foundation.Person.People;
import Foundation.Recources.Resource;

public class SawmillWork extends ResourceExtractingWork {


    private Sawmill sawmill;

    public SawmillWork(People people, Village village, Sawmill sawmill) {
        super(village.getResourceStore(), people);
        this.sawmill = sawmill;
    }

    @Override
    public Resource getResource(int amount) {
        return new Resource(Resource.Type.Material, "wood", amount);
    }

    @Override
    public int getEfficiency() {
        return sawmill.getEfficiency();
    }
}
