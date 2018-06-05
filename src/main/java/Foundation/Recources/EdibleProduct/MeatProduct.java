package Foundation.Recources.EdibleProduct;


import Foundation.Recources.Product;

public class MeatProduct extends EdibleProduct {

    public MeatProduct(int amount) {
        super(amount);
    }

    @Override
    public boolean isSameAs(Product other) {
        if (other.isEdible()){
            if (((EdibleProduct)other).isMeat()) return true;
        }
        return false;
    }

    @Override
    public boolean isMeat(){
        return true;
    }

    @Override
    public String getValue(String key){
        switch (key){
            case "name":
                return "meat";
        }
        return super.getValue(key);
    }

}
