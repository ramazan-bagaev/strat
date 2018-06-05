package Foundation.Recources;

import Utils.Broadcaster;
import Utils.Content;
import Utils.Subscription;

public abstract class Product implements ProductType, Broadcaster{

    private int amount;
    protected Content amountContent;

    public Product(int amount){
        this.amount = amount;
        amountContent = new Content();
    }

    public int getAmount(){
        return amount;
    }

    public void increase(int amount){
        if (amount <= 0) return;
        this.amount += amount;
        amountContent.changed();
    }

    public void decrease(int amount){
        if (amount <= 0) return;
        if (this.amount < amount) amount = this.amount;
        this.amount -= amount;
        amountContent.changed();
    }

    public abstract boolean isSameAs(Product other);

    public abstract int getWeight();

    @Override
    public boolean isEdible() {
        return false;
    }

    @Override
    public boolean isMaterial() {
        return false;
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

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return String.valueOf(amount);
        }
        return Broadcaster.noResult;
    }
}
