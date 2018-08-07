package Foundation.Products;

import java.util.ArrayList;

public class ProductBundle {

    private ArrayList<Product> products;
    private int maxAmount;
    private int currentAmount;

    public ProductBundle(int maxAmount){
        this.maxAmount = maxAmount;
        this.currentAmount = 0;
        products = new ArrayList<>();
    }

    public void addProduct(Product newProduct){
        int amount = newProduct.getAmount();
        if (amount + currentAmount > maxAmount) return;
        currentAmount += amount;
        for(Product product: products){
            if (product.isSameProductAs(newProduct)){
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
