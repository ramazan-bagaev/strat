package Foundation.Works;

import Foundation.Economics.TradeShop;
import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Person.Person;
import Foundation.Products.ProductBundle;
import Foundation.Works.Occupation.Occupation;

public class TradeWork extends Work {

    private MarketObject marketObject;
    private ProductBundle productBundle;
    private Person mainTrader;
    private TradeShop tradeShop;

    public TradeWork(Occupation occupation, Person mainTrader, MarketObject marketObject,
                     ProductBundle productBundle) {
        super(occupation);
        this.marketObject = marketObject;
        this.productBundle = productBundle;
        this.mainTrader = mainTrader;
        this.endStage = 7;
    }

    private void initShop(){
        this.tradeShop = marketObject.addTradeShop(productBundle, mainTrader);
    }

    private void removeShop(){
        marketObject.removeTradeShop(tradeShop);
    }

    @Override
    protected boolean doMainWork() {
        if (isBeginningStage()){
            initShop();
            return true;
        }
        if (stage == endStage - 1){
            removeShop();
            return true;
        }
        return true;
    }
}
