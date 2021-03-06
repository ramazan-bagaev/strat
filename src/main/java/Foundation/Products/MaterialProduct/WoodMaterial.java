package Foundation.Products.MaterialProduct;

import Foundation.Products.Product;

public class WoodMaterial extends MaterialProduct {

    public WoodMaterial(int amount) {
        super(amount);
        this.name = "wood";
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
            if (((MaterialProduct)other).isWood()) return true;
        }
        return false;
    }

    @Override
    public boolean isWood(){
        return true;
    }

}
