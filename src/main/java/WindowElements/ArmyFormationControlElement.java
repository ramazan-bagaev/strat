package WindowElements;

import Foundation.Army.Army;
import Foundation.Army.ArmyFormation;
import Foundation.Army.ArmyFormationUnit;
import Foundation.Army.ArmySquad;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.Window;
import Foundation.WindowElement;
import Images.ArrowImage;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import Utils.Subscription;

public class ArmyFormationControlElement extends WindowElement {

    private Army army;

    private Subscription armyFormationSubscription;

    private Coord pressedPos;
    private Index hooveredIndex;

    private boolean drag;
    private boolean reshape;
    private int margin;
    private Coord minDelta;


    private boolean onReshapeArea;

    public ArmyFormationControlElement(Army army, Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        this.army = army;
        minDelta = new Coord(size.x/army.getArmyFormation().getSize().x, size.y/army.getArmyFormation().getSize().y);
        armyFormationSubscription = new Subscription() {
            @Override
            public void changed() {
                setShapes();
            }
        };
        army.getArmyFormation().subscribe("formation", armyFormationSubscription);
        setShapes();
    }


    public boolean isThatChoosenUnit(ArmySquad armySquad){
        if (hooveredIndex == null) return false;
        ArmyFormationUnit unit = army.getArmyFormation().getUnit(hooveredIndex);
        if (unit == null) return false;
        return unit.armySquad == armySquad;
    }

    public void setShapes(){
        clearBasicShapes();
        ArmyFormation armyFormation = army.getArmyFormation();
        Coord delta = new Coord(size.x/armyFormation.getSize().x, size.y/armyFormation.getSize().y);
        for(int x = 0; x < armyFormation.getSize().x; x++){
            for(int y = 0; y < armyFormation.getSize().y; y++){
                RectangleShape rectangleShape = new RectangleShape(new Coord(x*delta.x, y*delta.y), new Coord(delta),
                        new Color(Color.Type.Black), true, false);
                addBasicShape(rectangleShape);
            }
        }

        margin = (int) (delta.x/5);
        int marginx2 = margin*2;
        for(ArmyFormationUnit unit: armyFormation.getUnits()){

            Coord pos = new Coord(unit.formationPos.x * delta.x, unit.formationPos.y * delta.y);
            Coord size = new Coord(unit.formationSize.x * delta.x, unit.formationSize.y * delta.y);
            Color color = new Color(Color.Type.LightGray);
            if (onReshapeArea && onReshapeArea && isThatChoosenUnit(unit.armySquad)) color = new Color(Color.Type.Brown);
            RectangleShape rectangleShape = new RectangleShape(pos, size,
                    color, false, true);
            addBasicShape(rectangleShape);
            pos.x += margin;
            pos.y += margin;
            size.x -= marginx2;
            size.y -= marginx2;
            Color color2 = new Color(Color.Type.Red);
            rectangleShape = new RectangleShape(pos, size,
                    color2, true, true);
            addBasicShape(rectangleShape);

            addBasicShapes(new ArrowImage(pos, size, unit.attentionDirection, null).getCopyOfBasicShapesWithoutShift());
        }
    }

    public void clear(){
        army.getArmyFormation().unsubscribe("formation", armyFormationSubscription);
    }

    @Override
    public boolean drag(Coord point, Coord pressedPosition, boolean dragBegin){
        ArmyFormation armyFormation = army.getArmyFormation();
        if (dragBegin){
            initNewPress(point);
            return true;
        }
        if (!point.inRectangle(new Coord(0, 0), getSize())) return true;
        Index newIndex = new Index((int)(point.x/minDelta.x), (int)(point.y/minDelta.y));
        if (!newIndex.inRectangle(new Index(0, 0), armyFormation.getSize())) return true;
        Index index = new Index((int)(pressedPos.x/minDelta.x), (int)(pressedPos.y/minDelta.y));
        Index shift = newIndex.minus(index);
        if (shift.x == 0 && shift.y == 0) return true;
        if (drag) {
            if (changeUnitPosition(index, shift)) pressedPos = point;
            return true;
        }
        if (reshape){
            if (changeShape(index, shift)) pressedPos = point;
        }
        return true;
    }

    @Override
    public void hoover(Coord point){
        if (!point.inRectangle(new Coord(0, 0), getSize())){
            setOnReshapeArea(false);

            return;
        }
        ArmyFormation armyFormation = army.getArmyFormation();
        hooveredIndex = new Index((int)(point.x/minDelta.x), (int)(point.y/minDelta.y));
        if (!hooveredIndex.inRectangle(new Index(0, 0), armyFormation.getSize())){

            setOnReshapeArea(false);
            return;
        }
        ArmyFormationUnit unit = armyFormation.getUnit(hooveredIndex);
        if (unit == null){
            setOnReshapeArea(false);
            return;
        }
        if (point.x % minDelta.x < margin && unit.formationPos.x == hooveredIndex.x) {
            setOnReshapeArea(true);
            return;
        }
        if (point.x % minDelta.x > minDelta.x-margin && unit.formationPos.x + unit.formationSize.x - 1 == hooveredIndex.x) {
            setOnReshapeArea(true);
            return;
        }
        if (point.y % minDelta.y < margin && unit.formationPos.y == hooveredIndex.y) {
            setOnReshapeArea(true);
            return;
        }
        if (point.y % minDelta.y > minDelta.y-margin && unit.formationPos.y + unit.formationSize.y - 1 == hooveredIndex.y) {
            setOnReshapeArea(true);
            return;
        }
        setOnReshapeArea(false);
    }

    public void initNewPress(Coord point){
        drag = false;
        reshape = false;
        pressedPos = point;
        if (!point.inRectangle(new Coord(0, 0), getSize())) return;
        ArmyFormation armyFormation = army.getArmyFormation();
        Index pressedIndex = new Index((int)(pressedPos.x/minDelta.x), (int)(pressedPos.y/minDelta.y));
        if (!pressedIndex.inRectangle(new Index(0, 0), armyFormation.getSize())) return;
        ArmyFormationUnit unit = armyFormation.getUnit(pressedIndex);
        if (unit == null) return;
        if (point.x % minDelta.x < margin && unit.formationPos.x == pressedIndex.x) {
            reshape = true;
            return;
        }
        if (point.x % minDelta.x > minDelta.x-margin && unit.formationPos.x + unit.formationSize.x - 1 == pressedIndex.x) {
            reshape = true;
            return;
        }
        if (point.y % minDelta.y < margin && unit.formationPos.y == pressedIndex.y) {
            reshape = true;
            return;
        }
        if (point.y % minDelta.y > minDelta.y-margin && unit.formationPos.y + unit.formationSize.y - 1 == pressedIndex.y) {
            reshape = true;
            return;
        }
        drag = true;
    }

    public void changeFormationDirection(Index.Direction direction){
        army.getArmyFormation().turnTo(direction);
        setShapes();
        getParent().getParent().getMainWindow().getGameWindowElement().setShapes();
    }

    public boolean changeUnitPosition(Index index, Index shift){
        if (army.getArmyFormation().shiftUnit(index, shift)){
            setShapes();
            getParent().getParent().getMainWindow().getGameWindowElement().setShapes();
            return true;
        }
        return false;
    }

    public boolean changeShape(Index index, Index shift){
        if (army.getArmyFormation().changeUnitShape(index, shift)){
            setShapes();
            getParent().getParent().getMainWindow().getGameWindowElement().setShapes();
            return true;
        }
        return false;
    }

    public void setOnReshapeArea(boolean onReshapeArea) {
        if (this.onReshapeArea == onReshapeArea) return;
        this.onReshapeArea = onReshapeArea;
        setShapes();
    }

}
