import java.util.ArrayList;

abstract public class Button extends WindowElement{

    private StringShape text;
    private RectangleShape carcas;

    public Button(Coord pos, Coord size, Window parent, String str){
        super(pos, size, parent);
        text = new StringShape(pos.x + 5, pos.y + size.y /2, str, BasicShape.Color.Black);
        carcas = new RectangleShape(pos.x, pos.y, size.x, size.y, BasicShape.Color.Black, false);

    }

    public StringShape getText() {
        return text;
    }

    public void setText(StringShape text) {
        this.text = text;
    }

    public RectangleShape getCarcas() {
        return carcas;
    }

    public void setCarcas(RectangleShape carcas) {
        this.carcas = carcas;
    }

    @Override
    public ArrayList<BasicShape> getShapes() {
        ArrayList<BasicShape> result = new ArrayList<>();
        result.add(text);
        result.add(carcas);
        return result;
    }
}
