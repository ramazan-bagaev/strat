package Foundation.Products.EdibleProduct;

import Foundation.Products.Product;

public class WheatProduct extends EdibleProduct {

    public WheatProduct(int amount) {
        super(amount);
        this.name = "wheat";
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
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
}
