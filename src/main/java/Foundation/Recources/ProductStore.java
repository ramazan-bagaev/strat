package Foundation.Recources;

import Foundation.FieldObjects.BuildingObject;

import java.util.ArrayList;

public class ProductStore {

    private ArrayList<Product> products;
    private BuildingObject storePlace;

    public ProductStore(BuildingObject storePlace){
        this.storePlace = storePlace;
        products = new ArrayList<>();
    }

    public void addProduct(Product newProduct){
        for(Product product: products){
            if (product.isSameAs(newProduct)){
                int amount = newProduct.getAmount();
                product.increase(amount);
                newProduct.decrease(amount);
                return;
            }
        }
        products.add(newProduct);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public BuildingObject getStorePlace() {
        return storePlace;
    }
}
