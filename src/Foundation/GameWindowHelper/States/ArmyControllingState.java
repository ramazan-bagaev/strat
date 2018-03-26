package Foundation.GameWindowHelper.States;

import Foundation.Runnable.Army;
import Utils.Index;
import Foundation.Elements.ArmyElement;
import Foundation.Field;
import Foundation.GameWindowHelper.Modes.ChoosenFieldMode;
import Foundation.GameWindowHelper.Modes.CityInfoMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;
import Foundation.GameWindowHelperElement;
import Utils.Coord;

public class ArmyControllingState extends HelperState {

    private ChoosenFieldMode choosenFieldMode;
    private CityInfoMode cityInfoMode;
    private MegaBorderMode megaBorderMode;

    private Army army;

    public ArmyControllingState(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement);
        cityInfoMode = new CityInfoMode(gameWindowHelperElement);
        choosenFieldMode = new ChoosenFieldMode(gameWindowHelperElement);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
        this.army = army;
    }

    @Override
    public void putHelperElements() {
        cityInfoMode.putHelpers();
        choosenFieldMode.putHelpers();
        megaBorderMode.putHelpers();
        choosenFieldMode.setNewPos(army.getPos());
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);

        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
        ArmyElement armyElement = field.getArmyElement();
        if (armyElement != null){
            Army army = armyElement.getArmy();
            if (!this.army.equals(army)) this.army = army;
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
        choosenFieldMode.setNewPos(army.getPos());
    }

    @Override
    public void hoover(Coord point) {

    }

    @Override
    public void clearHelperElements() {
        choosenFieldMode.removeHelpers();
        cityInfoMode.removeHelpers();
        megaBorderMode.removeHelpers();
    }

    @Override
    public void run() {

    }


}
