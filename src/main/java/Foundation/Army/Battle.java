package Foundation.Army;

import Foundation.Army.Army;
import Foundation.Field;
import Foundation.Runnable.RunEntity;

public class Battle implements RunEntity {

    private Combatant first;
    private Combatant second;

    public Battle(Combatant first, Combatant second){
        this.first = first;
        this.second = second;
    }

    public Combatant getFirst() {
        return first;
    }

    public Combatant getSecond() {
        return second;
    }

    @Override
    public void run() {
        FrontLine f1 = first.getFrontLine(first.getDirectionTo(second));
        FrontLine f2 = second.getFrontLine(second.getDirectionTo(first));
        FrontLine.fight(f1, f2);
    }
}
