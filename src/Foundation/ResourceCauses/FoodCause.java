package Foundation.ResourceCauses;

import Foundation.Resources.FoodType;
import Foundation.Resource;
import Foundation.ResourceCause;

public class FoodCause extends ResourceCause {

    private FoodType foodType;

    public FoodCause(FoodType foodType) {
        super(Resource.Type.Food);
        this.foodType = foodType;
    }

    public FoodCause(FoodType foodType, int capacity) {
        super(Resource.Type.Food, capacity);
        this.foodType = foodType;
    }

    public FoodCause(FoodType type, int capacity, int maxCapacity, int renewAmount) {
        super(Resource.Type.Food, capacity, maxCapacity, renewAmount);
    }

    @Override
    public void renew(){

    }

    @Override
    public int consume(int amount) {
        return 0;
    }
}
