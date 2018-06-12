package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Elements.Village;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class AddMineState extends HelperState {
    private Village village;

    private CoveringFieldMode coveringFieldMode;

    private ArrayList<Index> possible;
    private ArrayList<Index> impossible;


    public AddMineState(Village village, GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        this.village = village;
        coveringFieldMode = new CoveringFieldMode(gameWindowHelperElement);
        possible = new ArrayList<>();
        impossible = new ArrayList<>();
    }

    @Override
    public boolean isProperMode(){
        return (gameWindowHelperElement.getParent().getParent().getMode() == MainWindowCameraConfiguration.Mode.Normal);
    }

    private void init(){
        FieldMap fieldMap = gameWindowHelperElement.getMap().getFieldMap();
        for(Index local: village.getManor().getTerritory().getTerritory())
        {
            Field field = fieldMap.getFieldByIndex(local);
            if (field == null) continue;
            if (field.getGroundType() != Ground.GroundType.Rock){
                coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                impossible.add(local);
                continue;
            }
            coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Blue, 0.5f));
            possible.add(local);
        }
    }


    @Override
    public void putHelperElements() {
        coveringFieldMode.putHelpers();
        init();
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        Index index = new Index(0 ,0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        if (impossible.contains(index)) return;
        if (possible.contains(index)) {
            village.createMine(index);
            return;
        }
        gameWindowHelperElement.clearHelperElements();
        gameWindowHelperElement.setStandardState();
    }

    @Override
    public void click2(Coord point) {

    }

    @Override
    public void hoover(Coord point) {

    }

    @Override
    public void clearHelperElements() {
        coveringFieldMode.removeHelpers();
    }

    @Override
    public void run() {

    }
}
