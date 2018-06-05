package Foundation.Recources.EdibleProduct;

import Foundation.Recources.Product;

public abstract class EdibleProduct extends Product implements EdibleType {

    public EdibleProduct(int amount){
        super(amount);
    }

    @Override
    public boolean isEdible(){
        return true;
    }

    @Override
    public int getWeight(){
        return 1;
    }

    @Override
    public boolean isFish() {
        return false;
    }

    @Override
    public boolean isVegetable() {
        return false;
    }

    @Override
    public boolean isMeat() {
        return false;
    }

    @Override
    public boolean isFruit() {
        return false;
    }

    @Override
    public boolean isWheat() {
        return false;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "type":
                return "food";
        }
        return super.getValue(key);
    }
}
