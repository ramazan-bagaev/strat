package Foundation.Works;

import Foundation.Person.People;
import Foundation.Products.EdibleProduct.VegetableProduct;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Works.Occupation.Occupation;

public class VegetableMakingWork extends ProductMakingWork {

    public VegetableMakingWork(People people, ProductStore store, Occupation occupation) {
        super(people, store, occupation);
    }

    @Override
    public Product makeProduct() {
        return new VegetableProduct(people.getAmount());
    }
}
