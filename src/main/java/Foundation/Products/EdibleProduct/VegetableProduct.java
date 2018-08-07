package Foundation.Products.EdibleProduct;

import Foundation.Products.Product;

public class VegetableProduct extends EdibleProduct {

    public VegetableProduct(int amount) {
        super(amount);
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
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
