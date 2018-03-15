package Foundation.GameWindowHelper.Modes;

import Foundation.Color;
import Foundation.Coord;
import Foundation.Field;
import Foundation.GameWindowHelper.HelperElements.BorderHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelperElement;

import java.util.ArrayList;

public class BorderMode extends Mode{

    private ArrayList<Coord> territory;
    private Color color;
    private float width;

    private ArrayList<BorderHelper> borderHelpers;

    public BorderMode(GameWindowHelperElement gameWindowHelperElement, ArrayList<Coord> territory, Color color, float width) {
        super(gameWindowHelperElement);
        this.territory = territory;
        this.color = color;
        this.width = width;
        borderHelpers = new ArrayList<>();
    }

    @Override
    public void putHelpers() {
        for(Coord pos: territory){
            ArrayList<Coord.Direction> directions = new ArrayList<>();
            if (!territory.contains(pos.add(new Coord(0, 1)))) directions.add(Coord.Direction.Down);
            if (!territory.contains(pos.add(new Coord(0, -1)))) directions.add(Coord.Direction.Up);
            if (!territory.contains(pos.add(new Coord(1, 0)))) directions.add(Coord.Direction.Right);
            if (!territory.contains(pos.add(new Coord(-1, 0)))) directions.add(Coord.Direction.Left);
            if (directions.size() == 0) continue;

            Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(pos);
            if (field == null){
                continue;
            }
            HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(pos);
            if (helperField == null) {
                helperField = new HelperField(field,
                        gameWindowHelperElement.getMap());
                helperField.getMap().addByIndex(pos, helperField);
            }
            BorderHelper borderHelper = new BorderHelper(helperField, directions, color, width);
            helperField.addBorderHelper(borderHelper);
            borderHelpers.add(borderHelper);
        }
    }

    @Override
    public void removeHelpers() {
        for(BorderHelper borderHelper: borderHelpers){
            HelperField helperField = borderHelper.getParentField();
            helperField.removeBorderHelper(borderHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        borderHelpers.clear();
    }
}
