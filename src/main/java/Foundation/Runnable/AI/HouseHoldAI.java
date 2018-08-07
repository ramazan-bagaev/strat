package Foundation.Runnable.AI;

import Foundation.FieldObjects.BuildingObject.LivingBuildingObject;
import Foundation.Person.HouseHold;
import Foundation.Works.Occupation.Occupation;
import Foundation.Works.Occupation.PeasantOccupation;

public class HouseHoldAI extends AI{

    private HouseHold houseHold;

    public HouseHoldAI(HouseHold houseHold){
        this.houseHold = houseHold;
        houseHold.getProductStore().getStorePlace().getParent().getMap().getGameEngine().addRunEntity(this);
    }

    @Override
    public void makeDecision() {
        if (houseHold.getOccupation() == null){
            LivingBuildingObject buildingObject = houseHold.getHouse();
            //Occupation newOccupation = new PeasantOccupation(houseHold.getHouse());
        }
    }

}
