package Foundation.Works;

import Foundation.Person.People;
import Foundation.Products.EdibleProduct.MeatProduct;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Works.Occupation.Occupation;

public class MeatMakingWork extends ProductMakingWork {

    public MeatMakingWork(People people, ProductStore store, Occupation occupation) {
        super(people, store, occupation);
    }

    @Override
    public Product makeProduct() {
        return new MeatProduct(people.getAmount());
    }
}
