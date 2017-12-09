package Foundation.Fauna;

import Foundation.Resource;
import Foundation.Resources.Food;
import Foundation.Resources.Fur;

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
        Food food = new Food(amount, Food.FoodType.MEAT, "herbivorous meat");
        Fur fur = new Fur(amount, "herbivorous");
        resources.add(food);
        resources.add(fur);
        return resources;
    }

    @Override
    public boolean isHuntable(){
        return true;
    }
}
