package Windows;

import Foundation.*;
import Images.CityImage;
import Images.GroundImage;
import Images.RockImage;
import Images.TreeImage;

public class FieldInfoWindow extends ClosableWindow{

    private Field field;

    public FieldInfoWindow(Windows parent, Field field) {
        super(new Coord(0,0), new Coord(200, 100), parent);
        setField(field);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
        //removeWindowElements();
        if (getWindowElements().size() > 1) {
            getWindowElements().remove(getWindowElements().size() - 1);
            getWindowElements().remove(getWindowElements().size() - 1);
        }
        if (field == null) return;
        Element additionalElement = field.getAdditionalElement();
        Ground groundElement = field.getGround();
        Image groundImage = new GroundImage(getPos(), groundElement.getGroundType(), this);
        Image finalImage = groundImage;
        if (additionalElement != null) {
            switch (additionalElement.getType()) {
                case Rock:
                    Rock rock = (Rock) additionalElement;
                    Image rockImage = new RockImage(getPos(), rock.getSizeType(), this);
                    finalImage = new Image(groundImage, rockImage, getPos(), this);
                    break;
                case Tree:
                    Tree tree = (Tree) additionalElement;
                    Image treeImage = new TreeImage(getPos(), tree.getSizeType(), this);
                    finalImage = new Image(groundImage, treeImage, getPos(), this);
                    break;
                case City:
                    City city = (City) additionalElement;
                    Image cityImage = new CityImage(getPos(), city.getSizeType(), this);
                    finalImage = new Image(groundImage, cityImage, getPos(), this);
                    break;
                case Ground:
                    break;
            }
        }
        addWindowElement(finalImage);
        Label elementType = new Label(new Coord(10, 55).add(getPos()), new Coord(20, 10), "TYPE:", this);
        addWindowElement(elementType);
    }

}
