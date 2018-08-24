package Foundation.Products.MaterialProduct;

import Foundation.Products.Product;

public class WoodMaterial extends MaterialProduct {

    public WoodMaterial(int amount) {
        super(amount);
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

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "wood";
        }
        return super.getValue(key);
    }
}
