package Foundation;

import java.util.ArrayList;

public class Production extends Broadcaster{

    private ArrayList<ResourceConvertor> resourceConvertors;
    private ResourceStore resourceStore;

    public Production(ResourceStore resourceStore){
        resourceConvertors = new ArrayList<>();
        setResourceStore(resourceStore);
    }

    public ArrayList<ResourceConvertor> getResourceConvertors() {
        return resourceConvertors;
    }

    public void setResourceConvertors(ArrayList<ResourceConvertor> resourceConvertors) {
        this.resourceConvertors = resourceConvertors;
    }

    public void addResourceConvertor(ResourceConvertor resourceConvertor){
        resourceConvertors.add(resourceConvertor);
    }

    public void run(){
        for (ResourceConvertor resourceConvertor: resourceConvertors) {
            Resource resource = resourceConvertor.convert();
            resourceStore.addResource(resource);
        }
    }

    public ResourceStore getResourceStore() {
        return resourceStore;
    }

    public void setResourceStore(ResourceStore resourceStore) {
        this.resourceStore = resourceStore;
    }

    public int workerAmount(){
        int sum = 0;
        for (ResourceConvertor resourceConvertor: resourceConvertors){
            int peopleNum = resourceConvertor.getPeople().getAmount();
            sum += peopleNum;
        }
        return sum;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "workerAmount":
                return String.valueOf(workerAmount());
        }
        return Broadcaster.noResult;
    }
}

