package Foundation;

import CharacterShape.CharacterShape;
import CharacterShape.Font;

import java.util.ArrayList;

public class StringShape{

    private Font font;
    private String text;
    private Coord pos;
    private Coord size;
    private BasicShape.Color color;

    public StringShape(Coord pos, Coord size, String text, BasicShape.Color color, Font font){
        setPos(pos);
        setSize(size);
        setText(text);
        setFont(font);
        setColor(color);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.toUpperCase();
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Coord getPos() {
        return pos;
    }

    public void setPos(Coord pos) {
        this.pos = pos;
    }

    public Coord getSize() {
        return size;
    }

    public void setSize(Coord size) {
        this.size = size;
    }

    public ArrayList<BasicShape> getBasicShapes(){
        ArrayList<BasicShape> result = new ArrayList<>();
        int len = text.length();
        for (int i = 0; i < text.length(); i++){
            CharacterShape newShape = font.getCharacterShapeByDef(String.valueOf(text.charAt(i)));
            if (newShape == null) continue;
            Coord newPos = new Coord(getPos().x + i * (getSize().x/len), getPos().y);
            Coord newSize = new Coord(getSize().x / len, getSize().y);
            newShape.setPos(newPos);
            newShape.setSize(newSize);
            newShape.setColor(color);
            result.add(newShape);
        }
        return result;
    }

    public void setColor(BasicShape.Color color) {
        this.color = color;
    }
}
