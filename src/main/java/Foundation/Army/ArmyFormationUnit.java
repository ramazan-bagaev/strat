package Foundation.Army;

import Utils.Geometry.Index;

public class ArmyFormationUnit {

    public ArmySquad armySquad;
    public Index formationPos;
    public Index formationSize;

    public Index.Direction attentionDirection;

    public ArmyFormationUnit(ArmySquad armySquad, Index formationPos, Index formationSize, Index.Direction attentionDirection){
        this.armySquad = armySquad;
        this.formationPos = formationPos;
        this.formationSize = formationSize;
        this.attentionDirection = attentionDirection;
    }

    public ArmyFormationUnit(ArmyFormationUnit armyFormationUnit){
        this.armySquad = armyFormationUnit.armySquad;
        this.formationPos = new Index(armyFormationUnit.formationPos);
        this.formationSize = new Index(armyFormationUnit.formationSize);
        this.attentionDirection = armyFormationUnit.attentionDirection;
    }

    public boolean isIntersect(ArmyFormationUnit unit){
        Index a1 = formationPos;
        Index a2 = formationPos.add(formationSize).minus(new Index(-1, -1));
        Index b1 = unit.formationPos;
        Index b2 = unit.formationPos.add(unit.formationSize).minus(new Index(-1, -1));
        if (a1.x > b2.x) return false;
        if (a2.x < b1.x) return false;
        if (a1.y > b2.y) return false;
        if (a2.y < b1.y) return false;
        return true;
    }
}
