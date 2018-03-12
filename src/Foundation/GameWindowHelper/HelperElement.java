package Foundation.GameWindowHelper;

import Foundation.BasicShape;

import java.util.ArrayList;

public abstract class HelperElement {

    private ArrayList<BasicShape> basicShapes;

    protected HelperField parentField;

    public HelperElement(HelperField helperField){
        basicShapes = new ArrayList<>();
        parentField = helperField;
    }

    public ArrayList<BasicShape> getBasicShapes(){
        return basicShapes;
    }

    public HelperField getParentField() {
        return parentField;
    }
}
