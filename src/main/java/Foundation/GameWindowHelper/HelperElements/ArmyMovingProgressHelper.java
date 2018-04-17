package Foundation.GameWindowHelper.HelperElements;

import Foundation.GameWindowHelper.HelperField;
import Utils.Coord;

public class ArmyMovingProgressHelper extends ProgressHelper {

    public ArmyMovingProgressHelper(HelperField helperField, int progress) {
        super(helperField, new Coord(0, 0), new Coord(helperField.getMap().getFieldSize()/2,
                        helperField.getMap().getFieldSize()/4), progress);
    }
}
