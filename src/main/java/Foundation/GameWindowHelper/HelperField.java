package Foundation.GameWindowHelper;

import Foundation.BasicShapes.BasicShape;
import Foundation.GameWindowHelper.FieldObjectHelperElements.InFieldHelperElement;
import Foundation.GameWindowHelper.HelperElements.*;
import Utils.Geometry.Index;
import Foundation.*;

import java.util.ArrayList;


public class HelperField {

    private Index pos;
    private Index size;
    private HelperFieldMap map;
    private Field field;

    private ArrayList<DynamicDrawable> dynamicDrawables;


    private CityInfoHelper cityInfoHelper;
    private ChosenFieldHelper chosenFieldHelper;
    private CoveringFieldHelper coveringFieldHelper;
    private ArrayList<BorderHelper> borderHelpers;
    private ArrayList<HelperElement> helperElements;


    private ArrayList<InFieldHelperElement> inFieldHelperElements;

    public HelperField(Field field, HelperFieldMap map){
        this.map = map;
        pos = field.getFieldMapPos();
        size = new Index(field.getSize(), field.getSize());
        borderHelpers = new ArrayList<>();
        helperElements = new ArrayList<>();
        inFieldHelperElements = new ArrayList<>();
        dynamicDrawables = new ArrayList<>();
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public HelperFieldMap getMap() {
        return map;
    }

    public ArrayList<BasicShape> getShapes(){
        ArrayList<BasicShape> result = new ArrayList<>();
        if (cityInfoHelper != null) result.addAll(cityInfoHelper.getBasicShapes());
        if (chosenFieldHelper != null) result.addAll(chosenFieldHelper.getBasicShapes());
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

    public void setChosenFieldHelper(ChosenFieldHelper chosenFieldHelper){
        this.chosenFieldHelper = chosenFieldHelper;
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

    public ArrayList<InFieldHelperElement> getInFieldHelperElements() {
        return inFieldHelperElements;
    }

    public void addInFieldHelperElement(InFieldHelperElement helperElement){
        inFieldHelperElements.add(helperElement);
    }

    public void removeInFieldHelperElement(InFieldHelperElement helperElement){
        inFieldHelperElements.remove(helperElement);
    }

    public boolean isEmpty(){
        if (chosenFieldHelper != null) return false;
        if (cityInfoHelper != null) return false;
        if (coveringFieldHelper != null) return false;
        if (borderHelpers.size() != 0) return false;
        if (helperElements.size() != 0) return false;
        if (inFieldHelperElements.size() != 0) return false;
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

    public void drawDynamicDrawable(OpenGLBinder openGLBinder){
        for(DynamicDrawable dynamicDrawable: dynamicDrawables) dynamicDrawable.draw(openGLBinder);
    }
}
