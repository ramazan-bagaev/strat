package Foundation.Works;

import Foundation.Economics.TradeShop;
import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Products.ProductBundle;
import Foundation.Works.Occupation.Occupation;

public class TradeWork extends Work {

    private MarketObject marketObject;
    private ProductBundle productBundle;
    private Person mainTrader;
    private TradeShop tradeShop;

    public TradeWork(People people, Occupation occupation, Person mainTrader, MarketObject marketObject, ProductBundle productBundle) {
        super(people, occupation);
        this.marketObject = marketObject;
        this.productBundle = productBundle;
        this.mainTrader = mainTrader;
        this.endStage = 30;
    }

    private void initShop(){
        this.tradeShop = marketObject.addTradeShop(productBundle, mainTrader.getWallet());
    }

    private void removeShop(){
        marketObject.removeTradeShop(tradeShop);
    }

    @Override
    protected void doMainWork() {
        if (isStarted()){
            initShop();
            return;
        }
        if (isFinished()){
            removeShop();
            return;
        }
    }
}
