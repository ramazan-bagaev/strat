package Images;

import Foundation.BasicShapes.BasicShape;
import Utils.Geometry.Coord;
import WindowElementGroups.WindowElementGroup;
import Windows.Window;
import WindowElements.WindowElement;

import java.util.ArrayList;

public class Image extends WindowElement {

    public Image(Coord pos, Coord size, WindowElementGroup group){
        super(pos, size, group);
    }

    public Image(Coord pos, Coord size, Window parent){
        super(pos, size, parent);
    }

    public Image(Coord pos, Window parent) {
        super(pos, new Coord(50, 50), parent);
    }

    public Image(Coord pos, Coord size){
        super(pos, size);
    }

    public void add(Image image){
        ArrayList<BasicShape> imageShape = image.getBasicShapesRemoveAndShiftBack();
        addBasicShapes(imageShape);
    }

    public Image(Coord pos, Coord size, Window parent, ArrayList<BasicShape> basicShapes){
        super(pos, size, parent);
        addBasicShapes(basicShapes);
    }

}
