package Foundation.Works;

import Foundation.Person.People;
import Foundation.Products.Product;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;

import java.util.ArrayList;

public abstract class ProductMakingWork extends Work{

    private ProductStore productStore;

    protected ArrayList<Product> createdProducts;

    public ProductMakingWork(ProductStore store, Occupation occupation) {
        super(occupation);
        createdProducts = new ArrayList<>();
        this.productStore = store;
        endStage = 2;
    }

    public abstract void makeProduct();

    public abstract boolean initWork();

    @Override
    public boolean doMainWork(){
        if (isBeginningStage()){
            return initWork();
        }
        if (stage == endStage - 1){
            makeProduct();
            for(Product product: createdProducts) productStore.addProduct(product);
            createdProducts.clear();
            return true;
        }
        return true;
    }

}
