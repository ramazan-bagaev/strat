package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.Army.Army;
import Foundation.Color;
import Foundation.GameWindowHelper.HelperElements.ChosenFieldHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Utils.Index;
import Utils.Subscription;

public class ArmyChoosenFieldMode extends Mode {

    private ChosenFieldHelper chosenFieldHelper;
    private Army army;
    private Subscription armyPosSubscription;

    public ArmyChoosenFieldMode(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement);
        this.army = army;
    }

    public void init(){
        Index point = army.getFieldPos();
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(point);
        if (helperField == null){
            helperField = new HelperField(gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(point), gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(point, helperField);
        }
        chosenFieldHelper = new ChosenFieldHelper(point, new Color(Color.Type.Blue), helperField);
        helperField.setChosenFieldHelper(chosenFieldHelper);
    }

    @Override
    public void putHelpers() {
        armyPosSubscription = new Subscription() {
            @Override
            public void changed() {
               renewHelpers();
            }
        };
        army.subscribe("pos", armyPosSubscription);
        init();
    }

    public void renewHelpers(){
        HelperField helperField = chosenFieldHelper.getParent();
        helperField.setChosenFieldHelper(null);
        if (helperField.isEmpty()) helperField.delete();
        init();
    }

    @Override
    public void removeHelpers() {
        HelperField helperField = chosenFieldHelper.getParent();
        helperField.setChosenFieldHelper(null);
        if (helperField.isEmpty()) helperField.delete();
        army.unsubscribe("pos", armyPosSubscription);
    }

    public void setNewPos(Index pos){
        chosenFieldHelper.setNewPos(pos);
    }
}
