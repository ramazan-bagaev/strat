package Windows.WorkPlaceWindows;

import Foundation.*;
import Foundation.Elements.Village;
import Foundation.GameWindowHelper.States.*;
import Utils.Geometry.Coord;
import Windows.ClosableWindow;

public class VillageWorkWindow extends ClosableWindow {

    private Village village;

    public VillageWorkWindow(Village village, Frame parent) {
        super(new Coord(700, 500), new Coord(170, 300), parent);
        this.village = village;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        Button button = new Button(new Coord(10, 10), new Coord(120, 20), "add farm", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddFarmState addFarmState = new AddFarmState(village, gameWindowHelperElement);
                gameWindowHelperElement.setState(addFarmState);
                close();
            }
        };
        addWindowElement(button);

        button = new Button(new Coord(10, 40), new Coord(150, 20),"add sawmill", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddSawmillState addSawmillState = new AddSawmillState(village, gameWindowHelperElement);
                gameWindowHelperElement.setState(addSawmillState);
                close();
            }
        };
        addWindowElement(button);

        button = new Button(new Coord(10, 70), new Coord(150, 20), "add trawler", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddTrawlerState addTrawlerState = new AddTrawlerState(village, gameWindowHelperElement);
                gameWindowHelperElement.setState(addTrawlerState);
                close();
            }
        };
        addWindowElement(button);

        button = new Button(new Coord(10, 100), new Coord(120, 20), "add mine", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddMineState addMineState = new AddMineState(village, gameWindowHelperElement);
                gameWindowHelperElement.setState(addMineState);
                close();
            }
        };
        addWindowElement(button);
    }
}
