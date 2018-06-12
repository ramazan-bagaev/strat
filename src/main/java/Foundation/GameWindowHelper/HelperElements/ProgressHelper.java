package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.GameWindowHelper.HelperField;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class ProgressHelper extends HelperElement{

    protected int progress; // from 0 to 100
    protected Coord progressSize;
    private Coord pos;

    private RectangleShape progressShape;

    public ProgressHelper(HelperField helperField, Coord pos, Coord progressSize, int progress) {
        super(helperField);
        this.progress = progress;
        this.pos = pos;
        this.progressSize = progressSize;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();

        RectangleShape carcas = new RectangleShape(new Coord(pos), new Coord(progressSize), new Color(Color.Type.Red), true, true);
        progressShape = new RectangleShape(new Coord(pos), new Coord(progress* progressSize.x/100, progressSize.y),
                new Color(Color.Type.Green3), true, true);
        basicShapes.add(carcas);
        basicShapes.add(progressShape);
        setBasicShapes(basicShapes);
    }
}
