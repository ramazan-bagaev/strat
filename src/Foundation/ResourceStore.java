package Foundation;

import java.util.ArrayList;
import java.util.Iterator;

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
            if (bank.addResource(resource)) return;
        }
        ResourceBank newResourceBank = resource.getResourceBank();
        newResourceBank.addResource(resource);
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
        int sum = 0;
        for(ResourceBank resourceBank: resourceBanks){
            if (resourceBank.getType() == type) sum += resourceBank.consume(amount);
            if (sum == amount) return sum;
        }
        return sum;
    }

    public void run(){
        Iterator<ResourceBank> iter = resourceBanks.iterator();
        while(iter.hasNext()){
        ResourceBank resourceBank = iter.next();
            if (resourceBank.getCapacity() == 0) iter.remove();
        }
    }
}
