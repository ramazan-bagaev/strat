package Foundation.GameWindowHelper.Modes.InFieldMode;

import Foundation.Color;
import Foundation.FieldObjects.BuildingObject.BuildingObject;
import WindowElements.GameWindowElements.GameWindowHelperElement;

public class LinkedToBuildingRoadCoveringMode extends CoveringObjectMode {

    public LinkedToBuildingRoadCoveringMode(GameWindowHelperElement gameWindowHelperElement, BuildingObject buildingObject, Color color) {
        super(gameWindowHelperElement, buildingObject.getTransportNetObjects(), color);
    }
}
