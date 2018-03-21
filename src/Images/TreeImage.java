package Images;

import Foundation.*;

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
        int trunkX;
        int trunkY;
        int foliageX;
        int foliageY;
        int foliageHeight;
        int foliageWidth;
        int trunkHeight;
        int trunkWidth;
        int height = getSize().y;
        int width = getSize().x;

        foliageHeight = height / 2;
        foliageWidth = width * 3 / 4;
        trunkHeight = height / 2;
        trunkWidth = width / 4;
        foliageX = width / 8;
        foliageY = 0;
        trunkX = width * 3 / 8;
        trunkY = foliageY + foliageHeight;
        trunk = new RectangleShape(new Coord(trunkX, trunkY), new Coord(trunkWidth, trunkHeight), new Color(Color.Type.Red), true);
        foliage = new RectangleShape(new Coord(foliageX, foliageY), new Coord(foliageWidth, foliageHeight), new Color(Color.Type.Green3), true);
        treeShapes.add(trunk);
        treeShapes.add(foliage);

        setBasicShapes(treeShapes);




        /*ArrayList<Coord> ocupied = new ArrayList<>();
        int number = 50;

        for(int i = 0; i < number; i++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Coord xy = new Coord(x, y);
            if (ocupied.contains(xy)) continue;
            ocupied.add(xy);
            Coord newPos = new Coord(x*size.x/10, y*size.y/10);
            RectangleShape rectangleShape = new RectangleShape(newPos.add(new Coord(2*size.x/100,2*size.y/100)),
                    new Coord(6*size.x/100, 6*size.y/100),
                    new Color(Color.Type.Green2), false, true);
            addBasicShape(rectangleShape);
            rectangleShape = new RectangleShape(newPos.add(new Coord(4*size.x/100, 4*size.y/100)), new Coord(2*size.x/100, 2*size.y/100),
                    new Color(Color.Type.Green));
            addBasicShape(rectangleShape);
        }*/
    }

}
