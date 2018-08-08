package Foundation.Economics;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Products.Product;
import Foundation.Products.ProductBundle;
import Foundation.Products.ProductStore;

import java.util.HashMap;

public class TradeShop {

    private ProductStore tradeStore;

    private HashMap<Product, Integer> prices;

    private ProductStore ownerStore;

    private MarketObject marketObject;

    public TradeShop(ProductStore ownerStore, ProductStore tradeStore, MarketObject marketObject){
        this.ownerStore = ownerStore;
        this.marketObject = marketObject;
        this.tradeStore = tradeStore;
        prices = new HashMap<>();
    }

    public int getPrice(Product product){
        return prices.getOrDefault(product, -1);
    }

    public boolean hasProduct(Product product){
        return tradeStore.hasProduct(product);
    }
}
