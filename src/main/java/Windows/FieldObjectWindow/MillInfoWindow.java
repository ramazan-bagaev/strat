package Windows.FieldObjectWindow;

import Foundation.FieldObjects.BuildingObject.MillObject;
import Foundation.Frame;
import Utils.Geometry.Coord;
import WindowElements.Button;
import Windows.ClosableWindow;
import Windows.ProductContainerWindow;

public class MillInfoWindow extends ClosableWindow {

    private MillObject millObject;

    public MillInfoWindow(MillObject millObject, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.millObject = millObject;
        setShapes();
    }

    public void setShapes(){
        removeWindowElements();
        addCloseButton();

        Button button = new Button(new Coord(0, 50), new Coord(100, 20), "store", this){

            @Override
            public void click(Coord point){
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ProductContainerWindow(millObject.getStore(), frame));
            }
        };

        addWindowElement(button);

    }
}
