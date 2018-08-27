package Foundation.Products.EdibleProduct;


import Foundation.Products.Product;

public class MeatProduct extends EdibleProduct {

    public MeatProduct(int amount) {
        super(amount);
        this.name = "meat";
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
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
