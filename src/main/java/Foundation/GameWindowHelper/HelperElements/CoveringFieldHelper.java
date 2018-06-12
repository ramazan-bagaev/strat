package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.Color;
import Foundation.GameWindowHelper.HelperField;
import Foundation.BasicShapes.RectangleShape;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class CoveringFieldHelper extends HelperElement {

    private Color color;

    public CoveringFieldHelper(HelperField helperField, Color color) {
        super(helperField);
        this.color = color;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        RectangleShape rectangleShape = new RectangleShape(new Coord(), new Coord(size.x, size.y), color, false, true);
        basicShapes.add(rectangleShape);
        setBasicShapes(basicShapes);
    }
}
