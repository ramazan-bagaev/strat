package Windows.ElementInfoWindow;

import Foundation.BasicShapes.BasicShape;
import WindowElements.Button;
import Foundation.GameWindowHelper.States.VillagePeopleDistributionState;
import WindowElements.GameWindowElements.GameWindowHelperElement;
import Utils.Geometry.Coord;
import Foundation.Elements.Village;
import Foundation.Frame;
import Images.Image;
import WindowElements.MonitoredBroadcastLabel;
import Windows.ClosableWindow;
import Windows.IntermediatWindows.ChooseManyPeople.ChooseVillagePeopleWindow;
import Windows.PopulationInfoWindow;
import Windows.WorkPlaceWindows.VillageWorkWindow;

import java.util.ArrayList;

public class VillageInfoWindow extends ClosableWindow{

    private Village village;

    public VillageInfoWindow(Village village, Frame parent) {
        super(new Coord(700, 0), new Coord(300, 400), parent);
        this.village = village;
        setElements();
    }

    public void setElements(){
        removeWindowElements();
        addCloseButton();

        ArrayList<BasicShape> shapes = village.getCopyOfBasicShapesWithoutShift();
        for (BasicShape basicShape: shapes){
            basicShape.changeSize(100/village.getParent().getSize());
        }
        Image villageImage = new Image(new Coord(0, 0), new Coord(100, 100),this, shapes);
        addWindowElement(villageImage);

        Button givePeople = new Button(new Coord(110, 20), new Coord(170, 20), "give society", this){

            @Override
            public void click(Coord point){
                getParent().getParent().addSpecialWindow("population group window",
                        new ChooseVillagePeopleWindow(village, getParent().getParent()));
            }

        };
        addWindowElement(givePeople);

        Button distr = new Button(new Coord(110, 60), new Coord(170, 20), "working distribution", this){

            @Override
            public void click(Coord point){
                GameWindowHelperElement gameWindowHelperElement = parent.getMainWindow().getGameWindowHelperElement();
                gameWindowHelperElement.clearHelperElements();
                VillagePeopleDistributionState distrState = new VillagePeopleDistributionState(village, gameWindowHelperElement);
                gameWindowHelperElement.setState(distrState);
            }
        };
        addWindowElement(distr);

        Button addWork = new Button(new Coord(10, 120), new Coord(150, 20),"add work", this) {


            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("work list window", new VillageWorkWindow(village, frame));
            }
        };

        addWindowElement(addWork);


        MonitoredBroadcastLabel manorPopulationLabel = new MonitoredBroadcastLabel(new Coord(20, 200), new Coord(200, 10),
                "Population:", village.getSociety(), "amount", this) {

            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("population group window", new PopulationInfoWindow(village.getSociety(), frame));
            }
        };
        addWindowElement(manorPopulationLabel);



    }
}
