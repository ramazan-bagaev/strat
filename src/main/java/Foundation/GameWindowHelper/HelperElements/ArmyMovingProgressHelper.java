package Foundation.GameWindowHelper.HelperElements;

import Foundation.Army.Army;
import Foundation.GameWindowHelper.HelperField;
import Images.ArrowImage;
import Images.TurnArrowImage;
import Utils.Coord;
import Utils.Index;

public class ArmyMovingProgressHelper extends ProgressHelper {

    private Army.State state;

    public ArmyMovingProgressHelper(HelperField helperField, Army.State state, int progress) {
        super(helperField, new Coord(0, 0), new Coord(helperField.getMap().getFieldSize()/2,
                        helperField.getMap().getFieldSize()/4), progress);
        this.state = state;
        setArmyMovingShapes();
    }

    public void setArmyMovingShapes(){
        clearBasicShapes();
        switch (state){

            case Attack:
                return;
            case Defend:
                return;
            case Moving:
                setShapes();
                addShapes(new ArrowImage(new Coord(progressSize.x/4, 0), new Coord(progressSize.x/2, progressSize.y),
                        Index.Direction.Right, null).getCopyOfBasicShapesWithoutShift());
                return;
            case Regrouping:
                setShapes();
                addShapes(new TurnArrowImage(new Coord(progressSize.x/4, 0), new Coord(progressSize.x/2, progressSize.y),
                        null).getCopyOfBasicShapesWithoutShift());
                return;
            case None:
                return;
        }
    }
}
