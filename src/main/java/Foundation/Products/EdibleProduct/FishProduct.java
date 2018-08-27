package Foundation.Products.EdibleProduct;

import Foundation.Products.Product;

public class FishProduct extends EdibleProduct {

    public FishProduct(int amount){
        super(amount);
        this.name = "fish";
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
            if (((EdibleProduct)other).isFish()) return true;
        }
        return false;
    }

    @Override
    public boolean isFish(){
        return true;
    }
}
