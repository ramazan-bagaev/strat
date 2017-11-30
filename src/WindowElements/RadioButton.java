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
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        RectangleShape carcas = new RectangleShape(getPos(), getSize(), BasicShape.Color.Black, false);
        basicShapes.add(carcas);
        RectangleShape point = new RectangleShape(getPos().add(new Coord(getSize().x / 4, getSize().y / 4)),
                new Coord(getSize().x / 2, getSize().y / 2), BasicShape.Color.Black, true);
        if (checked) basicShapes.add(point);
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
