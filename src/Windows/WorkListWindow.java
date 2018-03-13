package Windows;

import Foundation.*;
import Foundation.GameWindowHelper.AddWorkState;

import java.util.ArrayList;

public class WorkListWindow extends ClosableWindow {

    private City city;

    public WorkListWindow(City city, Windows parent) {
        super(new Coord(700, 500), new Coord(150, 300), parent);
        this.city = city;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        ArrayList<Element> elements = new ArrayList<>();

        Label farm = new Label(pos.add(new Coord(10, 10)), new Coord(80, 20), "farm", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = getParent().getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                AddWorkState addWorkState = new AddWorkState(gameWindowHelperElement, city);
                gameWindowHelperElement.setState(addWorkState);
                close();
            }
        };
        addWindowElement(farm);
    }


}
