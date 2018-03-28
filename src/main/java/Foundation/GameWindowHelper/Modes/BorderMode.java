package Foundation.GameWindowHelper.Modes;

import Foundation.Color;
import Foundation.Territory;
import Utils.Index;
import Foundation.Field;
import Foundation.GameWindowHelper.HelperElements.BorderHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelperElement;
import Utils.Subscription;
import Utils.TimeMeasurer;

import java.util.ArrayList;

public class BorderMode extends Mode{

    private Subscription territorySubscription;

    private Territory territory;
    private Color color;
    private float width;

    private ArrayList<BorderHelper> borderHelpers;

    public BorderMode(GameWindowHelperElement gameWindowHelperElement, Territory territory, Color color, float width) {
        super(gameWindowHelperElement);
        this.territory = territory;
        this.color = color;
        this.width = width;

    }

    public void init(){
        for(Index pos: territory.getTerritory()){
            ArrayList<Index.Direction> directions = new ArrayList<>();
            if (!territory.contains(pos.add(new Index(0, 1)))) directions.add(Index.Direction.Down);
            if (!territory.contains(pos.add(new Index(0, -1)))) directions.add(Index.Direction.Up);
            if (!territory.contains(pos.add(new Index(1, 0)))) directions.add(Index.Direction.Right);
            if (!territory.contains(pos.add(new Index(-1, 0)))) directions.add(Index.Direction.Left);
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
    public void putHelpers() {
        borderHelpers = new ArrayList<>();
        territorySubscription = new Subscription() {
            @Override
            public void changed() {
                renewHelpers();
            }
        };
        territory.subscribe("territory", territorySubscription);
        init();
    }


    public void renewHelpers(){
        TimeMeasurer timeMeasurer = new TimeMeasurer();
        timeMeasurer.start("renew border");
        for(BorderHelper borderHelper: borderHelpers){
            HelperField helperField = borderHelper.getParent();
            helperField.removeBorderHelper(borderHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        borderHelpers.clear();
        init();
        timeMeasurer.stop(5);
    }

    @Override
    public void removeHelpers() {
        for(BorderHelper borderHelper: borderHelpers){
            HelperField helperField = borderHelper.getParent();
            helperField.removeBorderHelper(borderHelper);
            if (helperField.isEmpty()) helperField.delete();
        }
        borderHelpers.clear();
        territory.unsubscribe("territory", territorySubscription);
    }
}
