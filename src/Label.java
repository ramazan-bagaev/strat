import java.util.ArrayList;

public class Label extends WindowElement{

    private StringShape stringShape;

    public Label(Coord pos, Coord size, String text, Window parent){
        super(pos, size, parent);
        setStringShape(new StringShape(pos.x, pos.y, text, BasicShape.Color.Black));
    }

    @Override
    public ArrayList<BasicShape> getShapes() {
        ArrayList<BasicShape> result = new ArrayList<>();
        result.add(getStringShape());
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
