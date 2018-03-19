package Windows;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.GameWindowHelper.States.AddWorkState;

public class WorkListWindow extends ClosableWindow {

    private City city;

    public WorkListWindow(City city, Frame parent) {
        super(new Coord(700, 500), new Coord(150, 300), parent);
        this.city = city;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();


        Label manor = new Label(new Coord(10, 10), new Coord(80, 20), "manor", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddWorkState addWorkState = new AddWorkState(gameWindowHelperElement, city);
                gameWindowHelperElement.setState(addWorkState);
                close();
            }
        };
        addWindowElement(manor);
    }


}
