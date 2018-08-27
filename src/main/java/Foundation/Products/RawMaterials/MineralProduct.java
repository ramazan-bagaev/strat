package Foundation.Products.RawMaterials;

import Foundation.Products.Product;

public class MineralProduct extends RawMaterial{

    private ElementConcentration elementConcentration;

    public MineralProduct(int amount, ElementConcentration elementConcentration){
        super(amount);
        this.elementConcentration = elementConcentration;
        this.name = "mineral";
    }

    public ElementConcentration getElementConcentration() {
        return elementConcentration;
    }

    @Override
    public boolean isSameProductAs(Product other) {
        if (super.isSameProductAs(other)){
            MineralProduct mineral = (MineralProduct)other;
            if (mineral.isMineral()){
                return elementConcentration.equals(mineral.elementConcentration);
            }
        }
        return false;
    }

    @Override
    public boolean isMineral(){
        return true;
    }
}
