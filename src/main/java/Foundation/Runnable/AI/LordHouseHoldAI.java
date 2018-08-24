package Foundation.Runnable.AI;

import Foundation.FieldObjects.BuildingObject.ManorObject;
import Foundation.Person.HouseHold;
import Foundation.Works.Occupation.LordHouseOccupation;

public class LordHouseHoldAI extends AI {

    private HouseHold houseHold;
    private ManorObject manorObject;
    private LordHouseOccupation lordHouseOccupation;

    public LordHouseHoldAI(){
    }

    public void init(HouseHold houseHold){
        if (this.houseHold == null) houseHold.getHouse().getParent().getMap().getGameEngine().addRunEntity(this);
        this.houseHold = houseHold;
        this.manorObject = (ManorObject)houseHold.getHouse();
        this.lordHouseOccupation = new LordHouseOccupation(manorObject);
    }

    @Override
    public void makeDecision() {
        lordHouseOccupation.initWorks();
    }
}
