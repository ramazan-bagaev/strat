package Foundation.WorksP;

import Foundation.Person.People;
import Foundation.Recources.Resource;
import Foundation.Recources.ResourceStore;

public abstract class ResourceExtractingWork extends Work {

    protected ResourceStore resourceStore;

    public ResourceExtractingWork(ResourceStore resourceStore, People people) {
        super(people);
        this.resourceStore = resourceStore;
    }

    public abstract Resource getResource(int amount);

    public abstract int getEfficiency();

    @Override
    public void doJob(){
        int amount = people.getAmount();
        int coef = getEfficiency();
        Resource resource = getResource(amount * coef);
        resourceStore.addResource(resource);
    }
}
