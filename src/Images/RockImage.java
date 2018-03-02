package Images;

import Foundation.*;

import java.util.ArrayList;

public class RockImage extends Image {

    public RockImage(Coord pos, Coord size, Rock.SizeType sizeType, Window parent) {
        super(pos, size, parent);
        setShapes(sizeType);
    }

    public RockImage(Coord pos, Rock.SizeType sizeType, Window parent) {
        super(pos, parent);
        setShapes(sizeType);
    }

    public void setShapes(Rock.SizeType sizeType){
        Coord pos;
        Coord size;
        int x = getPos().x;
        int y = getPos().y;
        int sizeX = getSize().x;
        int sizeY = getSize().y;
        ArrayList<BasicShape> shapes = new ArrayList<>();
        switch (sizeType){
            case Big:{
                pos = new Coord(x + sizeX/4, y + sizeY/4);
                size = new Coord(sizeX/2, sizeY/2);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Gray);
                pos = new Coord(x + sizeX/3, y + sizeY/3);
                size = new Coord(sizeX/3, sizeY/3);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.LightGray);

                shapes.add(shape1);
                shapes.add(shape2);
                break;
            }


            case Middle: {
                pos = new Coord(x + sizeX/3, y + sizeY/3);
                size = new Coord(sizeX/3, sizeY/3);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Gray);
                pos = new Coord(x + sizeX * 2/5, y + sizeY *2/5);
                size = new Coord(sizeX/5, sizeY/5);
                RectangleShape shape2 = new RectangleShape(pos, size, BasicShape.Color.LightGray);

                shapes.add(shape1);
                shapes.add(shape2);
                break;
            }
            case Small: {
                pos = new Coord(x + sizeX * 2/5, y + sizeY *2/5);
                size = new Coord(sizeX/5, sizeY/5);
                RectangleShape shape1 = new RectangleShape(pos, size, BasicShape.Color.Gray);
                shapes.add(shape1);
                break;
            }
        }
        setBasicShapes(shapes);
    }
}
