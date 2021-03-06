package WindowElements.ScrollElements;

import Foundation.Color;
import Foundation.Person.Person;
import Utils.Geometry.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.BackgroundElement;
import WindowElements.StaticBroadcastLabel;

public class PersonScrolledRow extends ScrollableRow {

    private Person person;
    private BackgroundElement backgroundElement;

    public PersonScrolledRow(Person person, Coord size, ScrollableGroup parent) {
        super(size, parent);
        this.person = person;
        initRow();
    }

    public Person getPerson(){
        return person;
    }

    public void initRow(){

        backgroundElement = new BackgroundElement(new Coord(0, 0), new Coord(size), new Color(Color.Type.White), parent);
        rowElements.add(backgroundElement);

        StaticBroadcastLabel name = new StaticBroadcastLabel(new Coord(5, 0), new Coord(size.x/2 - 5, size.y),
                person, "name", parent);
        rowElements.add(name);

        StaticBroadcastLabel kasta = new StaticBroadcastLabel(new Coord(size.x/2 + 5, 0), new Coord(size.x/2 - 5, size.y),
                person, "kasta", parent);
        rowElements.add(kasta);
    }

    public void activate(){
        backgroundElement.changeColor(new Color(Color.Type.Snow));
    }

    public void deactivate(){
        backgroundElement.changeColor(new Color(Color.Type.White));
    }

    public void setColor(Color color){
        backgroundElement.changeColor(color);
    }

}
