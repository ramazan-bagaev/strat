package Foundation;

import java.util.ArrayList;

public class ResourceStore {

    private ArrayList<ResourceBank> resourceBanks;

    public ResourceStore(){
        resourceBanks = new ArrayList<>();
    }

    public void addResourceBank(ResourceBank resourceBank){
        for (ResourceBank bank: resourceBanks ){
            if (bank.getType() == resourceBank.getType()){
                Resource resource = resourceBank.getAll();
                bank.addResource(resource);
                return;
            }
        }
        resourceBanks.add(resourceBank);
    }

    public void addResource(Resource resource){
        for (ResourceBank bank: resourceBanks){
            if (bank.getType() == resource.getType()){
                bank.addResource(resource);
                return;
            }
        }
        ResourceBank newResourceBank = new ResourceBank(resource.getType(), resource.getAmount());
        resourceBanks.add(newResourceBank);
    }

    public ArrayList<ResourceBank> getResourceBanks() {
        return resourceBanks;
    }

    public void setResourceBanks(ArrayList<ResourceBank> resourceBanks) {
        this.resourceBanks = resourceBanks;
    }

    public ResourceBank getResourceBank(Resource.Type type){
        for(ResourceBank resourceBank: resourceBanks){
            if (resourceBank.getType() == type) return resourceBank;
        }
        return null;
    }

    public int consumeResource(int amount, Resource.Type type){
        for(ResourceBank resourceBank: resourceBanks){
            if (resourceBank.getType() == type) return resourceBank.consume(amount);
        }
        return 0;
    }
}
