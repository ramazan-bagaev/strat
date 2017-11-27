package Foundation;

import java.util.ArrayList;

public class InputWindowElement extends WindowElement {

    private StringShape text;
    private String defaultText;

    public InputWindowElement(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        RectangleShape rectangleShape = new RectangleShape(pos, size, BasicShape.Color.LightGray, true);
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.add(rectangleShape);
        defaultText = "enter text";
        setText(new StringShape(pos, size, defaultText, BasicShape.Color.Black, getParent().getFont("latin")));
        basicShapes.addAll(text.getBasicShapes());
    }

    @Override
    public void click(Coord point) {
        renewText("");
        Windows windows = getParent().getParent();
        windows.setInputOn(true);
        windows.setCurrentInput(this);
    }

    public void keyPressed(char c){
        String str = text.getText();
        str = str + c;
        renewText(str);
    }

    public StringShape getText() {
        return text;
    }

    public void setNewText(String str) {
        this.text.setText(str);
        this.text.setSize(new Coord(str.length() * 15, getSize().y));
    }

    public void renewText(String str){
        setNewText(str);
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        basicShapes.add(new RectangleShape(getPos(), getSize(), BasicShape.Color.LightGray, true));
        basicShapes.addAll(text.getBasicShapes());
    }

    public void renewDefaultText() {
        renewText(defaultText);
    }

    public void setText(StringShape text) {
        this.text = text;
    }
}
