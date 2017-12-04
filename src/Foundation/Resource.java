package Foundation;

import Images.FoodResourceImage;
import Images.RockResourceImage;
import Images.TreeResourceImage;

public class Resource {

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public enum Type{
        Tree, Stone, HumanHour, Food, Fertility
    }

    private int amount;
    private Type type;

    public Resource(Type type, int amount){
        setAmount(amount);
        setType(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static Image getImage(Coord pos, Coord size, Window parent, Type type){
        switch (type){

            case Tree:
                return new TreeResourceImage(pos, size, parent);
            case Stone:
                return new RockResourceImage(pos, size, parent);
            case HumanHour:
                return null;
            case Food:
                return new FoodResourceImage(pos, size, parent);
            case Fertility:
                return null;
        }
        return null;
    }

}
