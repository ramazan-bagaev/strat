package Windows.ElementInfoWindow;

import Foundation.*;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.States.AddManorFieldState;
import Images.Image;
import Images.ManorImage;
import Utils.Geometry.Coord;
import WindowElements.MonitoredBroadcastLabel;
import WindowElements.StaticBroadcastLabel;
import Windows.ClosableWindow;
import Windows.IntermediatWindows.ChooseManyPeople.ChooseManorPeopleWindow;
import Windows.IntermediatWindows.ChooseOnePerson.ChooseVillageStewardWindow;
import Windows.PopulationInfoWindow;
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

        Button givePeople = new Button(new Coord(110, 20), new Coord(170, 20), "give society", this){

            @Override
            public void click(Coord point){
                getParent().getParent().addSpecialWindow("population group window",
                        new ChooseManorPeopleWindow(manor, getParent().getParent()));
            }

        };

        addWindowElement(givePeople);

        Button addField = new Button(new Coord(20, 120), new Coord(170, 20), "add field", this) {

            @Override
            public void click(Coord point) {
                GameWindowHelperElement gameWindowHelperElement = ManorInfoWindow.this.getParent().getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                gameWindowHelperElement.setState(new AddManorFieldState(gameWindowHelperElement, manor));
            }
        };
        addWindowElement(addField);


        Button showResource = new Button(new Coord(20, 160), new Coord(200, 20), "show resources", this){

            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ResourceStoreWindow(manor.getResourceStore(), frame));
            }
        };

        addWindowElement(showResource);

        Button addVillage = new Button(new Coord(20, 200), new Coord(200, 20), "add village", this){

            @Override
            public void click(Coord point) {
                getParent().getParent().addSpecialWindow("population group window",
                        new ChooseVillageStewardWindow(manor, getParent().getParent())) ;
            }
        };

        addWindowElement(addVillage);

        StaticBroadcastLabel label = new StaticBroadcastLabel(new Coord(20, 240), new Coord(200, 10), "lord: ",
                manor.getLord(), "name",this){
            @Override
            public void click(Coord point){
                getParent().getParent().addSpecialWindow("person info window", new PersonInfoWindow(manor.getLord(), getParent().getParent()));
            }
        };

        addWindowElement(label);

        MonitoredBroadcastLabel manorPopulationLabel = new MonitoredBroadcastLabel(new Coord(20, 280), new Coord(200, 10),
                "Population:", manor.getSociety(), "amount", this) {
            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("population group window", new PopulationInfoWindow(manor.getSociety(), frame));
            }
        };
        addWindowElement(manorPopulationLabel);



    }

    public void setManor(Manor manor){
        this.manor = manor;
        setElements();
    }
}
