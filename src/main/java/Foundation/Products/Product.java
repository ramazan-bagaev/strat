package Foundation.Products;

import Foundation.Needs.Need;
import Utils.Broadcaster;
import Utils.Content;
import Utils.Subscription;

public abstract class Product implements ProductType, Need, Broadcaster{

    private int amount;
    protected Content amountContent;
    protected String name;

    public Product(int amount){
        this.amount = amount;
        amountContent = new Content();
        name = noResult;
    }

    @Override
    public int getAmount(){
        return amount;
    }

    @Override
    public void addAmount(int amount){
        increase(amount);
    }

    @Override
    public void decreaseAmount(int amount){
        decrease(amount);
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

    public abstract boolean isSameProductAs(Product other);

    @Override
    public boolean isSameNeedAs(Need need){
        if (!need.isProduct()) return false;
        return isSameProductAs((Product)need);
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    @Override
    public boolean isMaterial() {
        return false;
    }

    @Override
    public boolean isRawMaterial(){
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
            case "name":
                return name;
        }
        return Broadcaster.noResult;
    }

    @Override
    public boolean isProduct(){
        return true;
    }

    @Override
    public boolean isApartment(){
        return false;
    }

}
