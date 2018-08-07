package Foundation.Products.MaterialProduct;

import Foundation.Products.Product;

public class MetalMaterial extends MaterialProduct {

    public MetalMaterial(int amount) {
        super(amount);
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

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "metal";
        }
        return super.getValue(key);
    }
}
