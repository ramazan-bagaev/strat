package Foundation.GameWindowHelper;

import Foundation.BasicShapes.BasicShape;
import Utils.Index;
import Foundation.*;
import Foundation.GameWindowHelper.HelperElements.BorderHelper;
import Foundation.GameWindowHelper.HelperElements.ChoosenFieldHelper;
import Foundation.GameWindowHelper.HelperElements.CityInfoHelper;
import Foundation.GameWindowHelper.HelperElements.CoveringFieldHelper;

import java.util.ArrayList;


public class HelperField {

    private Index pos;
    private Index size;
    private HelperFieldMap map;


    private CityInfoHelper cityInfoHelper;
    private ChoosenFieldHelper choosenFieldHelper;
    private CoveringFieldHelper coveringFieldHelper;
    private ArrayList<BorderHelper> borderHelpers;

    public HelperField(Field field, HelperFieldMap map){
        this.map = map;
        pos = field.getFieldMapPos();
        size = new Index(field.getSize(), field.getSize());
        borderHelpers = new ArrayList<>();
    }

    public HelperFieldMap getMap() {
        return map;
    }

    public ArrayList<BasicShape> getShapes(){
        ArrayList<BasicShape> result = new ArrayList<>();
        if (cityInfoHelper != null) result.addAll(cityInfoHelper.getBasicShapes());
        if (choosenFieldHelper != null) result.addAll(choosenFieldHelper.getBasicShapes());
        if (coveringFieldHelper != null) result.addAll(coveringFieldHelper.getBasicShapes());
        for(BorderHelper borderHelper: borderHelpers) {
            if (borderHelper != null) {
                result.addAll(borderHelper.getBasicShapes());
            }
        }
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

    public CoveringFieldHelper getCoveringFieldHelper() {
        return coveringFieldHelper;
    }

    public Index getSize() {
        return size;
    }

    public Index getPos() {
        return pos;
    }

    public boolean isEmpty(){
        if (choosenFieldHelper != null) return false;
        if (cityInfoHelper != null) return false;
        if (coveringFieldHelper != null) return false;
        if (borderHelpers.size() != 0) return false;
        return true;
    }

    public void delete(){
        getMap().addByIndex(pos, null);
    }


    public void addBorderHelper(BorderHelper borderHelper) {
        borderHelpers.add(borderHelper);
    }

    public void removeBorderHelper(BorderHelper borderHelper) {
        borderHelpers.remove(borderHelper);
    }
}
