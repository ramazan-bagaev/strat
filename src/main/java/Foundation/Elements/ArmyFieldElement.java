package Foundation.Elements;

import Foundation.*;
import Foundation.Army.Army;
import Foundation.Army.ArmyFormation;
import Foundation.Army.ArmyFormationUnit;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Images.ArmyImage;
import Utils.Coord;
import Utils.Subscription;

import java.util.ArrayList;

public class ArmyFieldElement extends FieldElement {

    private Subscription armyFormationSubscription;

    private Army army;

    private ArmyFormation armyLocalFormation;

    private ArrayList<BasicShape> formationShapes;

    public ArmyFieldElement(Time time, Field parent, FieldMap map, Army army) {
        super(Type.Army, time, parent, map);
        this.army = army;
        this.armyLocalFormation = army.getArmyFormation();
        armyFormationSubscription = new Subscription() {
            @Override
            public void changed() {
                setFormationShapes();
            }
        };
        armyLocalFormation.subscribe("formation", armyFormationSubscription);
        formationShapes = new ArrayList<>();
        int size = parent.getSize();
        setBasicShapes(new ArmyImage(new Coord(), new Coord(size, size), null).getBasicShapesRemoveAndShiftBack());
        setFormationShapes();
    }

    public Army getArmy() {
        return army;
    }

    public void setFormationShapes(){
        formationShapes.clear();
        ArrayList<ArmyFormationUnit> units = armyLocalFormation.getUnits();
        int delta = parent.getFormationUnitSize();
        int margin = 2;
        int margin2 = 4;
        for(ArmyFormationUnit unit: units){
            Coord pos = new Coord(unit.formationPos.x * delta+margin, unit.formationPos.y * delta+margin);
            Coord size = new Coord(unit.formationSize.x * delta-margin2, unit.formationSize.y * delta-margin2);
            RectangleShape rectangleShape = new RectangleShape(pos, size, new Color(Color.Type.Black), false, true);
            rectangleShape.shift(parent.getShift());
            formationShapes.add(rectangleShape);
        }
    }

    public ArrayList<BasicShape> getFormationShapes() {
        return formationShapes;
    }

    public void clear(){
        armyLocalFormation.unsubscribe("formation", armyFormationSubscription);
    }
}
