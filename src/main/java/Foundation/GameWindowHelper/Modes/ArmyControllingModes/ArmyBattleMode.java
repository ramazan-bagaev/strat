package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.Army.Army;
import Foundation.Army.Battle;
import Foundation.Army.Combatant;
import Foundation.Field;
import Foundation.GameWindowHelper.HelperElements.BattleHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Utils.Subscription;

import java.util.ArrayList;

public class ArmyBattleMode extends Mode {

    private Subscription battleSubscription;

    private ArrayList<BattleHelper> battleHelpers;
    private Army army;

    public ArmyBattleMode(GameWindowHelperElement gameWindowHelperElement, Army army) {
        super(gameWindowHelperElement);
        this.army = army;
    }

    public void init(){
        for(Battle battle: army.getBattles()){
            Combatant first = battle.getFirst();
            Combatant second = battle.getSecond();
            Field field1 = first.getCombatantField();
            Field field2 = second.getCombatantField();

            HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(field1.getFieldMapPos());
            if (helperField == null) {
                helperField = new HelperField(field1,
                        gameWindowHelperElement.getMap());
                helperField.getMap().addByIndex(field1.getFieldMapPos(), helperField);
            }
            BattleHelper battleHelper = new BattleHelper(helperField, Combatant.State.Attack,
                    field1.getFieldMapPos().whatDirection(field2.getFieldMapPos()));
            helperField.addHelperElement(battleHelper);
            battleHelpers.add(battleHelper);

            helperField = gameWindowHelperElement.getMap().getFieldByIndex(field2.getFieldMapPos());
            if (helperField == null) {
                helperField = new HelperField(field2,
                        gameWindowHelperElement.getMap());
                helperField.getMap().addByIndex(field2.getFieldMapPos(), helperField);
            }
            battleHelper = new BattleHelper(helperField, Combatant.State.Attack,
                    field2.getFieldMapPos().whatDirection(field1.getFieldMapPos()));
            helperField.addHelperElement(battleHelper);
            battleHelpers.add(battleHelper);
        }
    }

    @Override
    public void putHelpers() {
        battleHelpers = new ArrayList<>();
        battleSubscription = new Subscription() {
            @Override
            public void changed() {
                renewHelpers();
            }
        };
        army.subscribe("battle", battleSubscription);
        init();
    }

    public void renewHelpers(){
        for(BattleHelper battleHelper: battleHelpers){
            HelperField helperField = battleHelper.getParent();
            helperField.removeHelperElement(battleHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        battleHelpers.clear();
        init();
    }

    @Override
    public void removeHelpers() {
        army.unsubscribe("battle", battleSubscription);
        for(BattleHelper battleHelper: battleHelpers){
            HelperField helperField = battleHelper.getParent();
            helperField.removeHelperElement(battleHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        battleHelpers.clear();
    }
}
