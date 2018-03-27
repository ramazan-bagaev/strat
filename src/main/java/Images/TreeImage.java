package Images;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Utils.Coord;

import java.util.ArrayList;
import java.util.Random;

public class TreeImage extends Image {

    private Random random;

    public TreeImage(Coord pos, Coord size, Random random, Window parent) {
        super(pos, size, parent);
        this.random = random;
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
        double trunkX;
        double trunkY;
        double foliageX;
        double foliageY;
        double foliageHeight;
        double foliageWidth;
        double trunkHeight;
        double trunkWidth;
        double height = getSize().y;
        double width = getSize().x;

        foliageHeight = height / 2;
        foliageWidth = width * 3 / 4;
        trunkHeight = height / 2;
        trunkWidth = width / 4;
        foliageX = width / 8;
        foliageY = 0;
        trunkX = width * 3 / 8;
        trunkY = foliageY + foliageHeight;
        trunk = new RectangleShape(new Coord(trunkX, trunkY), new Coord(trunkWidth, trunkHeight), new Color(Color.Type.Brown), true);
        foliage = new RectangleShape(new Coord(foliageX, foliageY), new Coord(foliageWidth, foliageHeight), new Color(Color.Type.Green3), true);
        treeShapes.add(trunk);
        treeShapes.add(foliage);

        setBasicShapes(treeShapes);




        /*ArrayList<Index> ocupied = new ArrayList<>();
        int number = 50;

        for(int i = 0; i < number; i++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Index xy = new Index(x, y);
            if (ocupied.contains(xy)) continue;
            ocupied.add(xy);
            Index newPos = new Index(x*size.x/10, y*size.y/10);
            RectangleShape rectangleShape = new RectangleShape(newPos.add(new Index(2*size.x/100,2*size.y/100)),
                    new Index(6*size.x/100, 6*size.y/100),
                    new Color(Color.Type.Green2), false, true);
            addBasicShape(rectangleShape);
            rectangleShape = new RectangleShape(newPos.add(new Index(4*size.x/100, 4*size.y/100)), new Index(2*size.x/100, 2*size.y/100),
                    new Color(Color.Type.Green));
            addBasicShape(rectangleShape);
        }*/
    }

}
