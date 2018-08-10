package Windows.ElementInfoWindow;

import Foundation.Frame;
import WindowElements.Label;
import Foundation.Person.Person;
import Images.Image;
import Images.PersonImage;
import Utils.Geometry.Coord;
import Windows.ClosableWindow;

public class PersonInfoWindow extends ClosableWindow{

    private Person person;

    public PersonInfoWindow(Person person, Frame parent) {
        super(new Coord(50, 400), new Coord(200, 400), parent);
        this.person = person;
        setElements();
    }

    public void setElements(){

        Image image = new PersonImage(new Coord(0, 0), new Coord(100, 100), this);
        addWindowElement(image);

        Label label = new Label(new Coord(10, 120), new Coord(200, 10),"name: " +person.getName(),this);
        addWindowElement(label);
    }
}
