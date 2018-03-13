package Foundation.GameWindowHelper;

import Foundation.*;

import java.util.ArrayList;

public class StandartState extends HelperState {

    private ArrayList<CityInfoHelper> cityInfoHelpers;

    private ChoosenFieldHelper choosenFieldHelper;


    public StandartState(GameWindowHelperElement gameWindowHelperElement){
        super(gameWindowHelperElement);
        cityInfoHelpers = new ArrayList<>();
        addCityInfo();
        addChoosenField(new Coord(0, 0));
    }

    private void addCityInfo(){
        HelperFieldMap map = gameWindowHelperElement.getMap();
        FieldMap fieldMap = map.getFieldMap();
        for (Field field: fieldMap.getValues()){
            City city = field.getCity();
            if (city == null) continue;
            HelperField helperField = new HelperField(field, map);
            CityInfoHelper cityInfoHelper = new CityInfoHelper(city, helperField);
            cityInfoHelpers.add(cityInfoHelper);
            helperField.setCityInfoHelper(cityInfoHelper);
            Coord pos = field.getFieldMapPos();
            map.addByIndex(pos, helperField);
        }
    }

    private void addChoosenField(Coord point){
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByPos(point);
        if (helperField == null){
            helperField = new HelperField(gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point), gameWindowHelperElement.getMap());
            helperField.getMap().addByPos(point, helperField);
        }
        choosenFieldHelper = new ChoosenFieldHelper(helperField);
        helperField.setChoosenFieldHelper(choosenFieldHelper);
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);

        choosenFieldHelper.setNewPos(point);

        gameWindowHelperElement.getGameWindowElement().click(point);
    }

    @Override
    public void click2(Coord point) {

    }

    @Override
    public void hoover(Coord point) {

    }

    @Override
    public void clearHelperElements() {
        for(CityInfoHelper cityInfoHelper: cityInfoHelpers){
            cityInfoHelper.getParentField().setCityInfoHelper(null);
        }
        cityInfoHelpers.clear();

        choosenFieldHelper.getParentField().setChoosenFieldHelper(null);
        choosenFieldHelper = null;
    }
}
