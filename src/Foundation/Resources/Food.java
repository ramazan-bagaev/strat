package Foundation.Resources;

import Foundation.*;
import Images.FoodResourceImage;

public class Food extends Resource{

    private FoodType foodType;
    private String name;


    public Food(int amount, FoodType foodType, String name){
        super(Type.Food, amount);
        this.foodType = foodType;
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    @Override
    public Image getImage(Coord pos, Coord size, Window parent) {
        return new FoodResourceImage(pos, size, parent);
    }


    @Override
    public String getValue(String key){
        switch (key){
            case "foodType":
                switch (foodType){
                    case MEAT:
                        return "meat";
                    case PLANT:
                        return "plant";
                }
        }
        return super.getValue(key);
    }

    @Override
    public boolean sameAs(Resource resource) {
        if (resource == null) return false;
        if (resource.getClass() != Food.class) return false;
        Food food = (Food)resource;
        return (food.getFoodType() == this.foodType && food.getName() == this.name);
    }

    public String getName() {
        return name;
    }

    public enum FoodType{
        MEAT, PLANT
    }

    public Resource getResource(int amount){
        if (amount > this.amount) amount = this.amount;
        if (amount < 0) amount = 0;
        Food res = new Food(amount, foodType, name);
        return res;
    }
}
