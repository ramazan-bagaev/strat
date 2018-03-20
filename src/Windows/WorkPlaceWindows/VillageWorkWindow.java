package Windows.WorkPlaceWindows;

import Foundation.*;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.GameWindowHelper.States.AddVillageState;
import Foundation.GameWindowHelper.States.AddWorkState;
import Windows.ClosableWindow;

public class VillageWorkWindow extends ClosableWindow {

    private Manor manor;

    public VillageWorkWindow(Village village, Frame parent) {
        super(new Coord(700, 500), new Coord(170, 300), parent);
        this.manor = manor;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        Label label = new Label(new Coord(10, 10), new Coord(120, 20), "add farm", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddVillageState addVillageState = new AddVillageState(manor, gameWindowHelperElement);
                gameWindowHelperElement.setState(addVillageState);
                close();
            }
        };
        addWindowElement(label);

        label = new Label(new Coord(10, 40), new Coord(150, 20), "add sawmill", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddWorkState addWorkState = new AddWorkState(gameWindowHelperElement, null);
                gameWindowHelperElement.setState(addWorkState);
                close();
            }
        };
        addWindowElement(label);

        label = new Label(new Coord(10, 70), new Coord(150, 20), "add fishing work", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddWorkState addWorkState = new AddWorkState(gameWindowHelperElement, null);
                gameWindowHelperElement.setState(addWorkState);
                close();
            }
        };
        addWindowElement(label);

        label = new Label(new Coord(10, 100), new Coord(120, 20), "add mine", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddWorkState addWorkState = new AddWorkState(gameWindowHelperElement, null);
                gameWindowHelperElement.setState(addWorkState);
                close();
            }
        };
        addWindowElement(label);
    }
}
