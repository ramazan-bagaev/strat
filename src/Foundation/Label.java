package Foundation;

import CharacterShape.Font;

import java.util.ArrayList;

public class Label extends WindowElement{

    private StringShape stringShape;

    public Label(Coord pos, Coord size, String text, Window parent){
        super(pos, size, parent);
        Font font = parent.getFont("latin");
        setStringShape(new StringShape(pos, size, text, BasicShape.Color.Black, font));
    }

    @Override
    public ArrayList<BasicShape> getShapes() {
        ArrayList<BasicShape> result = new ArrayList<>();
        result.addAll(getStringShape().getBasicShapes());
        return result;
    }

    @Override
    public void click() {
        return;
    }

    public StringShape getStringShape() {
        return stringShape;
    }

    public void setStringShape(StringShape stringShape) {
        this.stringShape = stringShape;
    }
}
