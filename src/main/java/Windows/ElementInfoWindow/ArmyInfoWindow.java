package Windows.ElementInfoWindow;

import Foundation.Button;
import Foundation.Frame;
import Foundation.Army.Army;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import WindowElements.ArmyFormationControlElement;
import Windows.ClosableWindow;

public class ArmyInfoWindow extends ClosableWindow {

    public Army army;

    private ArmyFormationControlElement controlElement;

    public ArmyInfoWindow(Army army, Frame parent) {
        super(new Coord(0, 500), new Coord(500, 500), parent);

        this.army = army;

        setShapes();
    }

    @Override
    public void setShapes(){

        removeWindowElements();
        addCloseButton();

        controlElement = new ArmyFormationControlElement(army,
                new Coord(10, 10), new Coord(400, 400), this);

        addWindowElement(controlElement);

        Button rightDirection = new Button(new Coord(430, 60), new Coord(70, 20), "right", this){

            @Override
            public void click(Coord point){
                controlElement.changeFormationDirection(Index.Direction.Right);
            }
        };

        addWindowElement(rightDirection);

        //People people = army.getPeople();

        /*MonitoredBroadcastLabel amount = new MonitoredBroadcastLabel(new Coord(10, 10), new Coord(280, 20), "Army pieceSize:",
                people, "amount", this);

        addWindowElement(amount);*/

    }

    public void setArmy(Army army){
        this.army = army;
        setShapes();
    }
}
