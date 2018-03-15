package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Foundation.GameWindowHelper.Modes.MegaBorderMode;

import java.util.ArrayList;

public class AddFarmState extends HelperState {

    private MegaBorderMode megaBorderMode;
    private CoveringFieldMode impossible;
    private CoveringFieldMode possible;

    private Manor manor;

    public AddFarmState(Manor manor, GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        this.manor = manor;
        impossible = new CoveringFieldMode(gameWindowHelperElement);
        possible = new CoveringFieldMode(gameWindowHelperElement);
        megaBorderMode = new MegaBorderMode(gameWindowHelperElement);
        impossible.putHelpers();
        possible.putHelpers();
        megaBorderMode.putHelpers();
        init();
    }

    public void init(){
        ArrayList<Coord> territory = manor.getTerritory();
        FieldMap fieldMap = gameWindowHelperElement.getMap().getFieldMap();
        Color p = new Color(Color.Type.White, 0.5f);
        Color i = new Color(Color.Type.Red, 0.5f);
        for(Coord pos: territory){
            Field field = fieldMap.getFieldByIndex(pos);
            if (field.getManor() != null){
                impossible.addCoveringFieldHelper(pos, i);
                continue;
            }
            if (field.getFarm() != null){
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
    public void click(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);
        point.x = point.x / gameWindowHelperElement.getMap().getFieldSize();
        point.y = point.y / gameWindowHelperElement.getMap().getFieldSize();
        if (impossible.isOccupiedBy(point)) return;
        if (possible.isOccupiedBy(point)){
            manor.createFarm(point);
            return;
        }
        clearHelperElements();
        gameWindowHelperElement.setStandartState();
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
