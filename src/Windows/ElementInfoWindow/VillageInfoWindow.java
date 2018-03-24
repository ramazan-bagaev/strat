package Windows.ElementInfoWindow;

import Foundation.BasicShapes.BasicShape;
import Foundation.Button;
import Utils.Coord;
import Foundation.Elements.Village;
import Foundation.Frame;
import Images.Image;
import WindowElements.MonitoredBroadcastLabel;
import Windows.ClosableWindow;
import Windows.IntermediatWindows.ChooseManyPeople.ChooseManorPeopleWindow;
import Windows.IntermediatWindows.ChooseManyPeople.ChooseVillagePeopleWindow;
import Windows.PopulationInfoWindow;
import Windows.ResourceStoreWindow;
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

        Button givePeople = new Button(new Coord(110, 20), new Coord(170, 20), "give people", this){

            @Override
            public void click(Coord point){
                getParent().getParent().addSpecialWindow("population group window",
                        new ChooseVillagePeopleWindow(village, getParent().getParent()));
            }

        };
        addWindowElement(givePeople);

        Button addWork = new Button(new Coord(10, 120), new Coord(150, 20),"add work", this) {


            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("work list window", new VillageWorkWindow(village, frame));
            }
        };

        addWindowElement(addWork);

        Button showResource = new Button(new Coord(20, 160), new Coord(200, 20),"show resources", this){

            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("resource store window", new ResourceStoreWindow(village.getResourceStore(), frame));
            }
        };

        addWindowElement(showResource);

        MonitoredBroadcastLabel manorPopulationLabel = new MonitoredBroadcastLabel(new Coord(20, 200), new Coord(200, 10),
                "Population:", village.getPeople(), "amount", this) {

            @Override
            public void click(Coord point) {
                Frame frame = getParent().getParent();
                frame.addSpecialWindow("population group window", new PopulationInfoWindow(village.getPeople(), frame));
            }
        };
        addWindowElement(manorPopulationLabel);



    }
}
