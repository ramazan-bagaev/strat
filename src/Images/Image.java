package Images;

import Foundation.BasicShape;
import Foundation.Coord;
import Foundation.Window;
import Foundation.WindowElement;

import java.util.ArrayList;

public class Image extends WindowElement {

    public Image(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
    }

    public Image(Coord pos, Window parent) {
        super(pos, new Coord(50, 50), parent);
    }

    public Image(Image image1, Image image2, Coord pos, Window parent){
        super(pos, new Coord(50, 50), parent);
        ArrayList<BasicShape> shapes = image1.getBasicShapes();
        shapes.addAll(image2.getBasicShapes());
        setBasicShapes(shapes);
    }

    @Override
    public void click(Coord point) {
    }
}
