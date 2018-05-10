package Foundation.WorksP;

import Foundation.Elements.Farm;
import Foundation.Elements.Village;
import Foundation.Person.People;
import Foundation.Recources.Resource;

public class FarmWork extends ResourceExtractingWork {

    private Farm farm;

    public FarmWork(People people, Village village, Farm farm) {
        super(village.getResourceStore(), people);
        this.farm = farm;
    }

    @Override
    public Resource getResource(int amount) {
        return new Resource(Resource.Type.Food, "wheat", amount);
    }

    @Override
    public int getEfficiency() {
        return farm.getEfficiency();
    }
}
