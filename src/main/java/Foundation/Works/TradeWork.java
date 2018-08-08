package Foundation.Works;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.Person.People;
import Foundation.Works.Occupation.Occupation;

public class TradeWork extends Work {

    private MarketObject marketObject;

    public TradeWork(People people, Occupation occupation, MarketObject marketObject) {
        super(people, occupation);
        this.marketObject = marketObject;
    }

    @Override
    protected void doMainWork() {
        
    }
}
