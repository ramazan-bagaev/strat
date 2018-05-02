package Foundation.Army;


import Foundation.Field;
import Utils.Index;

import java.util.ArrayList;

public interface Combatant {

    enum State {
        Attack, Defend, Moving, None
    }

    double getHealth(); // percent, from 0 to 100

    void addBattle(Battle battle);

    ArrayList<Battle> getBattles();

    void removeBattle(Battle battle);

    Field getCombatantField();

    FrontLine getFrontLine(Index.Direction direction);

    int getBasicDamage(Index.Direction direction);

    Index.Direction getDirectionTo(Combatant combatant);




}