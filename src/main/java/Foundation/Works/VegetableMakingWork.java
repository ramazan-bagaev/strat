package Foundation.Works;

import Foundation.Person.People;
import Foundation.Recources.EdibleProduct.VegetableProduct;
import Foundation.Recources.Product;
import Foundation.Recources.ProductStore;
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
