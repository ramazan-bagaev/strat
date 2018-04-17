package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.Army.Army;
import Foundation.GameWindowHelper.HelperElements.ArmyMovingProgressHelper;
import Foundation.GameWindowHelper.HelperElements.ProgressHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Utils.Index;
import Utils.Subscription;

import java.util.ArrayList;

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
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(army.getPos());
        if (helperField == null){
            helperField = new HelperField(gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(army.getPos()), gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(army.getPos(), helperField);
        }
        progress = army.getMovingProgress();
        progressHelper = new ArmyMovingProgressHelper(helperField, progress);
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
