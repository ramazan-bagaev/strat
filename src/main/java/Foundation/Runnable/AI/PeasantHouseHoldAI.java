package Foundation.Runnable.AI;

import Foundation.FieldObjects.BuildingObject.MarketObject;
import Foundation.FieldObjects.BuildingObject.PeasantHouseObject;
import Foundation.Person.HouseHold;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Products.Product;
import Foundation.Products.ProductBundle;
import Foundation.Products.ProductStore;
import Foundation.Works.Occupation.PeasantHouseOccupation;
import Foundation.Works.Occupation.PeasantTraderOccupation;

import java.util.ArrayList;
import java.util.Iterator;

public class PeasantHouseHoldAI extends AI{

    private HouseHold houseHold;
    private PeasantHouseOccupation peasantHouseOccupation;
    private PeasantHouseObject peasantHouseObject;
    private PeasantTraderOccupation peasantTraderOccupation;

    public PeasantHouseHoldAI(){
    }

    public void setHouseHold(HouseHold houseHold){
        if (this.houseHold == null) houseHold.getHouse().getParent().getMap().getGameEngine().addRunEntity(this);
        this.houseHold = houseHold;
        this.peasantHouseObject = (PeasantHouseObject)this.houseHold.getHouse();
        this.peasantHouseOccupation = new PeasantHouseOccupation(this.peasantHouseObject);
    }

    private void tryTrade(){
        if (peasantTraderOccupation != null) return;
        ProductStore productStore = houseHold.getProductStore();
        ProductBundle productBundle = new ProductBundle(100000);
        ArrayList<Product> products = productStore.getProducts();
        if (products.size() == 0) return;
        productBundle.addProduct(products.get(0));
        productStore.removeProduct(products.get(0));
        MarketObject marketObject = houseHold.getHouse().getParent().getInfo().getMarketObject();
        if (marketObject == null) return;
        People people = houseHold.getPeople();
        if (people.getAmount() == 0) return;
        Person mainTrader = people.getPersonArray().get(0);
        peasantTraderOccupation = new PeasantTraderOccupation(houseHold, mainTrader, productBundle, marketObject);
    }

    @Override
    public void makeDecision() {
        if (houseHold == null) return;
        if (peasantTraderOccupation != null) {
            if (peasantTraderOccupation.allWorksDone()) peasantTraderOccupation = null;
        }
        tryTrade();
    }

}
