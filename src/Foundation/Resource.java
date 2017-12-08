package Foundation;

import Foundation.Resources.Food;
import Images.FoodResourceImage;
import Images.RockResourceImage;
import Images.TreeResourceImage;

public abstract class Resource extends Broadcaster{

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

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return String.valueOf(amount);
            case "type":
                switch (type){
                    case HumanHour:
                        return "humanHour";
                    case Food:
                        return "food";
                    case Fertility:
                        return "fertility";
                }
        }
        return Broadcaster.noResult;
    }
}
