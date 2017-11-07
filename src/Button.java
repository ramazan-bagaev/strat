import java.util.ArrayList;

abstract public class Button extends WindowElement{

    private StringShape text;
    private RectangleShape carcas;

    public Button(int x, int y, int sizeX, int sizeY, Window parent, String str){
        super(x, y, sizeX, sizeY, parent);
        text = new StringShape(x + 5, y + sizeY /2, str, BasicShape.Color.Black);
        carcas = new RectangleShape(x, y, sizeX, sizeY, BasicShape.Color.Black, false);

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

    public abstract void onClick();
}
