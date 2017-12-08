package Foundation.Resources;

import Foundation.*;
import Foundation.ResourceBanks.FoodBank;
import Images.FoodResourceImage;

public class Food extends Resource{

    private FoodType foodType;

    public Food(int amount, FoodType foodType){
        super(Type.Food, amount);
        this.foodType = foodType;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    @Override
    public Image getImage(Coord pos, Coord size, Window parent) {
        return new FoodResourceImage(pos, size, parent);
    }

    @Override
    public ResourceBank getResourceBank(){
        FoodBank foodBank = new FoodBank(foodType);
        return foodBank;
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
