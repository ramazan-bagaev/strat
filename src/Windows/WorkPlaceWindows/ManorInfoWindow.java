package Windows.WorkPlaceWindows;

import Foundation.*;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.States.AddManorFieldState;
import Images.Image;
import Images.ManorImage;
import Windows.ClosableWindow;

public class ManorInfoWindow extends ClosableWindow {

    private Manor manor;

    public ManorInfoWindow(Manor manor, Windows parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.manor = manor;

        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        Image farmImage = new ManorImage(getPos(), new Coord(100, 100), this);
        addWindowElement(farmImage);

        Button addField = new Button(getPos().add(new Coord(20, 140)), new Coord(100, 20), this, "add field") {

            @Override
            public void click(Coord point) {
                GameWindowHelperElement gameWindowHelperElement = ManorInfoWindow.this.getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                gameWindowHelperElement.setState(new AddManorFieldState(gameWindowHelperElement, manor));
            }
        };
        addWindowElement(addField);

        Button addWork = new Button(getPos().add(new Coord(20, 200)), new Coord(100, 20), this, "add work") {

            @Override
            public void click(Coord point) {
                addManorWorkWindow();
            }
        };

        addWindowElement(addWork);



    }

    public void setManor(Manor manor){
        this.manor = manor;
        setElements();
    }

    public void addManorWorkWindow(){
        for(Window window: getParent().getWindows()){
            if (window.getClass() == ManorWorkWindow.class) return;
        }
        addWindow(new ManorWorkWindow(manor, getParent()));
    }
}
