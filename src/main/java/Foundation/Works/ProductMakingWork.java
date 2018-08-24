package Foundation.Works;

import Foundation.Person.People;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;

public abstract class ProductMakingWork extends Work{

    private ProductStore productStore;

    public ProductMakingWork(ProductStore store, Occupation occupation) {
        super(occupation);
        this.productStore = store;
        endStage = 2;
    }

    public abstract Product makeProduct();

    public abstract boolean initWork();

    @Override
    public boolean doMainWork(){
        if (isBeginningStage()){
            return initWork();
        }
        if (stage == endStage - 1){
            productStore.addProduct(makeProduct());
            return true;
        }
        return true;
    }

}
