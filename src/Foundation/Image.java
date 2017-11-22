package Foundation;

import java.util.ArrayList;

public class Image extends WindowElement{

    private ArrayList<BasicShape> basicShapes;

    public Image(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
    }

    public Image(Coord pos, Window parent) {
        super(pos, new Coord(50, 50), parent);
    }

    public Image(Image image1, Image image2, Coord pos, Window parent){
        super(pos, new Coord(50, 50), parent);
        ArrayList<BasicShape> shapes = image1.getShapes();
        shapes.addAll(image2.getShapes());
        setBasicShapes(shapes);
    }

    @Override
    public ArrayList<BasicShape> getShapes() {
        return basicShapes;
    }

    @Override
    public void click(Coord point) {

    }

    public void setBasicShapes(ArrayList<BasicShape> basicShapes) {
        this.basicShapes = basicShapes;
    }
}
