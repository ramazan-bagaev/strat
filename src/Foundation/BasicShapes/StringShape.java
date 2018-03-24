package Foundation.BasicShapes;

import Foundation.BasicShapes.CharacterShape.CharacterShape;
import Foundation.BasicShapes.CharacterShape.Font;
import Foundation.Color;
import Utils.Coord;

import java.util.ArrayList;

public class StringShape extends BasicShape {

    private Font font;
    private String text;
    private Coord pos;
    private Coord size;
    private Color color;

    public StringShape(Coord pos, Coord size, String text, Color color, Font font){
        this.pos = new Coord(pos);
        this.size = new Coord(size);
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
        for (int i = 0; i < text.length(); i++){
            CharacterShape newShape = font.getCharacterShapeByDef(String.valueOf(text.charAt(i)));
            if (newShape == null) continue;
            if ((i+1)*getSize().y > size.x)  return result;
            Coord newPos = new Coord(getPos().x + i * getSize().y, getPos().y);
            Coord newSize = new Coord(getSize().y, getSize().y);
            newShape.setPos(newPos);
            newShape.setSize(newSize);
            newShape.setColor(color);
            result.add(newShape);
        }
        return result;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void shift(Coord shift){
        pos = pos.add(shift);
    }

    @Override
    public void changeSize(double alpha) {
        pos = pos.multiply(alpha);
        size = size.multiply(alpha);
    }
}
