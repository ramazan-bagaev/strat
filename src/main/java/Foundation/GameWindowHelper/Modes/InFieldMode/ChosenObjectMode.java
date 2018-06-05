package Foundation.GameWindowHelper.Modes.InFieldMode;

import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.FieldObjectHelperElements.ChosenObjectHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;

public class ChosenObjectMode extends Mode {

    private ChosenObjectHelper chosenObjectHelper;
    private FieldObject fieldObject;

    public ChosenObjectMode(GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
    }

    public void init(){
        if (fieldObject == null) return;
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(fieldObject.getParent().getFieldMapPos());
        if (helperField == null){
            helperField = new HelperField(fieldObject.getParent(), gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(fieldObject.getParent().getFieldMapPos(), helperField);
        }
        chosenObjectHelper = new ChosenObjectHelper(fieldObject, helperField);
        helperField.addInFieldHelperElement(chosenObjectHelper);
    }

    public void setFieldObject(FieldObject fieldObject){
        cleanHelpers();
        this.fieldObject = fieldObject;
        putHelpers();
    }

    @Override
    public void putHelpers() {
        init();
    }

    public void cleanHelpers(){
        if (chosenObjectHelper == null) return;
        HelperField helperField = chosenObjectHelper.getParent();
        helperField.removeInFieldHelperElement(chosenObjectHelper);
        if (helperField.isEmpty()) helperField.delete();
    }

    @Override
    public void removeHelpers() {
        cleanHelpers();
    }
}
