package Foundation.Recources.EdibleProduct;


import Foundation.Recources.Product;

public class FruitProduct extends EdibleProduct {

    public FruitProduct(int amount) {
        super(amount);
    }

    @Override
    public boolean isSameAs(Product other) {
        if (other.isEdible()){
            if (((EdibleProduct)other).isFruit()) return true;
        }
        return false;
    }

    @Override
    public boolean isFruit(){
        return true;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "fruit";
        }
        return super.getValue(key);
    }
}
