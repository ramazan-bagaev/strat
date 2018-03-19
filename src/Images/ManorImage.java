package Images;

import Foundation.*;

import java.util.ArrayList;

public class ManorImage extends Image {

    public ManorImage(Coord pos, Coord size, Window parent) {
        super(pos, size, parent);

        setShapes();
    }

    public void setShapes(){
        RectangleShape rect =
                new RectangleShape(new Coord(size.x/4, size.y/4), new Coord(size.x/2, 3*size.y/4), new Color(Color.Type.LightGray));
        LineShape line1 = new LineShape(new Coord(0, 0), size, new Color(Color.Type.Yellow), 4.0f);
        LineShape line2 = new LineShape(new Coord(size.x, 0), new Coord(0, size.y), new Color(Color.Type.Yellow), 4.0f);
        clearBasicShapes();
        addBasicShape(rect);
        addBasicShape(line1);
        addBasicShape(line2);
    }
}
