package Foundation.WorksP;

import Foundation.*;
import Foundation.Fauna.Animal;
import Foundation.Fauna.Fauna;

import java.util.ArrayList;

public class HuntingWork extends Work {

    private Fauna fauna;

    public HuntingWork(ResourceStore resourceStore, Field field) {
        super(resourceStore);
        fauna = field.getEcosystem().getFauna();
    }

    @Override
    public void doJob() {
        int amount = 10 * people.getAmount();
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<Animal> animals = fauna.getAnimals();
        for(Animal animal: animals){
            if (animal.isHuntable()) resources.addAll(animal.getResources(amount));
        }
        for(Resource resource: resources){
            store.addResource(resource);
        }

    }
}
