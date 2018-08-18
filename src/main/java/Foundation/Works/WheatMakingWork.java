package Foundation.Works;

import Foundation.Person.People;
import Foundation.Products.EdibleProduct.WheatProduct;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;

public class WheatMakingWork extends ProductMakingWork{


    public WheatMakingWork(ProductStore store, Occupation occupation) {
        super(store, occupation);
    }

    @Override
    public Product makeProduct() {
        return new WheatProduct(people.getAmount());
    }
}
