package Images;

import Foundation.*;

import java.util.ArrayList;

public class TreeImage extends Image {

    public TreeImage(Coord pos, Coord size, Tree.SizeType sizeType, Window parent) {
        super(pos, size, parent);
        setShapes(sizeType);
    }

    public TreeImage(Coord pos, Tree.SizeType sizeType, Window parent) {
        super(pos, parent);
        setShapes(sizeType);
    }

    public void setShapes(Tree.SizeType sizeType){
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
        switch (sizeType){
            case Big:
                foliageHeight = height / 2;
                foliageWidth = width * 3 / 4;
                trunkHeight = height / 2;
                trunkWidth = width / 4;
                foliageX = x + width / 8;
                foliageY = y;
                trunkX = x + width * 3 / 8;
                trunkY = foliageY + foliageHeight;
                trunk = new RectangleShape(new Coord(trunkX, trunkY), new Coord(trunkWidth, trunkHeight), BasicShape.Color.Red, true);
                foliage = new RectangleShape(new Coord(foliageX, foliageY), new Coord(foliageWidth, foliageHeight), BasicShape.Color.Green, true);
                treeShapes.add(trunk);
                treeShapes.add(foliage);
                break;
            case Middle:
                foliageHeight = height * 3 / 8;
                foliageWidth = width / 2;
                trunkHeight = height / 4;
                trunkWidth = width / 5;
                trunkX = x + width * 2 / 5;
                trunkY = y + height - trunkHeight;
                foliageX = x + width / 4;
                foliageY = trunkY - foliageHeight;
                trunk = new RectangleShape(new Coord(trunkX, trunkY), new Coord(trunkWidth, trunkHeight), BasicShape.Color.Red);
                foliage = new RectangleShape(new Coord(foliageX, foliageY), new Coord(foliageWidth, foliageHeight), BasicShape.Color.Green);
                treeShapes.add(trunk);
                treeShapes.add(foliage);
                break;
            case Small:
                foliageHeight = height / 4;
                foliageWidth = width / 4;
                trunkHeight = height / 8;
                trunkWidth = width / 7;
                trunkX = x + width * 5 / 12;
                trunkY = y + height - trunkHeight;
                foliageX = x + width * 3 / 8;
                foliageY = trunkY - foliageHeight;
                trunk = new RectangleShape(new Coord(trunkX, trunkY), new Coord(trunkWidth, trunkHeight), BasicShape.Color.Red);
                foliage = new RectangleShape(new Coord(foliageX, foliageY), new Coord(foliageWidth, foliageHeight), BasicShape.Color.Green);
                treeShapes.add(trunk);
                treeShapes.add(foliage);
                break;
        }
        setBasicShapes(treeShapes);
    }

}
