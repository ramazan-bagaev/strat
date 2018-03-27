package Foundation.GameWindowHelper.States;

import Foundation.Elements.Village;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.VillagePeopleDistributionMode;
import Foundation.GameWindowHelperElement;
import Utils.Coord;

import java.util.ArrayList;

public class VillagePeopleDistributionState extends HelperState {

    private Coord clicked;

    private Village village;
    private VillagePeopleDistributionMode villagePeopleDistributionMode;

    public VillagePeopleDistributionState(Village village, GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        this.village = village;
        villagePeopleDistributionMode = new VillagePeopleDistributionMode(village, gameWindowHelperElement);
    }

    @Override
    public void putHelperElements() {
        villagePeopleDistributionMode.putHelpers();
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        if (gameWindowHelperElement.getMap().getFieldByPos(point) == null){
            gameWindowHelperElement.clearHelperElements();
            gameWindowHelperElement.setStandartState();
        }
    }

    @Override
    public boolean drag(Coord point, Coord pressedPos, boolean dragBegin){
        if (dragBegin){
            clicked = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(pressedPos);
        }
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        pressedPos = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(pressedPos);
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByPos(pressedPos);
        if (helperField == null) return false;
        double delta = point.y - clicked.y;
        boolean ch = villagePeopleDistributionMode.drag(delta, helperField);
        if (ch) clicked = new Coord(point);
        return true;
    }

    @Override
    public void click2(Coord point) {

    }

    @Override
    public void hoover(Coord point) {

    }

    @Override
    public void clearHelperElements() {
        villagePeopleDistributionMode.removeHelpers();
    }

    @Override
    public void run() {

    }
}
