package Foundation.GameWindowHelper.States;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.GameWindowHelper.Modes.CoveringFieldMode;
import Foundation.Person.Person;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;


public class AddManorState extends HelperState {

    private CoveringFieldMode coveringFieldMode;
    private ArrayList<Index> possible;
    private ArrayList<Index> impossible;
    private City city;
    private Person lord;


    public AddManorState(GameWindowHelperElement gameWindowHelperElement, City city, Person lord) {
        super(gameWindowHelperElement);
        this.city = city;
        this.lord = lord;
        possible = new ArrayList<>();
        impossible = new ArrayList<>();
        coveringFieldMode = new CoveringFieldMode(gameWindowHelperElement);
        coveringFieldMode.putHelpers();
        init();
    }

    public void init(){
        FieldMap fieldMap = gameWindowHelperElement.getMap().getFieldMap();
        ArrayList<Manor> manors = city.getManors();
        ArrayList<Index> otherManors = new ArrayList<>();
        for (Manor manor: manors){
            otherManors.addAll(manor.getTerritory());
        }
        for(Index local: city.getTerritory())
        {
            if (otherManors.contains(local)) continue;
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
            coveringFieldMode.addCoveringFieldHelper(local, new Color(Color.Type.Blue, 0.5f));
            possible.add(local);
        }
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getMainWindow().getCameraConfiguration().transform(point);
        Index index = new Index(0, 0);
        index.x = (int) (point.x / gameWindowHelperElement.getMap().getFieldSize());
        index.y = (int) (point.y / gameWindowHelperElement.getMap().getFieldSize());
        if (impossible.contains(index)) return;
        if (possible.contains(index)) {
            city.createManor(index, lord);
        }
        gameWindowHelperElement.clearHelperElements();
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
        coveringFieldMode.removeHelpers();
    }

    @Override
    public void run() {

    }
}
