package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.Army.Army;
import Foundation.GameWindowHelper.HelperElements.CoveringFieldHelper;
import Foundation.GameWindowHelper.Modes.DirectionMode;
import Foundation.GameWindowHelperElement;
import Utils.Subscription;

public class ArmyMovingDirectionMode extends DirectionMode {

    private Army army;
    private Subscription pathSubscription;
    

    public ArmyMovingDirectionMode(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement, army.getPath());
        this.army = army;
    }

    @Override
    public void putHelpers() {
        pathSubscription = new Subscription() {
            @Override
            public void changed() {
                renewHelpers();
            }
        };
        army.subscribe("path", pathSubscription);
        init();
    }

    public void renewHelpers(){
        cleanHelpers();
        path = army.getPath();
        init();
    }

    @Override
    public void removeHelpers() {
        army.unsubscribe("path", pathSubscription);
        cleanHelpers();
    }
}
