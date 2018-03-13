package Foundation.GameWindowHelper;

import Foundation.BasicShape;
import Foundation.Color;
import Foundation.Coord;
import Foundation.RectangleShape;

import java.util.ArrayList;

public class CoveringFieldHelper extends HelperElement {

    private Color color;

    public CoveringFieldHelper(HelperField helperField, Color color) {
        super(helperField);
        this.color = color;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        RectangleShape rectangleShape = new RectangleShape(new Coord(pos), new Coord(size), color, false, true);
        basicShapes.add(rectangleShape);
    }
}
