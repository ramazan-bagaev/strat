package Foundation.GameWindowHelper.States;

import Foundation.Army.Army;
import Foundation.GameWindowHelper.Modes.ArmyControllingModes.ArmyChoosenFieldMode;
import Foundation.GameWindowHelper.Modes.ArmyControllingModes.ArmyMovingProgressMode;
import Utils.Index;
import Foundation.Elements.ArmyElement;
import Foundation.Field;
import Foundation.GameWindowHelper.Modes.ChoosenFieldMode;
import Foundation.GameWindowHelper.Modes.CityInfoMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;
import Foundation.GameWindowHelperElement;
import Utils.Coord;
import Windows.ElementInfoWindow.ArmyInfoWindow;

public class ArmyControllingState extends HelperState {

    private ArmyChoosenFieldMode choosenFieldMode;
    private CityInfoMode cityInfoMode;
    private MegaBorderMode megaBorderMode;
    private ArmyMovingProgressMode armyMovingProgressMode;

    private Army army;

    public ArmyControllingState(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement);
        cityInfoMode = new CityInfoMode(gameWindowHelperElement);
        choosenFieldMode = new ArmyChoosenFieldMode(gameWindowHelperElement, army);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
        armyMovingProgressMode = new ArmyMovingProgressMode(gameWindowHelperElement, army);
        this.army = army;
    }

    @Override
    public void putHelperElements() {
        cityInfoMode.putHelpers();
        choosenFieldMode.putHelpers();
        megaBorderMode.putHelpers();
        armyMovingProgressMode.putHelpers();
        choosenFieldMode.setNewPos(army.getPos());
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);


        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
        if (field != null) {
            ArmyElement armyElement = field.getArmyElement();
            if (armyElement != null) {
                Army army = armyElement.getArmy();
                if (!this.army.equals(army)){
                    this.army = army;
                    ArmyInfoWindow armyInfoWindow = new ArmyInfoWindow(army, gameWindowHelperElement.getMainWindow().getParent());
                    gameWindowHelperElement.getMainWindow().getParent().addSpecialWindow("element info window", armyInfoWindow);
                }
                return;
            }
        }

        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());

        choosenFieldMode.setNewPos(index);

        gameWindowHelperElement.getGameWindowElement().click(point);

        gameWindowHelperElement.clearHelperElements();
        gameWindowHelperElement.setStandartState();
    }

    @Override
    public void click2(Coord point) {
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        army.action(index);
    }

    @Override
    public void hoover(Coord point) {

    }

    @Override
    public void clearHelperElements() {
        choosenFieldMode.removeHelpers();
        cityInfoMode.removeHelpers();
        megaBorderMode.removeHelpers();
        armyMovingProgressMode.removeHelpers();
    }

    @Override
    public void run() {
    }


}
