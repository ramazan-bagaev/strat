package Foundation.GameWindowHelper;

import Foundation.*;

import java.util.ArrayList;

public class CityInfoHelper extends HelperElement {

    private City city;

    public CityInfoHelper(City city, HelperField parent){
        super(parent);
        this.city = city;

        setShapes();

    }

    public void setShapes(){
        ArrayList<BasicShape> shapes = getBasicShapes();
        String name = city.getName();
        StringShape stringShape = new StringShape(city.getPos(), new Coord(city.getSize(), city.getSize()/4), name,
                BasicShape.Color.Black, parentField.getMap().getParent().getParent().getFont("latin"));
        shapes.addAll(stringShape.getBasicShapes());
    }

}
