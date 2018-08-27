package Foundation.Works;

import Foundation.FieldObjects.NaturalObjects.CropFieldObject;
import Foundation.Flora.CropField;
import Foundation.Person.People;
import Foundation.Products.EdibleProduct.WheatProduct;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;
import Utils.Geometry.Index;

public class WheatMakingWork extends ProductMakingWork{

    private CropField cropField;
    private int amount;

    public WheatMakingWork(ProductStore store, CropField cropField, Occupation occupation) {
        super(store, occupation);
        this.cropField = cropField;
        endStage = 7;
    }

    public int getSize(){
        return cropField.getSize();
    }

    public int getFreePosition(){
        return cropField.getSize() - people.getAmount();
    }

    @Override
    public boolean initWork(){
        amount = Math.min(people.getAmount(), cropField.getSize());
        return amount != 0;
    }

    @Override
    public void makeProduct() {
        createdProducts.add(new WheatProduct(amount));
    }
}
