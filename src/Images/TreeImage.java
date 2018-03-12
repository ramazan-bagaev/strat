package Images;

import Foundation.*;

import java.util.ArrayList;

public class TreeImage extends Image {

    public TreeImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);
        setShapes();
    }

    public TreeImage(Coord pos, Window parent) {
        super(pos, parent);
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> treeShapes = new ArrayList<>();
        RectangleShape trunk;
        RectangleShape foliage;
        int trunkX;
        int trunkY;
        int foliageX;
        int foliageY;
        int foliageHeight;
        int foliageWidth;
        int trunkHeight;
        int trunkWidth;
        int x = getPos().x;
        int y = getPos().y;
        int height = getSize().y;
        int width = getSize().x;

        foliageHeight = height / 2;
        foliageWidth = width * 3 / 4;
        trunkHeight = height / 2;
        trunkWidth = width / 4;
        foliageX = x + width / 8;
        foliageY = y;
        trunkX = x + width * 3 / 8;
        trunkY = foliageY + foliageHeight;
        trunk = new RectangleShape(new Coord(trunkX, trunkY), new Coord(trunkWidth, trunkHeight), BasicShape.Color.Red, true);
        foliage = new RectangleShape(new Coord(foliageX, foliageY), new Coord(foliageWidth, foliageHeight), BasicShape.Color.Green2, true);
        treeShapes.add(trunk);
        treeShapes.add(foliage);

        setBasicShapes(treeShapes);
    }

}
