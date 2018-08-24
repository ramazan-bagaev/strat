package Foundation.Works;

import Foundation.Person.People;
import Foundation.Products.EdibleProduct.VegetableProduct;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;

public class VegetableMakingWork extends ProductMakingWork {

    public VegetableMakingWork(ProductStore store, Occupation occupation) {
        super(store, occupation);
    }

    @Override
    public Product makeProduct() {
        return new VegetableProduct(people.getAmount());
    }

    @Override
    public boolean initWork() {
        return true;
    }
}
