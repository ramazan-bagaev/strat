package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Ground;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;

import java.util.ArrayList;


public class AddWorkState extends HelperState {

    private CoveringFieldMode coveringFieldMode;
    private ArrayList<Coord> possible;
    private ArrayList<Coord> impossible;
    private City city;


    public AddWorkState(GameWindowHelperElement gameWindowHelperElement, City city) {
        super(gameWindowHelperElement);
        this.city = city;
        possible = new ArrayList<>();
        impossible = new ArrayList<>();
        coveringFieldMode = new CoveringFieldMode(gameWindowHelperElement);
        coveringFieldMode.putHelpers();
        init();
    }

    public void init(){
        FieldMap fieldMap = gameWindowHelperElement.getMap().getFieldMap();
        for(Coord local: city.getTerritory())
        {
            Field field = fieldMap.getFieldByIndex(local);
            if (field == null) continue;
            if (field.getCity() != null){
                impossible.add(local);
                continue;
            }
            if (field.getRiver() != null){
                coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                impossible.add(local);
                continue;
            }
            if (field.getTree() != null){
                coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                impossible.add(local);
                continue;
            }
            if (field.getGroundType() != Ground.GroundType.Soil){
                coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                impossible.add(local);
                continue;
            }
            coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Blue, 0.5f));
            possible.add(local);
        }
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);
        point.x = point.x / gameWindowHelperElement.getMap().getFieldSize();
        point.y = point.y / gameWindowHelperElement.getMap().getFieldSize();
        if (impossible.contains(point)) return;
        if (possible.contains(point)) {
            city.createManor(point);
        }
        gameWindowHelperElement.clearHelperElements();
        gameWindowHelperElement.setStandartState();
    }

    @Override
    public void click2(Coord point) {

    }

    @Override
    public void hoover(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);
        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
    }

    @Override
    public void clearHelperElements() {
        coveringFieldMode.removeHelpers();
    }

    @Override
    public void run() {

    }
}
