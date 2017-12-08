package WindowElements;

import Foundation.Coord;
import Foundation.Label;
import Foundation.StringShape;
import Foundation.Window;

import java.util.ArrayList;

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
