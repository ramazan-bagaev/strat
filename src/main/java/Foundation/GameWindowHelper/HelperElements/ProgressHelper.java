package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.DynamicDrawable;
import Foundation.GameWindowHelper.HelperField;
import Foundation.OpenGLBinder;
import Utils.Coord;

import java.util.ArrayList;

public class ProgressHelper extends HelperElement{

    private int progress; // from 0 to 100
    private Coord size;
    private Coord pos;

    private RectangleShape progressShape;

    public ProgressHelper(HelperField helperField, Coord pos, Coord size, int progress) {
        super(helperField);
        this.progress = progress;
        this.pos = pos;
        this.size = size;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();

        RectangleShape carcas = new RectangleShape(new Coord(pos), new Coord(size), new Color(Color.Type.Black), true, false);
        progressShape = new RectangleShape(new Coord(pos), new Coord(progress*size.x/100, size.y),
                new Color(Color.Type.Red), true, true);
        basicShapes.add(carcas);
        basicShapes.add(progressShape);
        setBasicShapes(basicShapes);
    }
}
