package Foundation.Products.MaterialProduct;

import Foundation.Products.Product;

public abstract class MaterialProduct extends Product implements MaterialType {

    public MaterialProduct(int amount){
        super(amount);
    }


    @Override
    public boolean isMaterial(){
        return true;
    }

    @Override
    public boolean isWood() {
        return false;
    }

    @Override
    public boolean isMetal() {
        return false;
    }

    @Override
    public boolean isSameProductAs(Product other){
        if (other.isMaterial()) return true;
        return false;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "type":
                return "material";
        }
        return super.getValue(key);
    }
}
