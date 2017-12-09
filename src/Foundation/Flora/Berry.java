package Foundation.Flora;

import Foundation.Resource;
import Foundation.Resources.Food;

import java.util.ArrayList;

public class Berry extends Plant {

    public Berry(int standartAmount) {
        super(standartAmount);
        name = "berry";
    }

    @Override
    public ArrayList<Resource> getResources(int amount) {
        ArrayList<Resource> resources = new ArrayList<>();
        if (amount > this.amount) amount = this.amount;
        if (amount < 0) amount = 0;
        this.amount -= amount;
        Food food = new Food(amount, Food.FoodType.PLANT, "berry");
        resources.add(food);
        return resources;
    }

    @Override
    public boolean isEdible(){
        return true;
    }
}
