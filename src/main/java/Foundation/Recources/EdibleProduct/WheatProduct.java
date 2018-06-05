package Foundation.Recources.EdibleProduct;

import Foundation.Recources.Product;

public class WheatProduct extends EdibleProduct {

    public WheatProduct(int amount) {
        super(amount);
    }

    @Override
    public boolean isSameAs(Product other) {
        if (other.isEdible()){
            if (((EdibleProduct)other).isWheat()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isWheat(){
        return true;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "wheat";
        }
        return super.getValue(key);
    }
}
