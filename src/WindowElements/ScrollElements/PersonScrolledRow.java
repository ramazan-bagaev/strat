package WindowElements.ScrollElements;

import Foundation.Person.Person;
import Utils.Coord;
import WindowElementGroups.ScrollableGroup;
import WindowElements.StaticBroadcastLabel;

public class PersonScrolledRow extends ScrollableRow {

    private Person person;

    public PersonScrolledRow(Person person, Coord size, ScrollableGroup parent) {
        super(size, parent);
        this.person = person;
        initRow();
    }

    public Person getPerson(){
        return person;
    }

    public void initRow(){
        StaticBroadcastLabel name = new StaticBroadcastLabel(new Coord(5, 0), new Coord(size.x/2 - 5, size.y),
                person, "name", parent, parent.getParent());
        rowElements.add(name);

        StaticBroadcastLabel kasta = new StaticBroadcastLabel(new Coord(size.x/2 + 5, 0), new Coord(size.x/2 - 5, size.y),
                person, "kasta", parent, parent.getParent());
        rowElements.add(kasta);
    }
}
