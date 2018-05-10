package Foundation.Recources;

import Foundation.Recources.Resource;

import java.util.ArrayList;
import java.util.Iterator;

public class ResourceStore {

    private ArrayList<Resource> resources;

    public ResourceStore(){
        resources = new ArrayList<>();
    }

    public void addResource(Resource resource){
        if (resource.getAmount() <= 0) return;
        for (Resource storedResource: resources){
            if (storedResource.sameAs(resource)){
                storedResource.increaseAmount(resource.getAmount());
                return;
            }
        }
        resources.add(resource);
    }

    public void run(){
        Iterator<Resource> iter = resources.iterator();
        while(iter.hasNext()){
        Resource resource = iter.next();
            if (resource.getAmount() == 0) iter.remove();
        }
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public int consumeFood(int amount){
        int consumed = 0;
        for (Resource resource: resources){
            if (resource.getType() == Resource.Type.Food){
                consumed += resource.consume(amount);
                if (consumed == amount) return consumed;
            }
        }
        return consumed;
    }
}