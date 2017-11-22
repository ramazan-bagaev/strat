package Images;

import Foundation.*;

import java.util.ArrayList;

public class CityImage extends Image{

    public CityImage(Coord pos, Coord size, City.SizeType sizeType, Window parent) {
        super(pos, size, parent);
        setShapes(sizeType);
    }

    public CityImage(Coord pos, City.SizeType sizeType, Window parent) {
        super(pos, parent);
        setShapes(sizeType);
    }

    private void setShapes(City.SizeType sizeType) {
        ArrayList<BasicShape> shapes = new ArrayList<>();
        Coord pos;
        Coord size;
        int x = getPos().x;
        int y = getPos().y;
        int sizeX = getSize().x;
        int sizeY = getSize().y;
        switch (sizeType) {
            case Big: {
                pos = new Coord(x + sizeX / 8, y + sizeY / 8);
                size = new Coord(sizeX / 4, sizeY / 4);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Green);
                pos = new Coord(x + 5 * sizeX / 8, y + sizeY / 8);
                size = new Coord(sizeX / 4, sizeY / 4);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.Red);
                pos = new Coord(x + sizeX / 8, y + 5 * sizeY / 8);
                size = new Coord(sizeX / 4, sizeY / 4);
                RectangleShape shape3 = new RectangleShape(pos, size, BasicShape.Color.Blue);
                pos = new Coord(x + 5 * sizeX / 8, y + 5 * sizeY / 8);
                size = new Coord(sizeX / 4, sizeY / 4);
                RectangleShape shape4 = new RectangleShape(pos, size, BasicShape.Color.Yellow);
                shapes.add(shape1);
                shapes.add(shape2);
                shapes.add(shape3);
                shapes.add(shape4);
                break;
            }
            case Middle: {
                pos = new Coord(x + sizeX / 8, y + 3 * sizeY / 8);
                size = new Coord(sizeX / 4, sizeY / 4);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Green);
                pos = new Coord(x + 5 * sizeX / 8, y + 3 * sizeY / 8);
                size = new Coord(sizeX / 4, sizeY / 4);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.Red);
                shapes.add(shape1);
                shapes.add(shape2);
                break;
            }
            case Small: {
                pos = new Coord(x + 3 * sizeX / 8, y + 3 * sizeY / 8);
                size = new Coord(sizeX / 4, sizeY / 4);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Red);
                shapes.add(shape1);
                break;
            }
        }
        setBasicShapes(shapes);
    }
}
