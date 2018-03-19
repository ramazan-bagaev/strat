package Windows.WorkPlaceWindows;

import Foundation.*;
import Foundation.GameWindowHelper.States.AddVillageState;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.States.AddManorFieldState;
import Images.Image;
import Images.ManorImage;
import Windows.ClosableWindow;
import Windows.ResourceStoreWindow;

public class ManorInfoWindow extends ClosableWindow {

    private Manor manor;

    public ManorInfoWindow(Manor manor, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.manor = manor;

        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        Image farmImage = new ManorImage(new Coord(0, 0), new Coord(100, 100), this);
        addWindowElement(farmImage);

        Button addField = new Button(new Coord(20, 140), new Coord(100, 20), this, "add field") {

            @Override
            public void click(Coord point) {
                GameWindowHelperElement gameWindowHelperElement = ManorInfoWindow.this.getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                gameWindowHelperElement.setState(new AddManorFieldState(gameWindowHelperElement, manor));
            }
        };
        addWindowElement(addField);

        Button addWork = new Button(new Coord(20, 200), new Coord(100, 20), this, "add work") {

            @Override
            public void click(Coord point) {
                addManorWorkWindow();
            }
        };

        addWindowElement(addWork);

        Button showResource = new Button(new Coord(20, 240), new Coord(150, 20), this, "show resources"){

            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ResourceStoreWindow(manor.getResourceStore(), frame));
            }
        };

        addWindowElement(showResource);

        Button addVillage = new Button(new Coord(20, 280), new Coord(150, 20), this, "add village"){

            @Override
            public void click(Coord point) {
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddVillageState addVillageState = new AddVillageState(manor, gameWindowHelperElement);
                gameWindowHelperElement.setState(addVillageState);
            }
        };

        addWindowElement(addVillage);




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
