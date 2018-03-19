package Images;

import Foundation.*;

import java.util.ArrayList;

public class VillageImage extends Image {

    public VillageImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);

        setShapes();
    }

    public void setShapes(){
        clearBasicShapes();
        Coord size = getSize();
        TriangleShape roof = new TriangleShape(new Coord(size.x/10, 2* size.y/10), new Coord(2*size.x/10, size.y/10),
                new Coord(3*size.x/10, 2*size.y/10), new Color(Color.Type.Yellow));

        RectangleShape body = new RectangleShape(new Coord(size.x/10, size.y/10), new Coord(size.x/5, size.y /10),
                new Color(Color.Type.LightGray), false, true);
        addBasicShape(roof);
        addBasicShape(body);

        roof = new TriangleShape(new Coord(7*size.x/10, 2* size.y/10), new Coord(8*size.x/10, size.y/10),
                new Coord(9*size.x/10, 2*size.y/10), new Color(Color.Type.Yellow));

        body = new RectangleShape(new Coord(7*size.x/10, size.y/10), new Coord(size.x/5, size.y /10),
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
        addBasicShape(body);

        shift(pos);

    }
}
