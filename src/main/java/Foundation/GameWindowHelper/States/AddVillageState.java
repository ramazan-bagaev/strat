package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;
import Foundation.Person.Person;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;
import WindowElements.GameWindowElements.GameWindowHelperElement;

public class AddVillageState extends HelperState {

    private MegaBorderMode megaBorderMode;
    private CoveringFieldMode impossible;
    private CoveringFieldMode possible;

    private Manor manor;
    private Person steward;

    public AddVillageState(Manor manor, Person steward, GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        this.manor = manor;
        this.steward = steward;
        impossible = new CoveringFieldMode(gameWindowHelperElement);
        possible = new CoveringFieldMode(gameWindowHelperElement);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
    }

    @Override
    public boolean isProperMode(){
        return (gameWindowHelperElement.getParent().getParent().getMode() == MainWindowCameraConfiguration.Mode.Normal);
    }

    public void init(){
        Territory territory = manor.getTerritory();
        FieldMap fieldMap = gameWindowHelperElement.getMap().getFieldMap();
        Color p = new Color(Color.Type.White, 0.5f);
        Color i = new Color(Color.Type.Red, 0.5f);
        for(Index pos: territory.getIndexArray()){
            Field field = fieldMap.getFieldByIndex(pos);
            if (field.getManor() != null){
                impossible.addCoveringFieldHelper(pos, i);
                continue;
            }
            if (field.getVillage() != null){
                impossible.addCoveringFieldHelper(pos, i);
                continue;
            }
            if (field.getTree() != null){
                impossible.addCoveringFieldHelper(pos, i);
                continue;
            }
            if (field.getCity() != null){
                impossible.addCoveringFieldHelper(pos, i);
                continue;
            }
            if (field.getGroundType() != Ground.GroundType.Soil){
                impossible.addCoveringFieldHelper(pos, i);
                continue;
            }
            possible.addCoveringFieldHelper(pos, p);
        }
    }

    @Override
    public void putHelperElements() {
        impossible.putHelpers();
        possible.putHelpers();
        megaBorderMode.putHelpers();
        init();
    }

    @Override
    public void click(Coord point) {
        if (!isProperMode()) return;
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        Index index = new Index(0 ,0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        if (impossible.isOccupiedBy(index)) return;
        if (possible.isOccupiedBy(index)){
            manor.createVillage(index, steward);
        }
        clearHelperElements();
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
        impossible.removeHelpers();
        possible.removeHelpers();
        megaBorderMode.removeHelpers();
    }

    @Override
    public void run() {

    }

}
