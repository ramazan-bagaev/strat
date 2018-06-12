package Foundation.GameWindowHelper.HelperElements;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.BasicShapes.StringShape;
import Foundation.Elements.City;
import Foundation.GameWindowHelper.HelperField;
import Utils.Geometry.Coord;

import java.util.ArrayList;

public class CityInfoHelper extends HelperElement {

    private City city;

    public CityInfoHelper(City city, HelperField parent){
        super(parent);
        this.city = city;

        setShapes();

    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = new ArrayList<>();
        shapes.clear();
        String name = city.getName();
        StringShape stringShape = new StringShape(new Coord(0, -size.y/3), new Coord(size.x * name.length()/5,  size.y/4), name,
                new Color(Color.Type.Black), parent.getMap().getParent().getParent().getFont("latin"));
        shapes.addAll(stringShape.getBasicShapes());
        setBasicShapes(shapes);
    }

}
