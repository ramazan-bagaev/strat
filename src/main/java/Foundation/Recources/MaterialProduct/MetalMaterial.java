package Foundation.Recources.MaterialProduct;

import Foundation.Recources.MaterialProduct.MaterialProduct;
import Foundation.Recources.Product;

public class MetalMaterial extends MaterialProduct {

    public MetalMaterial(int amount) {
        super(amount);
    }

    @Override
    public boolean isSameAs(Product other) {
        if (other.isMaterial()){
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
