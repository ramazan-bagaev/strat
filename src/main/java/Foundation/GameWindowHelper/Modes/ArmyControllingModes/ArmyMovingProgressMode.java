package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.Army.Army;
import Foundation.GameWindowHelper.HelperElements.ArmyMovingProgressHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import WindowElements.GameWindowElements.GameWindowHelperElement;
import Utils.Subscription;

public class ArmyMovingProgressMode extends Mode {

    private Subscription armyMoveSubscription;

    private ArmyMovingProgressHelper progressHelper;
    private int progress;
    private Army army;

    public ArmyMovingProgressMode(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement);
        this.army = army;
    }

    public void init(){
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(army.getFieldPos());
        if (helperField == null){
            helperField = new HelperField(gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(army.getFieldPos()), gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(army.getFieldPos(), helperField);
        }
        progress = army.getMovingProgress();
        progressHelper = new ArmyMovingProgressHelper(helperField, army.getState(), army.getMovingProgress());
        helperField.addHelperElement(progressHelper);
    }

    @Override
    public void putHelpers() {
        armyMoveSubscription = new Subscription() {
            @Override
            public void changed() {
                renewHelpers();
            }
        };
        army.subscribe("movingProgress", armyMoveSubscription);
        init();
    }

    public void renewHelpers(){
        HelperField parent = progressHelper.getParent();
        parent.removeHelperElement(progressHelper);
        if (parent.isEmpty()) parent.delete();
        init();
    }

    @Override
    public void removeHelpers() {
        HelperField parent = progressHelper.getParent();
        parent.removeHelperElement(progressHelper);
        if (parent.isEmpty()) parent.delete();
        army.unsubscribe("movingProgress", armyMoveSubscription);
    }
}
