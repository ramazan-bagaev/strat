package WindowElements;

import Foundation.BasicShapes.CharacterShape.Font;
import Foundation.BasicShapes.StringShape;
import Foundation.Color;
import Utils.Geometry.Coord;
import WindowElementGroups.WindowElementGroup;
import WindowElements.WindowElement;
import Windows.Window;

public class Label extends WindowElement {

    private StringShape stringShape;

    public Label(Coord pos, Coord size, String text, Window parent){
        super(pos, size, parent);
        Font font = parent.getFont("latin");
        StringShape stringShape = new StringShape(new Coord(0, 0), size, text, new Color(Color.Type.Black), font);
        setStringShape(stringShape);
    }

    public Label(Coord pos, Coord size, String text, WindowElementGroup windowElementGroup){
        super(pos, size, windowElementGroup);
        Font font = getParent().getFont("latin");
        StringShape stringShape = new StringShape(new Coord(0, 0), size, text, new Color(Color.Type.Black), font);
        setStringShape(stringShape);
    }

    @Override
    public void click(Coord point) {
        return;
    }

    public StringShape getStringShape() {
        return stringShape;
    }

    public void setStringShape(StringShape stringShape) {
        this.stringShape = stringShape;
        setBasicShapes(stringShape.getBasicShapes());
    }

    /*@Override
    public void shift(Coord shift){
        super.shift(shift);
        stringShape.shift(shift);
    }*/

    @Override
    public void setShapes() {
        setBasicShapes(stringShape.getBasicShapes());
    }
}
