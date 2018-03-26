package Foundation.GameWindowHelper.HelperElements;

import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.RectangleShape;
import Foundation.BasicShapes.StringShape;
import Foundation.Color;
import Foundation.GameWindowHelper.HelperField;
import Utils.Coord;

import java.util.ArrayList;

public class HistogramHelper extends HelperElement {

    protected int number;
    protected int total;
    protected Color color;

    public HistogramHelper(HelperField helperField, int total) {
        super(helperField);
        this.total = total;
        this.number = 0;
        color = new Color(Color.Type.Gray, 0.9f);
        setShapes();
    }

    public HistogramHelper(HelperField helperField, int total, int number) {
        super(helperField);
        this.total = total;
        this.number = number;
        color = new Color(Color.Type.Gray, 0.9f);
        setShapes();
    }

    public HistogramHelper(HelperField helperField, int total, int number, Color color) {
        super(helperField);
        this.total = total;
        this.number = number;
        this.color = color;
        setShapes();
    }

    public void setShapes(){
        ArrayList<BasicShape> basicShapes = new ArrayList<>();
        double part = ((double)number)/(double)total;
        if (number != 0) {
            RectangleShape background = new RectangleShape(new Coord(0, 0), new Coord(size), color, false, true);
            basicShapes.add(background);
            RectangleShape rectangleShape = new RectangleShape(new Coord(size.x / 4, size.y * (1 - part)),
                    new Coord(size.x / 2, size.y * part), new Color(Color.Type.White), true, true);
            basicShapes.add(rectangleShape);
        }
        String num = String.valueOf(number);
        int len = num.length();
        double height = (size.x/4 / len);
        StringShape stringShape = new StringShape(new Coord(size.x/4, size.y/2 - height), new Coord(size.x/2, height*2),
                num, new Color(Color.Type.Black), parent.getMap().getParent().getParent().getFont("latin"));
        basicShapes.addAll(stringShape.getBasicShapes());
        setBasicShapes(basicShapes);
    }


}
