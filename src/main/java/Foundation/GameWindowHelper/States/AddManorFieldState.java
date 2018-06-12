package Foundation.GameWindowHelper.States;

import Foundation.Color;
import Foundation.MainWindowCameraConfiguration;
import Foundation.Territory;
import Utils.Geometry.Index;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;
import Foundation.GameWindowHelperElement;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class AddManorFieldState extends HelperState {

    private Manor manor;

    //private ArrayList<CoveringFieldHelper> farmTerritory;

    //private ArrayList<CoveringFieldHelper> possibleTerritory;

    private MegaBorderMode megaBorderMode;
    private CoveringFieldMode farmTerritory;
    private CoveringFieldMode possibleTerritory;

    private Territory maxTerritory;

    public AddManorFieldState(GameWindowHelperElement gameWindowHelperElement, Manor manor) {
        super(gameWindowHelperElement);
        farmTerritory = new CoveringFieldMode(gameWindowHelperElement);
        possibleTerritory = new CoveringFieldMode(gameWindowHelperElement);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
        this.manor = manor;
    }

    @Override
    public boolean isProperMode(){
        return (gameWindowHelperElement.getParent().getParent().getMode() == MainWindowCameraConfiguration.Mode.Normal);
    }

    @Override
    public void putHelperElements(){
        farmTerritory.putHelpers();
        possibleTerritory.putHelpers();
        megaBorderMode.putHelpers();
        initMaxTerritory();
        Territory currentTerritory = new Territory(manor.getTerritory());
        initFarmTerritory(currentTerritory);
    }

    private void initMaxTerritory(){
        maxTerritory = new Territory();
        ArrayList<Manor> manors = manor.getCity().getManors();
        Territory otherManors = new Territory();
        for (Manor man: manors) {
            if (man.equals(manor)) continue;
            otherManors.add(man.getTerritory());
        }
        Territory candidates = new Territory(manor.getCity().getTerritory());
        for (Index candidate: candidates.getTerritory()){
            if (otherManors.contains(candidate)) continue;
            maxTerritory.add(candidate);
        }
    }

    public void addFarmTerritory(Index pos){
        if (!possibleTerritory.isOccupiedBy(pos)) return;
        possibleTerritory.removeCoveringFieldHelper(pos);
        farmTerritory.addCoveringFieldHelper(pos, new Color(Color.Type.Black, 0.5f));
        manor.addTerritory(pos);
        refreshPossibleTerritory(pos);
    }

    public void initFarmTerritory(Territory currentTerritory){
        for (Index pos: currentTerritory.getTerritory()){
            farmTerritory.addCoveringFieldHelper(pos, new Color(Color.Type.Black, 0.5f));
            refreshPossibleTerritory(pos);
        }

    }

    public void refreshPossibleTerritory(Index pos){
        addPossibleTerritory(pos.add(new Index(0, 1)));
        addPossibleTerritory(pos.add(new Index(1, 0)));
        addPossibleTerritory(pos.add(new Index(-1, 0)));
        addPossibleTerritory(pos.add(new Index(0, -1)));
    }

    public void addPossibleTerritory(Index pos){
        if (farmTerritory.isOccupiedBy(pos)) return;
        if (!maxTerritory.contains(pos)) return;
        possibleTerritory.addCoveringFieldHelper(pos, new Color(Color.Type.White, 0.5f));
    }


    @Override
    public void click(Coord point) {
        if (!isProperMode()) return;
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        if (possibleTerritory.isOccupiedBy(index)){
            addFarmTerritory(index);
            return;
        }
        if (!farmTerritory.isOccupiedBy(index)){
            clearHelperElements();
            gameWindowHelperElement.setStandardState();
        }
    }

    @Override
    public void click2(Coord point) {

    }

    @Override
    public void hoover(Coord point) {

    }

    @Override
    public void clearHelperElements() {
        possibleTerritory.removeHelpers();
        farmTerritory.removeHelpers();
        megaBorderMode.removeHelpers();
    }

    @Override
    public void run() {

    }
}
