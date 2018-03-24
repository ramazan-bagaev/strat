package WindowElementGroups;

import Foundation.Button;
import Foundation.Label;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Window;
import Utils.Coord;
import WindowElements.ScrollElements.PersonScrolledRow;
import WindowElements.ScrollElements.ScrollableRow;
import Windows.ElementInfoWindow.PersonInfoWindow;

import java.util.ArrayList;

public class PeopleElementGroup extends ScrollableGroup{

    protected People people;

    public PeopleElementGroup(Coord pos, Coord size, Coord scrollSize, People people, Window parent){
        super(pos, size, scrollSize, parent);
        this.people = people;
        initRowElements();
    }

    public PeopleElementGroup(Coord pos, Coord size, People people, Window parent) {
        super(pos, size, size.add(new Coord(-20,0)), parent);
        this.people = people;
        initRowElements();
    }


    public void initRowElements(){
        initCameraConfiguration();
        ArrayList<Person> peopleArray = people.getPersonArray();
        scrollableRows.clear();
        for(Person person: peopleArray) {
            PersonScrolledRow personScrolledRow = new PersonScrolledRow(person, new Coord(getSize().x, 20), this);
            addScrollableRow(personScrolledRow);
        }
    }
}
