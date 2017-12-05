package Foundation.Resources;

import Foundation.Resource;

public class Food extends Resource{

    private FoodType foodType;

    public Food(int amount, FoodType foodType){
        super(Type.Food, amount);
        this.foodType = foodType;
    }

    public FoodType getFoodType() {
        return foodType;
    }
}
