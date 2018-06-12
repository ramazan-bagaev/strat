package Foundation.GameWindowHelper.Modes;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.GameWindowHelper.HelperElements.CityInfoHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.HelperFieldMap;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class CityInfoMode extends Mode {

    private ArrayList<CityInfoHelper> cityInfoHelpers;

    public CityInfoMode(GameWindowHelperElement gameWindowHelperElement) {
        super(gameWindowHelperElement);
        cityInfoHelpers = new ArrayList<>();
    }

    @Override
    public void putHelpers() {
        HelperFieldMap map = gameWindowHelperElement.getMap();
        FieldMap fieldMap = map.getFieldMap();
        for (Field field: fieldMap.getValues()){
            City city = field.getCity();
            if (city == null) continue;
            HelperField helperField = new HelperField(field, map);
            CityInfoHelper cityInfoHelper = new CityInfoHelper(city, helperField);
            cityInfoHelpers.add(cityInfoHelper);
            helperField.setCityInfoHelper(cityInfoHelper);
            Index pos = field.getFieldMapPos();
            map.addByIndex(pos, helperField);
        }
    }

    @Override
    public void removeHelpers() {
        for(CityInfoHelper cityInfoHelper: cityInfoHelpers){
            HelperField helperField = cityInfoHelper.getParent();
            helperField.setCityInfoHelper(null);
            if (cityInfoHelper.getParent().isEmpty()) helperField.delete();
        }
        cityInfoHelpers.clear();
    }
}
