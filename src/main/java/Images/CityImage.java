package Images;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class CityImage extends Image{

    public CityImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public CityImage(Coord pos, Window parent) {
        super(pos, parent);
        setShapes();
    }

    public void setShapes() {
        ArrayList<BasicShape> shapes = new ArrayList<>();
        Coord pos;
        Coord size;
        double sizeX = getSize().x;
        double sizeY = getSize().y;

        pos = new Coord(sizeX / 8, sizeY / 8);
        size = new Coord(sizeX / 4, sizeY / 4);
        RectangleShape shape1 = new RectangleShape(pos, size, new Color(Color.Type.Green));
        pos = new Coord(5 * sizeX / 8, sizeY / 8);
        size = new Coord(sizeX / 4, sizeY / 4);
        RectangleShape shape2 = new RectangleShape(pos, size, new Color(Color.Type.Red));
        pos = new Coord(sizeX / 8, 5 * sizeY / 8);
        size = new Coord(sizeX / 4, sizeY / 4);
        RectangleShape shape3 = new RectangleShape(pos, size, new Color(Color.Type.Blue));
        pos = new Coord(5 * sizeX / 8, 5 * sizeY / 8);
        size = new Coord(sizeX / 4, sizeY / 4);
        RectangleShape shape4 = new RectangleShape(pos, size, new Color(Color.Type.Yellow));
        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);

        setBasicShapes(shapes);
    }
}
