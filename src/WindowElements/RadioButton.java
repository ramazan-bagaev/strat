package WindowElements;

import Foundation.*;

import java.util.ArrayList;

public abstract class RadioButton extends WindowElement {

    private boolean checked;

    public RadioButton(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
        checked = false;
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        RectangleShape carcas = new RectangleShape(new Coord(0, 0), getSize(), new Color(Color.Type.Black), false);
        addBasicShape(carcas);
        RectangleShape point = new RectangleShape(new Coord(getSize().x / 4, getSize().y / 4),
                new Coord(getSize().x / 2, getSize().y / 2), new Color(Color.Type.Black), true);
        if (checked) addBasicShape(point);
    }

    public void check(){
        if (checked == false) {
            checked = true;
            setShapes();
            onCheck();
        }
    }

    public void uncheck(){
        checked = false;
        setShapes();
    }

    @Override
    public void click(Coord point){
        check();
    }

    public abstract void onCheck();

}
