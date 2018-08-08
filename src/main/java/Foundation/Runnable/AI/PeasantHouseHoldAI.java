package Foundation.Runnable.AI;

import Foundation.FieldObjects.BuildingObject.PeasantHouseObject;
import Foundation.Person.HouseHold;
import Foundation.Works.Occupation.PeasantOccupation;

public class PeasantHouseHoldAI extends AI{

    private HouseHold houseHold;
    private PeasantOccupation peasantOccupation;
    private PeasantHouseObject peasantHouseObject;

    public PeasantHouseHoldAI(){
    }

    public void setHouseHold(HouseHold houseHold){
        if (this.houseHold == null) houseHold.getHouse().getParent().getMap().getGameEngine().addRunEntity(this);
        this.houseHold = houseHold;
        this.peasantHouseObject = (PeasantHouseObject)this.houseHold.getHouse();
        this.peasantOccupation = new PeasantOccupation(this.peasantHouseObject);
        houseHold.getHouse().getParent().getMap().getGameEngine().addRunEntity(this.peasantOccupation);
    }

    @Override
    public void makeDecision() {
        if (houseHold == null) return;
    }

}
