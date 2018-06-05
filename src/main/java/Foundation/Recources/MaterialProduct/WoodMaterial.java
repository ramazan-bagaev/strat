package Foundation.Recources.MaterialProduct;

import Foundation.Recources.MaterialProduct.MaterialProduct;
import Foundation.Recources.Product;

public class WoodMaterial extends MaterialProduct {

    public WoodMaterial(int amount) {
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
    public boolean isWood(){
        return true;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "wood";
        }
        return super.getValue(key);
    }
}
