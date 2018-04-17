package Foundation.GameWindowHelper.HelperElements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.HelperFieldMap;
import Utils.Index;
import Utils.Coord;

import java.util.ArrayList;

public class ChoosenFieldHelper extends HelperElement {

    private Index pos;
    private Color color;

    public ChoosenFieldHelper(Index pos, HelperField helperField) {
        super(helperField);
        this.pos = pos;
        color = new Color(Color.Type.Red);
        setShapes();
    }

    public ChoosenFieldHelper(Index pos, Color color, HelperField helperField){
        super(helperField);
        this.pos = pos;
        this.color = color;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        basicShapes.clear();
        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(size.x, size.y), color, false);
        basicShapes.add(rectangleShape);
        setBasicShapes(basicShapes);
        //getParentField().getMap().getParent().setShapes();
    }

    public void setNewPos(Index pos) {
        if (getParent().getMap().getFieldMap().getFieldByIndex(pos) == null) return;
        if (pos.equals(this.pos)) return;
        HelperField helperField = parent;
        helperField.setChoosenFieldHelper(null);
        if (helperField.isEmpty()){
            getParent().getMap().addByIndex(this.pos, null);
        }
        HelperFieldMap map = helperField.getMap();
        HelperField newHelperField = map.getFieldByIndex(pos);
        if (newHelperField == null){
            newHelperField = new HelperField(map.getFieldMap().getFieldByIndex(pos), map);
            map.addByIndex(pos, newHelperField);
        }
        this.pos = new Index(pos);
        this.parent = newHelperField;
        newHelperField.setChoosenFieldHelper(this);
        setShapes();
        parent.getMap().getParent().setShapes();
    }
}
