package Foundation.Works;

import Foundation.FieldObjects.NaturalObjects.CropFieldObject;
import Foundation.Person.People;
import Foundation.Products.EdibleProduct.WheatProduct;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;
import Utils.Geometry.Index;

public class WheatMakingWork extends ProductMakingWork{

    private CropFieldObject cropFieldObject;

    public WheatMakingWork(ProductStore store, CropFieldObject cropFieldObject, Occupation occupation) {
        super(store, occupation);
        this.cropFieldObject = cropFieldObject;
    }

    public int getSize(){
        Index size = cropFieldObject.getSize();
        return size.x * size.y;
    }

    @Override
    public Product makeProduct() {
        return new WheatProduct(people.getAmount());
    }
}
