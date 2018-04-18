package Foundation.GameWindowHelper.Modes;

import Foundation.Field;
import Foundation.GameWindowHelper.HelperElements.DirectionHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelperElement;
import Utils.Index;

import java.util.ArrayList;
import java.util.LinkedList;

public class DirectionMode extends Mode{

    protected LinkedList<Index> path;

    protected ArrayList<DirectionHelper> directionHelpers;

    public DirectionMode(GameWindowHelperElement gameWindowHelperElement,LinkedList<Index> path) {
        super(gameWindowHelperElement);
        this.path = path;
        directionHelpers = new ArrayList<>();
    }

    public void init(){
        if (path == null) return;
        if (path.size() < 2) return;
        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(path.get(0));
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(path.get(0));
        if (helperField == null) {
            helperField = new HelperField(field, gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(path.get(0), helperField);
        }
        DirectionHelper directionHelper = new DirectionHelper(helperField, Index.Direction.None, path.get(0).whatDirection(path.get(1)));
        helperField.addHelperElement(directionHelper);
        directionHelpers.add(directionHelper);


        for(int i = 1; i < path.size() - 1; i++){
            field = gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(path.get(i));
            helperField = gameWindowHelperElement.getMap().getFieldByIndex(path.get(i));
            if (helperField == null) {
                helperField = new HelperField(field, gameWindowHelperElement.getMap());
                helperField.getMap().addByIndex(path.get(i), helperField);
            }
            directionHelper = new DirectionHelper(helperField, path.get(i).whatDirection(path.get(i-1)), path.get(i).whatDirection(path.get(i+1)));
            helperField.addHelperElement(directionHelper);
            directionHelpers.add(directionHelper);
        }


        field = gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(path.get(path.size()-1));
        helperField = gameWindowHelperElement.getMap().getFieldByIndex(path.get(path.size()-1));
        if (helperField == null) {
            helperField = new HelperField(field, gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(path.get(path.size()-1), helperField);
        }
        directionHelper = new DirectionHelper(helperField, path.get(path.size()-1).whatDirection(path.get(path.size()-2)), Index.Direction.None);
        helperField.addHelperElement(directionHelper);
        directionHelpers.add(directionHelper);

    }

    @Override
    public void putHelpers() {
        init();
    }

    public void cleanHelpers(){
        for(DirectionHelper directionHelper: directionHelpers){
            HelperField helperField = directionHelper.getParent();
            helperField.removeHelperElement(directionHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        directionHelpers.clear();
    }

    @Override
    public void removeHelpers() {
        cleanHelpers();
    }
}
