package Foundation.Recources.EdibleProduct;

import Foundation.Recources.Product;

public class VegetableProduct extends EdibleProduct {

    public VegetableProduct(int amount) {
        super(amount);
    }

    @Override
    public boolean isSameAs(Product other) {
        if (other.isEdible()){
            if (((EdibleProduct)other).isVegetable()) return true;
        }
        return false;
    }

    @Override
    public boolean isVegetable(){
        return true;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "vegetable";
        }
        return super.getValue(key);
    }
}
