package Foundation.GameWindowHelper.Modes;

import Foundation.*;
import Foundation.GameWindowHelper.HelperElements.CoveringFieldHelper;
import Foundation.GameWindowHelper.HelperField;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class CoveringFieldMode extends Mode{

    private ArrayList<CoveringFieldHelper> coveringFieldHelpers;
    private ArrayList<Index> indices;

    public CoveringFieldMode(GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
    }

    @Override
    public void putHelpers() {
        coveringFieldHelpers = new ArrayList<>();
        indices = new ArrayList<>();
    }

    @Override
    public void removeHelpers() {
        for(CoveringFieldHelper coveringFieldHelper: coveringFieldHelpers){
            HelperField helperField = coveringFieldHelper.getParent();
            helperField.setCoveringFieldHelper(null);
            if (helperField.isEmpty()) helperField.delete();
        }
        coveringFieldHelpers.clear();
    }

    public void addCoveringFieldHelper(Index pos, Color color){
        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(pos);
        if (field == null) return;
        if (indices.contains(pos)) return;
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(pos);
        if (helperField == null){
            helperField = new HelperField(field, gameWindowHelperElement.getMap());
            gameWindowHelperElement.getMap().addByIndex(pos, helperField);
        }
        CoveringFieldHelper coveringFieldHelper = new CoveringFieldHelper(helperField, color);
        coveringFieldHelpers.add(coveringFieldHelper);
        helperField.setCoveringFieldHelper(coveringFieldHelper);
        gameWindowHelperElement.getGameWindowElement().setShapes();
        indices.add(pos);
    }

    public void removeCoveringFieldHelper(Index pos){
        if (!indices.contains(pos)) return;
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(pos);
        CoveringFieldHelper coveringFieldHelper = helperField.getCoveringFieldHelper();
        helperField.setCoveringFieldHelper(null);
        coveringFieldHelpers.remove(coveringFieldHelper);
        indices.remove(pos);
    }

    public boolean isOccupiedBy(Index pos){
        return indices.contains(pos);
    }
}
