package Images;

import Foundation.*;
import Foundation.BasicShapes.RectangleShape;
import Utils.Geometry.Index;
import Utils.Geometry.Coord;
import Windows.Window;

import java.util.ArrayList;
import java.util.Random;

public class VillageImage extends Image {

    private Random random;

    public VillageImage(Coord pos, Coord size, Random random, Window parent) {
        super(pos, size, parent);
        this.random = random;
        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        Coord size = getSize();
        /*TriangleShape roof = new TriangleShape(new Index(pieceSize.x/10, 2*pieceSize.y/10), new Index(2*pieceSize.x/10, pieceSize.y/10),
                new Index(3*pieceSize.x/10, 2*pieceSize.y/10), new Color(Color.Type.Yellow));

        RectangleShape body = new RectangleShape(new Index(pieceSize.x/10, 2*pieceSize.y/10), new Index(pieceSize.x/5, pieceSize.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);

        roof = new TriangleShape(new Index(7*pieceSize.x/10, 2*pieceSize.y/10), new Index(8*pieceSize.x/10, pieceSize.y/10),
                new Index(9*pieceSize.x/10, 2*pieceSize.y/10), new Color(Color.Type.Yellow));

        body = new RectangleShape(new Index(7*pieceSize.x/10, 2*pieceSize.y/10), new Index(pieceSize.x/5, pieceSize.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);

        roof = new TriangleShape(new Index(pieceSize.x/10, 8* pieceSize.y/10), new Index(2*pieceSize.x/10, 7*pieceSize.y/10),
                new Index(3*pieceSize.x/10, 8*pieceSize.y/10), new Color(Color.Type.Yellow));

        body = new RectangleShape(new Index(pieceSize.x/10, 8*pieceSize.y/10), new Index(pieceSize.x/5, pieceSize.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);

        roof = new TriangleShape(new Index(7*pieceSize.x/10, 8* pieceSize.y/10), new Index(8*pieceSize.x/10, 7*pieceSize.y/10),
                new Index(9*pieceSize.x/10, 8*pieceSize.y/10), new Color(Color.Type.Yellow));

        body = new RectangleShape(new Index(7*pieceSize.x/10, 8*pieceSize.y/10), new Index(pieceSize.x/5, pieceSize.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);*/

        ArrayList<Index> ocupied = new ArrayList<>();
        int number = 10;

        for(int i = 0; i < number; i++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Index xy = new Index(x, y);
            if (ocupied.contains(xy)) continue;
            ocupied.add(xy);
            ocupied.add(xy.add(new Index(0,1)));
            ocupied.add(xy.add(new Index(0,-1)));
            ocupied.add(xy.add(new Index(1,0)));
            ocupied.add(xy.add(new Index(-1,0)));
            ocupied.add(xy.add(new Index(1,1)));
            ocupied.add(xy.add(new Index(1,-1)));
            ocupied.add(xy.add(new Index(-1,1)));
            ocupied.add(xy.add(new Index(-1,-1)));

            Coord newPos = new Coord(x*size.x/10, y*size.y/10);
            RectangleShape rectangleShape = new RectangleShape(newPos, new Coord(size.x/10, size.y/10),
                    new Color(Color.Type.Gray), false, true);
            addBasicShape(rectangleShape);
            rectangleShape = new RectangleShape(newPos.add(new Coord(4*size.x/100, 4*size.y/100)), new Coord(2*size.x/100, 2*size.y/100),
                    new Color(Color.Type.Red));
            addBasicShape(rectangleShape);
        }



    }
}
