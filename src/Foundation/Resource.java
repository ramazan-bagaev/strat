package Foundation;

import Images.Image;
import Utils.Index;

public class Resource extends Broadcaster{



    public enum Type{
        Food, Material
    }


    protected String name;
    protected int amount;
    private Type type;


    public Resource(Type type, String name, int amount){
        this.amount = amount;
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Image getImage(Index pos, Index size, Window parent){
        return null;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return String.valueOf(amount);
            case "type":
                switch (type){
                    case Food:
                        return "food";
                    case Material:
                        return "material";
                }
            case "name":
                return name;
        }
        return Broadcaster.noResult;
    }

    public boolean sameAs(Resource resource){
        if (this.type == resource.type && this.name.equals(resource.name)) return true;
        return false;
    }

    public Resource getResource(int amount){
        return new Resource(type, name, getRealAmount(amount));
    }

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
