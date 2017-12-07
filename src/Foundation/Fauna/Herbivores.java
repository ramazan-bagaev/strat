package Foundation.Fauna;

import Foundation.Resource;
import Foundation.Resources.Food;
import Foundation.Resources.FoodType;

import java.util.ArrayList;

public class Herbivores extends Animal{

    public Herbivores(int amount){
        super(2, amount);
        name = "herbivores";
    }

    @Override
    public ArrayList<Resource> getResources(int amount) {
        ArrayList<Resource> resources = new ArrayList<>();
        if (this.amount < amount) amount = this.amount;
        if (amount < 0) amount = 0;
        this.amount -= amount;
        Food food = new Food(amount, new FoodType(FoodType.MEAT));
        resources.add(food);
        return resources;
    }
}
