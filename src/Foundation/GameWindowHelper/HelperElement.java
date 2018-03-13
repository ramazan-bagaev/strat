package Foundation.GameWindowHelper;

import Foundation.BasicShape;
import Foundation.Coord;

import java.util.ArrayList;

public abstract class HelperElement {

    private ArrayList<BasicShape> basicShapes;

    protected HelperField parentField;
    protected Coord pos;
    protected Coord size;

    public HelperElement(HelperField helperField){
        basicShapes = new ArrayList<>();
        parentField = helperField;
        pos = new Coord(helperField.getPos());
        size = new Coord(helperField.getSize());
        pos.x = pos.x * size.x;
        pos.y = pos.y * size.y;
    }

    public ArrayList<BasicShape> getBasicShapes(){
        return basicShapes;
    }

    public HelperField getParentField() {
        return parentField;
    }
}
