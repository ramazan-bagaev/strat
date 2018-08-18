package Foundation.Works.Occupation;

import Foundation.FieldObjects.BuildingObject.BuildingObject;
import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.GameEngine;
import Foundation.Person.HouseHold;
import Foundation.Person.Person;
import Foundation.Products.ProductBundle;
import Foundation.Time.TimeDuration;
import Foundation.Time.TimeDurations.OneDayAWeekActivityTimeDuration;
import Foundation.Works.TradeWork;
import Foundation.Works.TransportWork;

public class PeasantTraderOccupation extends Occupation{

    private HouseHold houseHold;
    private Person mainTrader;
    private ProductBundle productBundle;
    private MarketObject marketObject;


    public PeasantTraderOccupation(HouseHold houseHold, Person mainTrader, ProductBundle productBundle, MarketObject marketObject){
        super(houseHold.getHouse().getParent().getMap().getGameEngine());
        this.houseHold = houseHold;
        this.mainTrader = mainTrader;
        this.productBundle = productBundle;
        this.marketObject = marketObject;
        initWorks();
        gameEngine.addRunEntity(this);
    }

    private void initWorks(){
        TimeDuration timeDuration = new OneDayAWeekActivityTimeDuration(8, 20, 7);
        TransportWork commute = new TransportWork(this, houseHold.getHouse(), marketObject,
                productBundle);

        TradeWork tradeWork = new TradeWork(this, mainTrader, marketObject,
                productBundle);
        tradeWork.addPerson(mainTrader, timeDuration);

        tradeWork.addPreviousWork(commute);

        addWork(tradeWork);
        addWork(commute);
    }


}
