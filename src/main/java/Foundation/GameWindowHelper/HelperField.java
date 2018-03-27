package Foundation.GameWindowHelper;

import Foundation.BasicShapes.BasicShape;
import Foundation.GameWindowHelper.HelperElements.*;
import Utils.Index;
import Foundation.*;

import java.util.ArrayList;


public class HelperField {

    private Index pos;
    private Index size;
    private HelperFieldMap map;


    private CityInfoHelper cityInfoHelper;
    private ChoosenFieldHelper choosenFieldHelper;
    private CoveringFieldHelper coveringFieldHelper;
    private ArrayList<BorderHelper> borderHelpers;
    private ArrayList<HelperElement> helperElements;

    public HelperField(Field field, HelperFieldMap map){
        this.map = map;
        pos = field.getFieldMapPos();
        size = new Index(field.getSize(), field.getSize());
        borderHelpers = new ArrayList<>();
        helperElements = new ArrayList<>();
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
        for (HelperElement helperElement: helperElements){
            result.addAll(helperElement.getBasicShapes());
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
        if (helperElements.size() != 0) return false;
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

    public void addHelperElement(HelperElement helperElement){
        helperElements.add(helperElement);
    }

    public void removeHelperElement(HelperElement helperElement){
        helperElements.remove(helperElement);
    }
}
