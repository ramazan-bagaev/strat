package Foundation;

import java.util.ArrayList;

public class Image extends WindowElement{

    private ArrayList<BasicShape> basicShapes;

    public Image(Coord pos, Element.Type type, Window parent) {
        super(pos, new Coord(50, 50), parent);
    }

    @Override
    public ArrayList<BasicShape> getShapes() {
        return basicShapes;
    }

    @Override
    public void click() {

    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        this.basicShapes = basicShapes;
    }
}
