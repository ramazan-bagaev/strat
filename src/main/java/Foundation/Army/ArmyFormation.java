package Foundation.Army;

import Utils.Broadcaster;
import Utils.Content;
import Utils.Index;
import Utils.Subscription;

import java.util.ArrayList;

public class ArmyFormation implements Broadcaster{

    private ArrayList<ArmyFormationUnit> units;
    private Index size;

    private Content formationContent;



    private Index.Direction generalDirection;

    public ArmyFormation(ArmySquad armySquad, Index pos, Index squadSize, Index size){
        this.size = size;
        this.generalDirection = Index.Direction.Up;
        units = new ArrayList<>();
        formationContent = new Content();
        addUnit(armySquad, pos, squadSize);
    }

    public ArmyFormation(ArmyFormation armyFormation){
        this.size = new Index(armyFormation.getSize());
        this.generalDirection = armyFormation.generalDirection;
        units = new ArrayList<>();
        formationContent = new Content();
        for(ArmyFormationUnit unit: armyFormation.getUnits()){
            units.add(new ArmyFormationUnit(unit));
        }
    }

    public void addUnit(ArmySquad armySquad, Index pos, Index squadSize){
        if (!isFreeZone(pos, squadSize)) return;
        ArmyFormationUnit newUnit = new ArmyFormationUnit(armySquad, pos, squadSize, generalDirection);
        units.add(newUnit);
        formationContent.changed();
    }

    public boolean shiftUnit(Index pos, Index shift){
        ArmyFormationUnit unit = getUnit(pos);
        if (unit == null) return false;
        Index newPos = unit.formationPos.add(shift);
        if (newPos.equals(unit.formationPos)) return false;
        if (!isFreeZoneExceptArmySquad(unit.armySquad, newPos, unit.formationSize)) return false;
        unit.formationPos = newPos;
        formationContent.changed();
        return true;
    }

    public void turnTo(Index.Direction direction){
        if (direction == generalDirection) return;
        int turns = howManyTurnClockWise(generalDirection, direction);
        for(int i = 0; i < turns; i++){
            turnFormationClockWise();
        }
        generalDirection = direction;
    }

    public static int howManyTurnClockWise(Index.Direction from, Index.Direction to){
        switch (from){

            case Up:
                switch (to){

                    case Up:
                        return 0;
                    case Down:
                        return 2;
                    case Right:
                        return 1;
                    case Left:
                        return 3;
                    case None:
                        return 0;
                }
                break;
            case Down:
                switch (to){

                    case Up:
                        return 2;
                    case Down:
                        return 0;
                    case Right:
                        return 3;
                    case Left:
                        return 1;
                    case None:
                        return 0;
                }
                break;
            case Right:
                switch (to){

                    case Up:
                        return 3;
                    case Down:
                        return 1;
                    case Right:
                        return 0;
                    case Left:
                        return 2;
                    case None:
                        return 0;
                }
                break;
            case Left:
                switch (to){

                    case Up:
                        return 1;
                    case Down:
                        return 3;
                    case Right:
                        return 2;
                    case Left:
                        return 1;
                    case None:
                        return 0;
                }
                break;
            case None:
                return 0;
        }
        return 0;
    }

    public void turnFormationClockWise(){
        for(ArmyFormationUnit unit: units){

            Index pos = unit.formationPos;
            Index helpIndex = unit.formationPos.add(unit.formationSize);

            turnClockWise(pos);
            turnClockWise(helpIndex);

            if (pos.x > helpIndex.x) pos.x = helpIndex.x;
            if (pos.y > helpIndex.y) pos.y = helpIndex.y;
            unit.formationPos = pos;

            int sizeX = unit.formationSize.x;
            unit.formationSize.x = unit.formationSize.y;
            unit.formationSize.y = sizeX;

            unit.attentionDirection = Index.turnRight(unit.attentionDirection);
        }
        formationContent.changed();
    }



    public void turnClockWise(Index index){
        //if ((index.x - pieceSize.x/2)*(index.y - pieceSize.y/2) > 0){
            int temp = index.x;
            index.x = size.y - index.y;
            index.y = temp;
        //}
        //else index.y = pieceSize.y - index.y;
    }

    public boolean isFreeZone(Index pos, Index squadSize){
        if (size.x <= pos.x + squadSize.x - 1 || 0 > pos.x) return false;
        if (size.y <= pos.y + squadSize.y - 1 || 0 > pos.y) return false;
        Index index = new Index(0, 0);
        for(int y = pos.y; y < pos.y + squadSize.y; y++){
            for(int x = pos.x; x < pos.x + squadSize.x; x++){
                index.x = x;
                index.y = y;
                if (getUnit(index) != null) return false;
            }
        }
        return true;
    }

    public boolean isFreeZoneExceptArmySquad(ArmySquad armySquad, Index pos, Index squadSize){
        if (size.x <= pos.x + squadSize.x - 1 || 0 > pos.x) return false;
        if (size.y <= pos.y + squadSize.y - 1 || 0 > pos.y) return false;
        Index index = new Index(0, 0);
        for(int y = pos.y; y < pos.y + squadSize.y; y++){
            for(int x = pos.x; x < pos.x + squadSize.x; x++){
                index.x = x;
                index.y = y;
                ArmyFormationUnit unit = getUnit(index);
                if (unit != null){
                    if (unit.armySquad != armySquad) return false;
                }
            }
        }
        return true;
    }

    public ArmyFormationUnit getUnit(Index pos){
        for (ArmyFormationUnit unit: units){
            if (pos.inRectangle(unit.formationPos, unit.formationSize)) return unit;
        }
        return null;
    }

    public FrontLine getFrontLine(Index.Direction direction){
        ArrayList<FrontLinePiece> frontLinePieces = new ArrayList<>();
        switch (direction){

            case Up:
                for(int y = 0; y < size.y; y++) {
                    for (int x = 0; x < size.x; x++) {
                        ArmyFormationUnit unit = getUnit(new Index(x, y));
                        if (unit != null) {
                            FrontLinePiece frontLinePiece = new FrontLinePiece();
                            frontLinePiece.pos = x;
                            frontLinePiece.armySquad = unit.armySquad;
                            frontLinePieces.add(frontLinePiece);
                        }
                    }
                    if (frontLinePieces.size() > 0) return new FrontLine(frontLinePieces, 10);
                }
                break;
            case Down:
                for(int y = size.y - 1; y >= 0; y--) {
                    for (int x = 0; x < size.x; x++) {
                        ArmyFormationUnit unit = getUnit(new Index(x, y));
                        if (unit != null) {
                            FrontLinePiece frontLinePiece = new FrontLinePiece();
                            frontLinePiece.pos = x;
                            frontLinePiece.armySquad = unit.armySquad;
                            frontLinePieces.add(frontLinePiece);
                        }
                    }
                    if (frontLinePieces.size() > 0) return new FrontLine(frontLinePieces, 10);
                }
                break;
            case Right:
                for(int x = size.x - 1; x >= 0; x--) {
                    for (int y = 0; y < size.y; y++) {
                        ArmyFormationUnit unit = getUnit(new Index(x, y));
                        if (unit != null) {
                            FrontLinePiece frontLinePiece = new FrontLinePiece();
                            frontLinePiece.pos = y;
                            frontLinePiece.armySquad = unit.armySquad;
                            frontLinePieces.add(frontLinePiece);
                        }
                    }
                    if (frontLinePieces.size() > 0) return new FrontLine(frontLinePieces, 10);
                }
                break;
            case Left:
                for(int x = 0; x < size.x; x++) {
                    for (int y = 0; y < size.y; y++) {
                        ArmyFormationUnit unit = getUnit(new Index(x, y));
                        if (unit != null) {
                            FrontLinePiece frontLinePiece = new FrontLinePiece();
                            frontLinePiece.pos = y;
                            frontLinePiece.armySquad = unit.armySquad;
                            frontLinePieces.add(frontLinePiece);
                        }
                    }
                    if (frontLinePieces.size() > 0) return new FrontLine(frontLinePieces, 10);
                }
                break;
            case None:
                break;
        }
        return null;
    }

    public Index getSize() {
        return size;
    }

    public ArrayList<ArmyFormationUnit> getUnits() {
        return units;
    }

    public Index.Direction getGeneralDirection() {
        return generalDirection;
    }

    @Override
    public String getValue(String key) {
        return null;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "formation":
                formationContent.subscribe(subscription);
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "formation":
                formationContent.unsubscribe(subscription);
        }
    }

    public boolean changeUnitShape(Index index, Index shift) {
        if (shift.x == 0 && shift.y == 0) return false;
        ArmyFormationUnit unit = getUnit(index);
        if (unit == null) return false;
        Index newSize = new Index(0, 0);
        Index newPos = new Index(0, 0);
        boolean changed = false;
        if (index.x == unit.formationPos.x){
            if (unit.formationSize.x - shift.x > 0) {
                newSize.x = unit.formationSize.x - shift.x;
                newSize.y = unit.formationSize.y;
                newPos.x = unit.formationPos.x + shift.x;
                newPos.y = unit.formationPos.y;
                if (!isFreeZoneExceptArmySquad(unit.armySquad, newPos, newSize)) return false;
                unit.formationSize = newSize;
                unit.formationPos = newPos;
                changed = true;
                shift.x = 0;
            }
        }
        if (index.x == unit.formationPos.x + unit.formationSize.x - 1) {
            if (unit.formationSize.x + shift.x > 0) {
                newSize.x = unit.formationSize.x + shift.x;
                newSize.y = unit.formationSize.y;
                newPos.x = unit.formationPos.x;
                newPos.y = unit.formationPos.y;
                if (!isFreeZoneExceptArmySquad(unit.armySquad, newPos, newSize)) return false;
                unit.formationSize = newSize;
                unit.formationPos = newPos;
                changed = true;
                shift.x = 0;
            }
        }
        if (index.y == unit.formationPos.y){
            if (unit.formationSize.y - shift.y > 0) {
                newSize.x = unit.formationSize.x;
                newSize.y = unit.formationSize.y - shift.y;
                newPos.x = unit.formationPos.x;
                newPos.y = unit.formationPos.y + shift.y;
                if (!isFreeZoneExceptArmySquad(unit.armySquad, newPos, newSize)) return false;
                unit.formationSize = newSize;
                unit.formationPos = newPos;
                changed = true;
                shift.y = 0;
            }
        }
        if (index.y == unit.formationPos.y + unit.formationSize.y - 1) {
            if (unit.formationSize.y + shift.y > 0) {
                newSize.x = unit.formationSize.x;
                newSize.y = unit.formationSize.y + shift.y;
                newPos.x = unit.formationPos.x;
                newPos.y = unit.formationPos.y;
                if (!isFreeZoneExceptArmySquad(unit.armySquad, newPos, newSize)) return false;
                unit.formationSize = newSize;
                unit.formationPos = newPos;
                changed = true;
                shift.y = 0;
            }
        }
        if (changed) formationContent.changed();
        return changed;

    }
}
