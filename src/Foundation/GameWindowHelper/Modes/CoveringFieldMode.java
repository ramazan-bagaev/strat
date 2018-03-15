package Foundation.GameWindowHelper.Modes;

import Foundation.*;
import Foundation.GameWindowHelper.HelperElements.CoveringFieldHelper;
import Foundation.GameWindowHelper.HelperField;

import java.util.ArrayList;

public class CoveringFieldMode extends Mode{

    private ArrayList<CoveringFieldHelper> coveringFieldHelpers;
    private ArrayList<Coord> coords;

    public CoveringFieldMode(GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
    }

    @Override
    public void putHelpers() {
        coveringFieldHelpers = new ArrayList<>();
        coords = new ArrayList<>();
    }

    @Override
    public void removeHelpers() {
        for(CoveringFieldHelper coveringFieldHelper: coveringFieldHelpers){
            HelperField helperField = coveringFieldHelper.getParentField();
            helperField.setCoveringFieldHelper(null);
            if (helperField.isEmpty()) helperField.delete();
        }
        coveringFieldHelpers.clear();
    }

    public void addCoveringFieldHelper(Coord pos, Color color){
        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(pos);
        if (field == null) return;
        if (coords.contains(pos)) return;
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(pos);
        if (helperField == null){
            helperField = new HelperField(field, gameWindowHelperElement.getMap());
            gameWindowHelperElement.getMap().addByIndex(pos, helperField);
        }
        CoveringFieldHelper coveringFieldHelper = new CoveringFieldHelper(helperField, color);
        coveringFieldHelpers.add(coveringFieldHelper);
        helperField.setCoveringFieldHelper(coveringFieldHelper);
        gameWindowHelperElement.getGameWindowElement().setShapes();
        coords.add(pos);
    }

    public void removeCoveringFieldHelper(Coord pos){
        if (!coords.contains(pos)) return;
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(pos);
        CoveringFieldHelper coveringFieldHelper = helperField.getCoveringFieldHelper();
        helperField.setCoveringFieldHelper(null);
        coveringFieldHelpers.remove(coveringFieldHelper);
        coords.remove(pos);
    }

    public boolean isOccupiedBy(Coord pos){
        return coords.contains(pos);
    }
}
