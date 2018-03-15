package Foundation.GameWindowHelper.States;

import Foundation.Color;
import Foundation.Coord;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Foundation.GameWindowHelperElement;

import java.util.ArrayList;

public class AddManorFieldState extends HelperState {

    private Manor manor;

    //private ArrayList<CoveringFieldHelper> farmTerritory;

    //private ArrayList<CoveringFieldHelper> possibleTerritory;

    private CoveringFieldMode farmTerritory;
    private CoveringFieldMode possibleTerritory;

    private ArrayList<Coord> maxTerritory;

    public AddManorFieldState(GameWindowHelperElement gameWindowHelperElement, Manor manor) {
        super(gameWindowHelperElement);
        farmTerritory = new CoveringFieldMode(gameWindowHelperElement);
        possibleTerritory = new CoveringFieldMode(gameWindowHelperElement);
        farmTerritory.putHelpers();
        possibleTerritory.putHelpers();
        maxTerritory = manor.getCity().getTerritory();
        this.manor = manor;
        ArrayList<Coord> currentTerritory = manor.getTerritory();
        initFarmTerritory(currentTerritory);
    }

    public void addFarmTerritory(Coord pos){
        if (!possibleTerritory.isOccupiedBy(pos)) return;
        possibleTerritory.removeCoveringFieldHelper(pos);
        farmTerritory.addCoveringFieldHelper(pos, new Color(Color.Type.Black, 0.5f));
        manor.addTerritory(pos);
        refreshPossibleTerritory(pos);
    }

    public void initFarmTerritory(ArrayList<Coord> currentTerritory){
        for (Coord pos: currentTerritory){
            farmTerritory.addCoveringFieldHelper(pos, new Color(Color.Type.Black, 0.5f));
            refreshPossibleTerritory(pos);
        }

    }

    public void refreshPossibleTerritory(Coord pos){
        addPossibleTerritory(pos.add(new Coord(0, 1)));
        addPossibleTerritory(pos.add(new Coord(1, 0)));
        addPossibleTerritory(pos.add(new Coord(-1, 0)));
        addPossibleTerritory(pos.add(new Coord(0, -1)));
    }

    public void addPossibleTerritory(Coord pos){
        if (farmTerritory.isOccupiedBy(pos)) return;
        if (!maxTerritory.contains(pos)) return;
        possibleTerritory.addCoveringFieldHelper(pos, new Color(Color.Type.White, 0.5f));
    }


    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);
        point.x = point.x / gameWindowHelperElement.getMap().getFieldSize();
        point.y = point.y / gameWindowHelperElement.getMap().getFieldSize();
        if (possibleTerritory.isOccupiedBy(point)){
            addFarmTerritory(point);
            return;
        }
        if (!farmTerritory.isOccupiedBy(point)){
            clearHelperElements();
            gameWindowHelperElement.setStandartState();
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
    }
}
