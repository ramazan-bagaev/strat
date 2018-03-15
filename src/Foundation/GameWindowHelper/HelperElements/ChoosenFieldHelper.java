package Foundation.GameWindowHelper.HelperElements;

import Foundation.*;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.HelperFieldMap;

import java.util.ArrayList;

public class ChoosenFieldHelper extends HelperElement {


    public ChoosenFieldHelper(HelperField helperField) {
        super(helperField);
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        RectangleShape rectangleShape = new RectangleShape(new Coord(pos), new Coord(size), new Color(Color.Type.Red), false);
        basicShapes.add(rectangleShape);
        //getParentField().getMap().getParent().setShapes();
    }

    public void setNewPos(Coord pos) {
        if (getParentField().getMap().getFieldMap().getFieldByPos(pos) == null) return;
        if (pos.equals(this.pos)) return;
        HelperField helperField = parentField;
        helperField.setChoosenFieldHelper(null);
        if (helperField.isEmpty()){
            getParentField().getMap().addByPos(this.pos, null);
        }
        HelperFieldMap map = helperField.getMap();
        HelperField newHelperField = map.getFieldByPos(pos);
        if (newHelperField == null){
            newHelperField = new HelperField(map.getFieldMap().getFieldByPos(pos), map);
            map.addByPos(pos, newHelperField);
        }
        this.pos = new Coord((pos.x / size.x)*size.x, (pos.y / size.y)* size.y);
        this.parentField = newHelperField;
        newHelperField.setChoosenFieldHelper(this);
        setShapes();
        parentField.getMap().getParent().setShapes();
    }
}
