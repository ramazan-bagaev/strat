package Foundation.WorksP;

import Foundation.Field;
import Foundation.Flora.Flora;
import Foundation.Flora.Plant;
import Foundation.Resource;
import Foundation.ResourceStore;
import Foundation.Work;

import java.util.ArrayList;

public class GatheringWork extends Work {

    private Flora flora;

    public GatheringWork(ResourceStore resourceStore, Field field) {
        super(resourceStore);

        flora = field.getEcosystem().getFlora();

    }

    @Override
    public void doJob() {
        int amount = 10 * people.getAmount();
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<Plant> plants = flora.getPlants();
        for(Plant plant: plants) {
            if (plant.isEdible()) resources.addAll(plant.getResources(amount));
        }
        for(Resource resource: resources){
            store.addResource(resource);
        }
    }
}
