package Foundation.Products.RawMaterials;

import Foundation.Products.Product;

public class RawMaterial extends Product implements RawMaterialType{


    public RawMaterial(int amount) {
        super(amount);
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (other.isRawMaterial()) return true;
        return false;
    }

    @Override
    public boolean isRawMaterial(){
        return true;
    }

    @Override
    public boolean isMineral() {
        return false;
    }
}
