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
        HumanHour, Food, Fertility, Fur
    }

    protected int amount;
    private Type type;

    public Resource(Resource resource){
        this.type = resource.getType();
        this.amount = 0;
    }

    public Resource(Type type, int amount){
        this.amount = amount;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract Image getImage(Coord pos, Coord size, Window parent);

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
                    case Fur:
                        return "fur";
                }
        }
        return Broadcaster.noResult;
    }

    public abstract boolean sameAs(Resource resource);

    public abstract Resource getResource(int amount);

    public int getRealAmount(int amount){
        if (amount > this.amount) amount = this.amount;
        if (amount < 0) amount = 0;
        return amount;
    }

    public void increaseAmount(int delta){
        if (delta > 0) amount += delta;
    }

    public int consume(int amount){
        amount = getRealAmount(amount);
        this.amount -= amount;
        return amount;
    }
}
