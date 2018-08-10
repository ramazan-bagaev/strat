package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.ArmyFieldElement;
import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.Modes.ChosenFieldMode;
import Foundation.GameWindowHelper.Modes.CityInfoMode;
import Foundation.GameWindowHelper.Modes.InFieldMode.ChosenObjectMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;
import Foundation.Army.Army;
import Foundation.GameWindowHelper.Modes.Mode;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;
import WindowElements.GameWindowElements.GameWindowHelperElement;
import Windows.ElementInfoWindow.ArmyInfoWindow;

public class StandardState extends HelperState {

    private ChosenFieldMode chosenFieldMode;
    private CityInfoMode cityInfoMode;
    private MegaBorderMode megaBorderMode;

    private ChosenObjectMode chosenObjectMode;
    private Mode onFieldObjectClickMode;


    public StandardState(GameWindowHelperElement gameWindowHelperElement){
        super(gameWindowHelperElement);
        cityInfoMode = new CityInfoMode(gameWindowHelperElement);
        chosenFieldMode = new ChosenFieldMode(gameWindowHelperElement);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
        chosenObjectMode = new ChosenObjectMode(gameWindowHelperElement);
    }

    @Override
    public void putHelperElements() {
        cityInfoMode.putHelpers();
        chosenFieldMode.putHelpers();
        megaBorderMode.putHelpers();


        chosenObjectMode.putHelpers();
        if (onFieldObjectClickMode != null) onFieldObjectClickMode.putHelpers();
    }

    @Override
    public void click(Coord point) {
        MainWindowCameraConfiguration.Mode mode = gameWindowHelperElement.getParent().getParent().getMode();
        if (mode == MainWindowCameraConfiguration.Mode.Detailed) clickForDetailed(point);
        if (mode == MainWindowCameraConfiguration.Mode.Normal) clickForNormal(point);

    }

    public void clickForDetailed(Coord point){
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        FieldObject fieldObject = gameWindowHelperElement.getMap().getFieldMap().getFieldObjectByPos(point);
        if (fieldObject == null) {
            chosenObjectMode.setFieldObject(null);
            if (onFieldObjectClickMode != null) onFieldObjectClickMode.removeHelpers();
            onFieldObjectClickMode = null;
            return;
        }
        chosenObjectMode.setFieldObject(fieldObject);
        Mode mode = fieldObject.getModeOnClick(gameWindowHelperElement);
        if (mode != null){
            if (onFieldObjectClickMode != null) onFieldObjectClickMode.removeHelpers();
            onFieldObjectClickMode = mode;
            onFieldObjectClickMode.putHelpers();
        }
        else{
            if (onFieldObjectClickMode != null) onFieldObjectClickMode.removeHelpers();
            onFieldObjectClickMode = null;
        }


        gameWindowHelperElement.getGameWindowElement().click(point);
    }

    public void clickForNormal(Coord point){
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);

        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
        if (field != null) {
            ArmyFieldElement armyElement = field.getArmyElement();
            if (armyElement != null) {
                Army army = armyElement.getArmy();
                gameWindowHelperElement.clearHelperElements();
                gameWindowHelperElement.setState(new ArmyControllingState(gameWindowHelperElement, army));
                ArmyInfoWindow armyInfoWindow = new ArmyInfoWindow(army, gameWindowHelperElement.getMainWindow().getParent());
                gameWindowHelperElement.getMainWindow().getParent().addSpecialWindow("element info window", armyInfoWindow);
                return;
            }
        }
        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        chosenFieldMode.setNewPos(index);

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
        chosenFieldMode.removeHelpers();
        cityInfoMode.removeHelpers();
        megaBorderMode.removeHelpers();
        chosenObjectMode.removeHelpers();
        if (onFieldObjectClickMode != null) onFieldObjectClickMode.removeHelpers();
    }

    @Override
    public void run() {

    }
}
