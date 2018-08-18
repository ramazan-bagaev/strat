package Foundation.Products;

import Foundation.FieldObjects.BuildingObject.BuildingObject;
import Utils.Broadcaster;
import Utils.Content;
import Utils.Subscription;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductContainer implements Broadcaster {

    protected ArrayList<Product> products;
    protected Content productsContent;

    protected int maxAmount;

    public ProductContainer(){
        products = new ArrayList<>();
        productsContent = new Content();
        maxAmount = 10000;
    }

    public void addProduct(Product newProduct){
        if (newProduct.getAmount() + getAmount() > maxAmount) return;
        for(Product product: products){
            if (product.isSameProductAs(newProduct)){
                int amount = newProduct.getAmount();
                product.increase(amount);
                newProduct.decrease(amount);
                return;
            }
        }
        products.add(newProduct);
        productsContent.changed();
    }

    public void removeProduct(Product presentProduct){
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()){
            Product product = iterator.next();
            if (product.isSameProductAs(presentProduct)){
                if (product.getAmount() == 0){
                    iterator.remove();
                    productsContent.changed();
                    return;
                }
            }
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


    public boolean hasProduct(Product product){
        for(Product storeProduct: products){
            if (product.isSameProductAs(storeProduct)) return true;
        }
        return false;
    }

    public int getAmount(){
        int amount = 0;
        for(Product product: products){
            amount += product.getAmount();
        }
        return amount;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount":
                return String.valueOf(getAmount());
        }
        return null;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "amount":
                productsContent.subscribe(subscription);
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "amount":
                productsContent.subscribe(subscription);
        }
    }
}
