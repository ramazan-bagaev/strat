package Foundation;

import CharacterShape.Font;

import java.util.ArrayList;

abstract public class Button extends WindowElement {

    private StringShape text;
    private RectangleShape carcas;

    public Button(Coord pos, Coord size, Window parent, String str){
        super(pos, size, parent);
        Font font = parent.getFont("latin");
        text = new StringShape(new Coord(pos.x + size.x/4, pos.y + size.y /4), new Coord(size.x/ 2, size.y /2), str, BasicShape.Color.Black, font);
        carcas = new RectangleShape(pos.x, pos.y, size.x, size.y, BasicShape.Color.White, true);

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
        result.add(carcas);
        result.addAll(text.getBasicShapes());
        return result;
    }
}
