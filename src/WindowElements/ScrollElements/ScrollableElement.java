package WindowElements.ScrollElements;

import Utils.Coord;
import Foundation.Label;
import Foundation.BasicShapes.StringShape;
import Foundation.Window;

public abstract class ScrollableElement extends Label{

    protected String text;

    public ScrollableElement(Coord pos, Coord size, Window parent) {
        super(pos, size, "", parent);
    }

    public void setShapes(){
        StringShape stringShape = getStringShape();
        stringShape.setText(text);
        setStringShape(stringShape);
    }


    public boolean changed(){
        String newText = getNewText();
        if (!newText.equals(text)){
            text = newText;
            return true;
        }
        return false;
    }

    public void initText(){
        text = getNewText();
        setShapes();
    }

    @Override
    public void run(){
        if (changed()) setShapes();
    }


    public abstract String getNewText();
}
