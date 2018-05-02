package Foundation.Army;

import Utils.Index;

public class FrontLinePiece {

    public ArmySquad armySquad;

    public Fortification fortification;

    public int pos;

    public FrontLinePiece(){

    }

    public static void fight(FrontLinePiece a, FrontLinePiece b){
        System.out.println("fight " + a.pos + " " + b.pos);
    }
}
