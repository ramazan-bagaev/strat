package Foundation.GameWindowHelper.Modes;

import Utils.Index;
import Foundation.GameWindowHelper.HelperElements.ChosenFieldHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelperElement;

public class ChosenFieldMode extends Mode {

    private ChosenFieldHelper chosenFieldHelper;

    public ChosenFieldMode(GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
    }

    @Override
    public void putHelpers() {
        Index point = new Index(0, 0);
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(point);
        if (helperField == null){
            helperField = new HelperField(gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(point), gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(point, helperField);
        }
        chosenFieldHelper = new ChosenFieldHelper(point, helperField);
        helperField.setChosenFieldHelper(chosenFieldHelper);

    }

    @Override
    public void removeHelpers() {
        HelperField helperField = chosenFieldHelper.getParent();
        helperField.setChosenFieldHelper(null);
        if (helperField.isEmpty()) helperField.delete();
    }

    public void setNewPos(Index pos){
        chosenFieldHelper.setNewPos(pos);
    }
}
