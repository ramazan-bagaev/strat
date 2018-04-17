package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.Army.Army;
import Foundation.Color;
import Foundation.GameWindowHelper.HelperElements.ChoosenFieldHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Utils.Index;
import Utils.Subscription;

public class ArmyChoosenFieldMode extends Mode {

    private ChoosenFieldHelper choosenFieldHelper;
    private Army army;
    private Subscription armyPosSubscription;

    public ArmyChoosenFieldMode(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement);
        this.army = army;
    }

    public void init(){
        Index point = army.getPos();
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(point);
        if (helperField == null){
            helperField = new HelperField(gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(point), gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(point, helperField);
        }
        choosenFieldHelper = new ChoosenFieldHelper(point, new Color(Color.Type.Blue), helperField);
        helperField.setChoosenFieldHelper(choosenFieldHelper);
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
        HelperField helperField = choosenFieldHelper.getParent();
        helperField.setChoosenFieldHelper(null);
        if (helperField.isEmpty()) helperField.delete();
        init();
    }

    @Override
    public void removeHelpers() {
        HelperField helperField = choosenFieldHelper.getParent();
        helperField.setChoosenFieldHelper(null);
        if (helperField.isEmpty()) helperField.delete();
        army.unsubscribe("pos", armyPosSubscription);
    }

    public void setNewPos(Index pos){
        choosenFieldHelper.setNewPos(pos);
    }
}
