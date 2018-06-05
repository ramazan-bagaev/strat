package Foundation.Works;

import Foundation.Person.People;
import Foundation.Recources.EdibleProduct.MeatProduct;
import Foundation.Recources.Product;
import Foundation.Recources.ProductStore;
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
