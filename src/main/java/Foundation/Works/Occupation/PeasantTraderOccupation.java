package Foundation.Works.Occupation;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Person.HouseHold;
import Foundation.Person.Person;
import Foundation.Products.ProductBundle;
import Foundation.Time.TimeDuration;
import Foundation.Time.TimeDurations.OneWeekdayTimeDuration;
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
        TransportWork commute = new TransportWork(this, houseHold.getHouse(), marketObject,
                productBundle);
        if (commute.getVisitedNodes() == null) return;
        TimeDuration commuteTime = mainTrader.getSchedule().getFreeDayTimeDuration(commute.getTimeAmountNeeded(), 7);
        if (commuteTime == null) return;
        commute.addPerson(mainTrader, commuteTime);

        TradeWork tradeWork = new TradeWork(this, mainTrader, marketObject, productBundle);
        TimeDuration tradeTime = mainTrader.getSchedule().getFreeDayTimeDuration(7, 7);
        if (tradeTime == null) return;

        tradeWork.addPerson(mainTrader, tradeTime);


        tradeWork.addPreviousWork(commute);

        addWork(tradeWork);
        addWork(commute);
    }

    @Override
    public void run(){
        super.run();
    }


}
