package Foundation.GameWindowHelper;

import Foundation.Coord;
import Foundation.*;

import java.util.ArrayList;


public class HelperField {

    private Coord globalPos;
    private Coord size;
    private HelperFieldMap map;
    private CityInfoHelper cityInfoHelper;


    public HelperField(Field field, HelperFieldMap map){
        this.map = map;
        globalPos = field.getFieldMapPos();
        size = new Coord(field.getSize(), field.getSize());
    }

    public HelperFieldMap getMap() {
        return map;
    }

    public ArrayList<BasicShape> getShapes(){
        ArrayList<BasicShape> result = new ArrayList<>();
        if (cityInfoHelper != null) result.addAll(cityInfoHelper.getBasicShapes());
        return result;
    }

    public void setCityInfoHelper(CityInfoHelper cityInfoHelper){
        this.cityInfoHelper = cityInfoHelper;
    }
}
