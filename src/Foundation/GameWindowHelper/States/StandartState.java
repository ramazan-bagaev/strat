package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.GameWindowHelper.*;
import Foundation.GameWindowHelper.Modes.ChoosenFieldMode;
import Foundation.GameWindowHelper.Modes.CityInfoMode;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;

import java.util.ArrayList;

public class StandartState extends HelperState {

    private ChoosenFieldMode choosenFieldMode;
    private CityInfoMode cityInfoMode;
    private MegaBorderMode megaBorderMode;


    public StandartState(GameWindowHelperElement gameWindowHelperElement){
        super(gameWindowHelperElement);
        cityInfoMode = new CityInfoMode(gameWindowHelperElement);
        choosenFieldMode = new ChoosenFieldMode(gameWindowHelperElement);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
        cityInfoMode.putHelpers();
        choosenFieldMode.putHelpers();
        megaBorderMode.putHelpers();
    }



    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);

        choosenFieldMode.setNewPos(point);

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
}
