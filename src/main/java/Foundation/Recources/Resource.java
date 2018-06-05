package Foundation.Recources;

import Foundation.Window;
import Images.Image;
import Utils.Broadcaster;
import Utils.Content;
import Utils.Index;
import Utils.Subscription;

public class Resource implements Broadcaster {

    public enum Type{
        Food, Material
    }

    protected Content amountContent;


    protected String name;
    protected int amount;
    private Type type;

    public Resource(Type type, String name, int amount){
        this.amount = amount;
        this.type = type;
        this.name = name;
        amountContent = new Content();
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

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "amount":
                amountContent.subscribe(subscription);
                break;
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "amount":
                amountContent.unsubscribe(subscription);
                break;
        }
    }

    public boolean sameAs(Resource resource){
        if (this.type == resource.type && this.name.equals(resource.name)) return true;
        return false;
    }

    public Resource getResource(int amount){
        int realAmount = getRealAmount(amount);
        this.amount -= realAmount;
        amountContent.changed();
        return new Resource(type, name, realAmount);
    }

    public int getRealAmount(int amount){
        if (amount > this.amount) amount = this.amount;
        if (amount < 0) amount = 0;
        return amount;
    }

    public void increaseAmount(int delta){
        if (delta > 0){
            amount += delta;
            amountContent.changed();
        }
    }

    public int consume(int amount){
        amount = getRealAmount(amount);
        this.amount -= amount;
        amountContent.changed();
        return amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        amountContent.changed();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
