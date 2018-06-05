package Foundation.Works;

import Foundation.Person.People;
import Foundation.Recources.EdibleProduct.WheatProduct;
import Foundation.Recources.Product;
import Foundation.Recources.ProductStore;
import Foundation.Works.Occupation.Occupation;

public class WheatMakingWork extends ProductMakingWork{


    public WheatMakingWork(People people, ProductStore store, Occupation occupation) {
        super(people, store, occupation);
    }

    @Override
    public Product makeProduct() {
        return new WheatProduct(people.getAmount());
    }
}
