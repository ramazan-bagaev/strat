package Foundation.ResourceBanks;

import Foundation.Resource;
import Foundation.ResourceBank;
import Foundation.Resources.Food;
import Foundation.Resources.FoodType;

public class FoodBank extends ResourceBank{

    private FoodType foodType;

    public FoodBank(FoodType foodType) {
        super(Resource.Type.Food);
        this.foodType = foodType;

    }

    public FoodType getFoodType() {
        return foodType;
    }

    @Override
    public boolean canStore(Resource resource) {
        if (resource.getType() != Resource.Type.Food) return false;
        Food food = (Food)resource;
        return (food.getFoodType().sameAs(foodType));
    }

    @Override
    public Resource getResource(int amount) {
        if (amount > capacity) amount = capacity;
        if (amount < 0) amount = 0;
        capacity = capacity - amount;
        Food food = new Food(amount, foodType);
        return food;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "foodType":
                switch (foodType.group){
                    case FoodType.MEAT:
                        return "meat";
                    case FoodType.PLANTS:
                        return "plants";
                }
        }
        return super.getValue(key);
    }
}
