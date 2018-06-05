package Foundation.Recources.EdibleProduct;

import Foundation.Recources.Product;

public class FishProduct extends EdibleProduct {

    public FishProduct(int amount){
        super(amount);
    }

    @Override
    public boolean isSameAs(Product other) {
        if (other.isEdible()){
            if (((EdibleProduct)other).isFish()) return true;
        }
        return false;
    }

    @Override
    public boolean isFish(){
        return true;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "fish";
        }
        return super.getValue(key);
    }
}
