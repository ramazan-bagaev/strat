package Images;

import Foundation.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.RandomAccess;

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
        /*TriangleShape roof = new TriangleShape(new Coord(size.x/10, 2*size.y/10), new Coord(2*size.x/10, size.y/10),
                new Coord(3*size.x/10, 2*size.y/10), new Color(Color.Type.Yellow));

        RectangleShape body = new RectangleShape(new Coord(size.x/10, 2*size.y/10), new Coord(size.x/5, size.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);

        roof = new TriangleShape(new Coord(7*size.x/10, 2*size.y/10), new Coord(8*size.x/10, size.y/10),
                new Coord(9*size.x/10, 2*size.y/10), new Color(Color.Type.Yellow));

        body = new RectangleShape(new Coord(7*size.x/10, 2*size.y/10), new Coord(size.x/5, size.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);

        roof = new TriangleShape(new Coord(size.x/10, 8* size.y/10), new Coord(2*size.x/10, 7*size.y/10),
                new Coord(3*size.x/10, 8*size.y/10), new Color(Color.Type.Yellow));

        body = new RectangleShape(new Coord(size.x/10, 8*size.y/10), new Coord(size.x/5, size.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);

        roof = new TriangleShape(new Coord(7*size.x/10, 8* size.y/10), new Coord(8*size.x/10, 7*size.y/10),
                new Coord(9*size.x/10, 8*size.y/10), new Color(Color.Type.Yellow));

        body = new RectangleShape(new Coord(7*size.x/10, 8*size.y/10), new Coord(size.x/5, size.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);*/

        ArrayList<Coord> ocupied = new ArrayList<>();
        int number = 10;

        for(int i = 0; i < number; i++){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Coord xy = new Coord(x, y);
            if (ocupied.contains(xy)) continue;
            ocupied.add(xy);
            ocupied.add(xy.add(new Coord(0,1)));
            ocupied.add(xy.add(new Coord(0,-1)));
            ocupied.add(xy.add(new Coord(1,0)));
            ocupied.add(xy.add(new Coord(-1,0)));
            ocupied.add(xy.add(new Coord(1,1)));
            ocupied.add(xy.add(new Coord(1,-1)));
            ocupied.add(xy.add(new Coord(-1,1)));
            ocupied.add(xy.add(new Coord(-1,-1)));

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
