package Foundation;

import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.StringShape;
import Utils.Geometry.Coord;

public class InputWindowElement extends WindowElement {

    private StringShape text;
    private String defaultText;
    private boolean firstClick;

    private Keyboard keyboard;

    public InputWindowElement(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        firstClick = true;
        keyboard = getParent().getParent().getInput().getKeyboard();
        setShapes();
    }

    @Override
    public void click(Coord point) {
        if (firstClick){
            renewText("");
            firstClick = false;
        }
        keyboard.setActiveElement(this);
    }

    @Override
    public void characterInput(char c){
        String str = text.getText();
        str = str + c;
        renewText(str);
    }

    @Override
    public void setShapes() {
        RectangleShape rectangleShape = new RectangleShape(new Coord(0, 0), size, new Color(Color.Type.LightGray), true);
        addBasicShape(rectangleShape);
        defaultText = "enter text";
        setText(new StringShape(new Coord(0, 0), size, defaultText, new Color(Color.Type.Black), getParent().getFont("latin")));
        addBasicShapes(text.getBasicShapes());
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
        clearBasicShapes();
        addBasicShape(new RectangleShape(new Coord(0, 0), getSize(), new Color(Color.Type.LightGray), true));
        addBasicShapes(text.getBasicShapes());
    }

    public void renewDefaultText() {
        renewText(defaultText);
    }

    public void setText(StringShape text) {
        this.text = text;
    }
}
