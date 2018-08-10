package Foundation.GameWindowHelper.Modes.InFieldMode;

import Foundation.Color;
import Foundation.FieldObjects.FieldObject;
import Foundation.GameWindowHelper.FieldObjectHelperElements.CoveringObjectHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import WindowElements.GameWindowElements.GameWindowHelperElement;

import java.util.ArrayList;

public class CoveringObjectMode extends Mode{

    private ArrayList<CoveringObjectHelper> coveringObjectHelpers;
    private ArrayList<? extends FieldObject> fieldObjectsForCovering;
    private Color color;

    public CoveringObjectMode(GameWindowHelperElement gameWindowHelperElement, ArrayList<? extends FieldObject> fieldObjectsForCovering, Color color) {
        super(gameWindowHelperElement);
        coveringObjectHelpers = new ArrayList<>();
        this.fieldObjectsForCovering = fieldObjectsForCovering;
        this.color = color;
    }

    public void init(){
        for(FieldObject fieldObject: fieldObjectsForCovering){
            if (fieldObject == null) return;
            HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(fieldObject.getParent().getFieldMapPos());
            if (helperField == null){
                helperField = new HelperField(fieldObject.getParent(), gameWindowHelperElement.getMap());
                helperField.getMap().addByIndex(fieldObject.getParent().getFieldMapPos(), helperField);
            }
            CoveringObjectHelper coveringObjectHelper = new CoveringObjectHelper(fieldObject, helperField, color);
            helperField.addInFieldHelperElement(coveringObjectHelper);
            coveringObjectHelpers.add(coveringObjectHelper);
        }
    }

    @Override
    public void putHelpers() {
        init();
    }

    public void cleanHelpers(){
        for(CoveringObjectHelper coveringObjectHelper: coveringObjectHelpers){
            HelperField helperField = coveringObjectHelper.getParent();
            helperField.removeInFieldHelperElement(coveringObjectHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        coveringObjectHelpers.clear();
    }

    @Override
    public void removeHelpers() {
        cleanHelpers();
    }
}
