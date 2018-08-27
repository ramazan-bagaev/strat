package Foundation.Products.MaterialProduct;

import Foundation.Products.Product;

public class MetalMaterial extends MaterialProduct {

    public MetalMaterial(int amount) {
        super(amount);
        this.name = "metal";
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
            if (((MaterialProduct)other).isMetal()) return true;
        }
        return false;
    }

    @Override
    public boolean isMetal(){
        return true;
    }
}
