package Foundation.Recources.MaterialProduct;

import Foundation.Recources.Product;
import Utils.Broadcaster;

public abstract class MaterialProduct extends Product implements MaterialType {

    public MaterialProduct(int amount){
        super(amount);
    }


    @Override
    public boolean isMaterial(){
        return true;
    }

    @Override
    public int getWeight(){
        return 2;
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
    public String getValue(String key) {
        switch (key){
            case "type":
                return "material";
        }
        return super.getValue(key);
    }
}
