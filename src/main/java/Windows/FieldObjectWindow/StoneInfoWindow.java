package Windows.FieldObjectWindow;

import Foundation.FieldObjects.NaturalObjects.StoneObject;
import Foundation.Frame;
import Utils.Geometry.Coord;
import WindowElements.Button;
import Windows.ClosableWindow;
import Windows.MineralConcentrationWindow;
import Windows.ProductContainerWindow;

public class StoneInfoWindow extends ClosableWindow {

    private StoneObject stoneObject;

    public StoneInfoWindow(StoneObject stoneObject, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.stoneObject = stoneObject;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        Button button = new Button(new Coord(0, 50), new Coord(100, 20), "contain", this){

            @Override
            public void click(Coord point){
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("mineral concentration window",
                        new MineralConcentrationWindow(stoneObject.getMineralConcentration(),frame));
            }
        };

        addWindowElement(button);

    }
}
