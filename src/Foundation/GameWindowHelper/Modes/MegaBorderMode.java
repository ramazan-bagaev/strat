package Foundation.GameWindowHelper.Modes;

import Foundation.Color;
import Foundation.Elements.City;
import Foundation.Elements.Manor;
import Foundation.FieldMap;
import Foundation.Field;
import Foundation.GameWindowHelperElement;

import java.util.ArrayList;
import java.util.Random;

public class MegaBorderMode extends Mode {

    private ArrayList<BorderMode> borderModes;

    public MegaBorderMode(GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        borderModes = new ArrayList<>();
    }

    public void init(){
        FieldMap fieldMap = gameWindowHelperElement.getMap().getFieldMap();
        for (Field field: fieldMap.getValues()){
            City city = field.getCity();
            if (city == null) continue;
            Random random = field.getRandom();
            Color color = new Color(random.nextInt(256)/256f, random.nextInt(256)/256f, random.nextInt(256)/256f);
            BorderMode borderMode = new BorderMode(gameWindowHelperElement, city.getTerritory(), color, 4);

            borderModes.add(borderMode);
            ArrayList<Manor> manors = city.getManors();
            for(Manor manor : manors){
                borderMode = new BorderMode(gameWindowHelperElement, manor.getTerritory(), color,0.5f);
                borderModes.add(borderMode);
            }
        }
    }


    @Override
    public void putHelpers() {
        init();
        for(BorderMode borderMode: borderModes){
            borderMode.putHelpers();
        }
    }

    @Override
    public void removeHelpers() {
        for(BorderMode borderMode: borderModes){
            borderMode.removeHelpers();
        }
        borderModes.clear();
    }
}
