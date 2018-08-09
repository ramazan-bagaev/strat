package Foundation.Economics;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Products.Product;
import Foundation.Products.ProductBundle;
import Foundation.Products.ProductStore;

import java.util.HashMap;

public class TradeShop {

    private ProductBundle goods;

    private HashMap<Product, Integer> prices;

    private Wallet traderWallet;

    private MarketObject marketObject;

    public TradeShop(Wallet traderWallet, ProductBundle goods, MarketObject marketObject){
        this.traderWallet = traderWallet;
        this.marketObject = marketObject;
        this.goods = goods;
        prices = new HashMap<>();
    }

    public int getPrice(Product product){
        return prices.getOrDefault(product, -1);
    }

    public boolean hasProduct(Product product){
        return goods.hasProduct(product);
    }
}
