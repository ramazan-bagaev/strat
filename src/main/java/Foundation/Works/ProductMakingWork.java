package Foundation.Works;

import Foundation.Person.People;
import Foundation.Recources.Product;
import Foundation.Recources.ProductStore;
import Foundation.Works.Occupation.Occupation;

public abstract class ProductMakingWork extends Work{

    private ProductStore productStore;

    public ProductMakingWork(People people, ProductStore store, Occupation occupation) {
        super(people, occupation);
        this.productStore = store;
        endStage = 1;
    }

    public abstract Product makeProduct();

    @Override
    public void doMainWork(){
        productStore.addProduct(makeProduct());
    }

}
