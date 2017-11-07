import java.util.ArrayList;

public class Label extends WindowElement{

    private StringShape stringShape;

    public Label(int x, int y, int sizeX, int sizeY, String text, Window parent){
        super(x, y, sizeX, sizeY, parent);
        setStringShape(new StringShape(x, y, text, BasicShape.Color.Black));
    }

    @Override
    public ArrayList<BasicShape> getShapes() {
        ArrayList<BasicShape> result = new ArrayList<>();
        result.add(getStringShape());
        return result;
    }

    public StringShape getStringShape() {
        return stringShape;
    }

    public void setStringShape(StringShape stringShape) {
        this.stringShape = stringShape;
    }
}
