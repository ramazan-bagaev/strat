package Foundation.Works;

import Foundation.Person.People;
import Foundation.Products.EdibleProduct.MeatProduct;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;

public class MeatMakingWork extends ProductMakingWork {

    public MeatMakingWork(ProductStore store, Occupation occupation) {
        super(store, occupation);
    }

    @Override
    public Product makeProduct() {
        return new MeatProduct(people.getAmount());
    }

    @Override
    public boolean initWork() {
        return true;
    }
}
