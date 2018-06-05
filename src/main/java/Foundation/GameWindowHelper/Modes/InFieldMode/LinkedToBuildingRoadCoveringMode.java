package Foundation.GameWindowHelper.Modes.InFieldMode;

import Foundation.Color;
import Foundation.FieldObjects.BuildingObject;
import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;

import java.util.ArrayList;

public class LinkedToBuildingRoadCoveringMode extends CoveringObjectMode {

    public LinkedToBuildingRoadCoveringMode(GameWindowHelperElement gameWindowHelperElement, BuildingObject buildingObject, Color color) {
        super(gameWindowHelperElement, buildingObject.getTransportNetObjects(), color);
    }
}
