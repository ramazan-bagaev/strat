package Foundation.GameWindowHelper;

import Foundation.Coord;
import Foundation.*;

import java.util.ArrayList;


public class HelperField {

    private Coord pos;
    private Coord size;
    private HelperFieldMap map;


    private CityInfoHelper cityInfoHelper;
    private ChoosenFieldHelper choosenFieldHelper;

    private CoveringFieldHelper coveringFieldHelper;

    public HelperField(Field field, HelperFieldMap map){
        this.map = map;
        pos = field.getFieldMapPos();
        size = new Coord(field.getSize(), field.getSize());
    }

    public HelperFieldMap getMap() {
        return map;
    }

    public ArrayList<BasicShape> getShapes(){
        ArrayList<BasicShape> result = new ArrayList<>();
        if (cityInfoHelper != null) result.addAll(cityInfoHelper.getBasicShapes());
        if (choosenFieldHelper != null) result.addAll(choosenFieldHelper.getBasicShapes());
        if (coveringFieldHelper != null) result.addAll(coveringFieldHelper.getBasicShapes());
        return result;
    }

    public void setCityInfoHelper(CityInfoHelper cityInfoHelper){
        this.cityInfoHelper = cityInfoHelper;
        getMap().getParent().setShapes();
    }

    public void setChoosenFieldHelper(ChoosenFieldHelper choosenFieldHelper){
        this.choosenFieldHelper = choosenFieldHelper;
        getMap().getParent().setShapes();
    }

    public void setCoveringFieldHelper(CoveringFieldHelper coveringFieldHelper) {
        this.coveringFieldHelper = coveringFieldHelper;
        getMap().getParent().setShapes();
    }

    public Coord getSize() {
        return size;
    }

    public Coord getPos() {
        return pos;
    }

    public boolean isEmpty(){
        if (choosenFieldHelper != null) return false;
        if (cityInfoHelper != null) return false;
        if (coveringFieldHelper != null) return false;
        return true;
    }
}
