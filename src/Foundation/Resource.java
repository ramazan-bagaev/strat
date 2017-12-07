package Foundation;

import Foundation.Resources.Food;
import Images.FoodResourceImage;
import Images.RockResourceImage;
import Images.TreeResourceImage;

public abstract class Resource {

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public enum Type{
        HumanHour, Food, Fertility
    }

    protected int amount;
    private Type type;

    public Resource(Type type, int amount){
        this.amount = amount;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract Image getImage(Coord pos, Coord size, Window parent);

    public abstract ResourceBank getResourceBank();

}
