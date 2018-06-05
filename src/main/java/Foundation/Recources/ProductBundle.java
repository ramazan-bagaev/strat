package Foundation.Recources;

import java.util.ArrayList;

public class ProductBundle {

    private ArrayList<Product> products;
    private int maxWeight;
    private int currentWeight;

    public ProductBundle(int maxWeight){
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        products = new ArrayList<>();
    }

    public void addProduct(Product newProduct){
        int weight = newProduct.getWeight();
        if (weight + currentWeight > maxWeight) return;
        currentWeight+=weight;
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
}
