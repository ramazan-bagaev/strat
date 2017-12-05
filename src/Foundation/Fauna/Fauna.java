package Foundation.Fauna;

import Foundation.Broadcaster;
import Foundation.Flora.Flora;
import Foundation.ResourceCause;
import Foundation.ResourceCauses.FoodCause;

import java.util.ArrayList;

public class Fauna extends Broadcaster{

    private ArrayList<ResourceCause> resourceCauses;

    private Flora flora;
    private int herbivores;
    private int predators;

    public Fauna(Flora flora){
        this.flora = flora;
        resourceCauses = new ArrayList<>();
        resourceCauses.add(new FoodCause());
        run();
    }

    public void run(){
        herbivores = flora.getWildPlantsAmount() + flora.getTreeAmount();
        predators = herbivores / 10;
    }


    @Override
    public String getValue(String key) {
        switch (key){
            case "herbivores":
                return String.valueOf(herbivores);
            case "predators":
                return String.valueOf(predators);
        }

        return Broadcaster.noResult;
    }

    public ArrayList<ResourceCause> getResourceCauses() {
        return resourceCauses;
    }

    public void setResourceCauses(ArrayList<ResourceCause> resourceCauses) {
        this.resourceCauses = resourceCauses;
    }
}
