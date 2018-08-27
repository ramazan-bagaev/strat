package Foundation.Products.MaterialProduct;

import Foundation.Products.Product;

public class StoneMaterial extends MaterialProduct {

    public StoneMaterial(int amount) {
        super(amount);
        this.name = "stone";
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
            if (((MaterialProduct)other).isStone()) return true;
        }
        return false;
    }

    @Override
    public boolean isStone(){
        return true;
    }
}
