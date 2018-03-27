package Foundation.GameWindowHelper.Modes;

import Utils.Index;
import Foundation.GameWindowHelper.HelperElements.ChoosenFieldHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelperElement;

public class ChoosenFieldMode extends Mode {

    private ChoosenFieldHelper choosenFieldHelper;

    public ChoosenFieldMode(GameWindowHelperElement gameWindowHelperElement) {
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
        choosenFieldHelper = new ChoosenFieldHelper(point, helperField);
        helperField.setChoosenFieldHelper(choosenFieldHelper);

    }

    @Override
    public void removeHelpers() {
        HelperField helperField = choosenFieldHelper.getParent();
        helperField.setChoosenFieldHelper(null);
        if (helperField.isEmpty()) helperField.delete();
    }

    public void setNewPos(Index pos){
        choosenFieldHelper.setNewPos(pos);
    }
}
