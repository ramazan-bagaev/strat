package Foundation.GameWindowHelper.States;

import Foundation.Army.Army;
import Foundation.Elements.ArmyFieldElement;
import Foundation.GameWindowHelper.Modes.ArmyControllingModes.*;
import Foundation.MainWindowCameraConfiguration;
import Utils.Geometry.Index;
import Foundation.Field;
import Foundation.GameWindowHelper.Modes.CityInfoMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;
import Foundation.GameWindowHelperElement;
import Utils.Geometry.Coord;
import Windows.ElementInfoWindow.ArmyInfoWindow;

public class ArmyControllingState extends HelperState {

    private ArmyChoosenFieldMode choosenFieldMode;
    private CityInfoMode cityInfoMode;
    private MegaBorderMode megaBorderMode;
    private ArmyMovingProgressMode armyMovingProgressMode;
    private ArmyMovingDirectionMode armyMovingDirectionMode;
    private ArmyHooveringMode armyHooveringMode;
    private ArmyBattleMode armyBattleMode;

    private Army army;

    public ArmyControllingState(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement);
        cityInfoMode = new CityInfoMode(gameWindowHelperElement);
        choosenFieldMode = new ArmyChoosenFieldMode(gameWindowHelperElement, army);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
        armyMovingProgressMode = new ArmyMovingProgressMode(gameWindowHelperElement, army);
        armyMovingDirectionMode = new ArmyMovingDirectionMode(gameWindowHelperElement, army);
        armyHooveringMode = new ArmyHooveringMode(gameWindowHelperElement, null);
        armyBattleMode = new ArmyBattleMode(gameWindowHelperElement, army);
        this.army = army;
    }

    @Override
    public boolean isProperMode(){
        return (gameWindowHelperElement.getParent().getParent().getMode() == MainWindowCameraConfiguration.Mode.Normal);
    }

    @Override
    public void putHelperElements() {
        cityInfoMode.putHelpers();
        choosenFieldMode.putHelpers();
        megaBorderMode.putHelpers();
        armyMovingProgressMode.putHelpers();
        armyMovingDirectionMode.putHelpers();
        armyHooveringMode.putHelpers();
        armyBattleMode.putHelpers();
        choosenFieldMode.setNewPos(army.getFieldPos());
    }

    @Override
    public void click(Coord point) {
        if (!isProperMode()) return;
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);


        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
        if (field != null) {
            ArmyFieldElement armyElement = field.getArmyElement();
            if (armyElement != null) {
                Army newArmy = armyElement.getArmy();
                if (!this.army.equals(newArmy)){
                    setArmy(newArmy);
                    ArmyInfoWindow armyInfoWindow = new ArmyInfoWindow(newArmy, gameWindowHelperElement.getMainWindow().getParent());
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
        gameWindowHelperElement.setStandardState();
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
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        if (armyHooveringMode.getIndex() != null) {
            if (armyHooveringMode.getIndex().equals(index)) return;
        }
        armyHooveringMode.removeHelpers();
        armyHooveringMode.setIndex(index);
        armyHooveringMode.putHelpers();
    }

    @Override
    public void clearHelperElements() {
        choosenFieldMode.removeHelpers();
        cityInfoMode.removeHelpers();
        megaBorderMode.removeHelpers();
        armyMovingProgressMode.removeHelpers();
        armyMovingDirectionMode.removeHelpers();
        armyHooveringMode.removeHelpers();
        armyBattleMode.removeHelpers();
    }

    @Override
    public void run() {
    }


    public void setArmy(Army army){
        this.army = army;

        choosenFieldMode.removeHelpers();
        choosenFieldMode = new ArmyChoosenFieldMode(gameWindowHelperElement, army);
        choosenFieldMode.putHelpers();

        armyMovingProgressMode.removeHelpers();
        armyMovingProgressMode = new ArmyMovingProgressMode(gameWindowHelperElement, army);
        armyMovingProgressMode.putHelpers();

        armyMovingDirectionMode.removeHelpers();
        armyMovingDirectionMode = new ArmyMovingDirectionMode(gameWindowHelperElement, army);
        armyMovingDirectionMode.putHelpers();

        armyBattleMode.removeHelpers();
        armyBattleMode = new ArmyBattleMode(gameWindowHelperElement, army);
        armyBattleMode.putHelpers();
    }


}
