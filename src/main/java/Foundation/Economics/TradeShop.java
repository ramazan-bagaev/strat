package Foundation.Economics;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Person.Person;
import Foundation.Products.Product;
import Foundation.Products.ProductBundle;
import Foundation.Products.ProductStore;
import Utils.Broadcaster;
import Utils.Subscription;

import java.util.HashMap;

public class TradeShop implements Broadcaster {

    private ProductBundle goods;

    private HashMap<Product, Integer> prices;

    private Person trader;

    private MarketObject marketObject;

    public TradeShop(Person trader, ProductBundle goods, MarketObject marketObject){
        this.trader = trader;
        this.marketObject = marketObject;
        this.goods = goods;
        prices = new HashMap<>();
    }

    public Person getTrader(){
        return trader;
    }

    public int getPrice(Product product){
        return prices.getOrDefault(product, -1);
    }

    public boolean hasProduct(Product product){
        return goods.hasProduct(product);
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount": return String.valueOf(goods.getAmount());
        }
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "amount": goods.subscribe(key, subscription);
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "amount": goods.unsubscribe(key, subscription);
        }
    }
}
