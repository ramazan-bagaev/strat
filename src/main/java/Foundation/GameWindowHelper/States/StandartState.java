package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.ArmyElement;
import Foundation.GameWindowHelper.Modes.ChoosenFieldMode;
import Foundation.GameWindowHelper.Modes.CityInfoMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;
import Foundation.Army.Army;
import Utils.Index;
import Utils.Coord;

public class StandartState extends HelperState {

    private ChoosenFieldMode choosenFieldMode;
    private CityInfoMode cityInfoMode;
    private MegaBorderMode megaBorderMode;


    public StandartState(GameWindowHelperElement gameWindowHelperElement){
        super(gameWindowHelperElement);
        cityInfoMode = new CityInfoMode(gameWindowHelperElement);
        choosenFieldMode = new ChoosenFieldMode(gameWindowHelperElement);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
    }

    @Override
    public void putHelperElements() {
        cityInfoMode.putHelpers();
        choosenFieldMode.putHelpers();
        megaBorderMode.putHelpers();
    }


    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);

        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
        if (field != null) {
            ArmyElement armyElement = field.getArmyElement();
            if (armyElement != null) {
                Army army = armyElement.getArmy();
                gameWindowHelperElement.clearHelperElements();
                gameWindowHelperElement.setState(new ArmyControllingState(gameWindowHelperElement, army));
                return;
            }
        }
        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        choosenFieldMode.setNewPos(index);

        gameWindowHelperElement.getGameWindowElement().click(point);
    }

    @Override
    public void click2(Coord point) {

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
