package Foundation.Works;

import Foundation.FieldObjects.NaturalObjects.ForestObject;
import Foundation.Products.MaterialProduct.WoodMaterial;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Works.Occupation.Occupation;

public class WoodMakingWork extends ProductMakingWork {

    private ForestObject forestObject;
    private int amount;

    public WoodMakingWork(ProductStore store, ForestObject forestObject, Occupation occupation) {
        super(store, occupation);
        this.forestObject = forestObject;
        endStage = 7;
    }

    public int getFreePositions(){
        return forestObject.getSquare() - people.getAmount();
    }

    @Override
    public Product makeProduct() {
        forestObject.decreaseWoodAmount(amount);
        return new WoodMaterial(amount);
    }

    @Override
    public boolean initWork() {
        amount = Math.min(people.getAmount(), forestObject.getSquare());
        return amount != 0;
    }
}
