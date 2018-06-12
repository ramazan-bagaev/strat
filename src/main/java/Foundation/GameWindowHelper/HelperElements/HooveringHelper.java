package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.Color;
import Foundation.GameWindowHelper.HelperField;
import Images.Image;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class HooveringHelper extends HelperElement {

    private Image image;
    private Color filterColor;

    public HooveringHelper(HelperField helperField, Color filterColor){
        super(helperField);
        this.filterColor = filterColor;
        setShapes();
    }

    public HooveringHelper(HelperField helperField, Image image) {
        super(helperField);
        this.image = image;
        setShapes();
    }

    public HooveringHelper(HelperField helperField, Image image, Color filterColor){
        super(helperField);
        this.image = image;
        this.filterColor = filterColor;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();

        if (filterColor != null){
            RectangleShape filter = new RectangleShape(new Coord(), new Coord(size), new Color(filterColor), false, true);
            basicShapes.add(filter);
        }
        if (image != null){
            basicShapes.addAll(image.getCopyOfBasicShapesWithoutShift());
        }

        setBasicShapes(basicShapes);
    }
}
