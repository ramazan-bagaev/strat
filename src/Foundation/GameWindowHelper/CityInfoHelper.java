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
        StringShape stringShape = new StringShape(pos, new Coord(size.x,  size.y/4), name,
                new Color(Color.Type.Black), parentField.getMap().getParent().getParent().getFont("latin"));
        shapes.addAll(stringShape.getBasicShapes());
        getParentField().getMap().getParent().setShapes();
    }

}
