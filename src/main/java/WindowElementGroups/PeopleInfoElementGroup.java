package WindowElementGroups;

import Foundation.Button;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Window;
import Utils.Geometry.Coord;
import WindowElements.ScrollElements.PersonScrolledRow;
import WindowElements.ScrollElements.ScrollableRow;
import Windows.ElementInfoWindow.PersonInfoWindow;

import java.util.ArrayList;

public class PeopleInfoElementGroup extends PeopleElementGroup{

    private Type currentType;


    public enum Type{
        All, High, Middle, Low
    }


    public PeopleInfoElementGroup(Coord pos, Coord size, People people, Type type, Window parent) {
        super(pos, size, size.add(new Coord(-20, 20)), people, parent);
        currentType = type;
        addButtonChangeType();
    }

    public PeopleInfoElementGroup(Coord pos, Coord size, People people, Window parent) {
        super(pos, size, size.add(new Coord(-20, -20)), people, parent);
        currentType = Type.All;
        addButtonChangeType();
    }

    public void addButtonChangeType(){
        double x = scrollWindowSize.x;
        Button all = new Button(new Coord(0, size.y-20), new Coord(x/4, 20), this,"all", getParent()) {
            @Override
            public void click(Coord point) {
                changeType(Type.All);
            }
        };
        addWindowElement(all);
        Button high = new Button(new Coord(x/4, size.y-20), new Coord(x/4, 20), this,"high", getParent()) {
            @Override
            public void click(Coord point) {
                changeType(Type.High);
            }
        };
        addWindowElement(high);
        Button middle = new Button(new Coord(2*x/4, size.y-20), new Coord(x/4, 20), this,"middle", getParent()) {
            @Override
            public void click(Coord point) {
                changeType(Type.Middle);
            }
        };
        addWindowElement(middle);
        Button low = new Button(new Coord(3*x/4, size.y-20), new Coord(x/4, 20), this,"low", getParent()) {
            @Override
            public void click(Coord point) {
                changeType(Type.Low);
            }
        };
        addWindowElement(low);

    }

    @Override
    public void initRowElements(){
        initCameraConfiguration();
        ArrayList<Person> peopleArray = people.getPersonArray();
        scrollableRows.clear();
        for(Person person: peopleArray) {
            if (currentType == Type.High && person.getKasta() != Person.Kasta.High) continue;
            if (currentType == Type.Middle && person.getKasta() != Person.Kasta.Middle) continue;
            if (currentType == Type.Low && person.getKasta() != Person.Kasta.Low) continue;       // TODO: maybe optimized if necessary
            PersonScrolledRow personScrolledRow = new PersonScrolledRow(person, new Coord(getSize().x, 20), this);
            addScrollableRow(personScrolledRow);
        }
    }

    public void changeType(Type type){
        if (type == currentType)return;
        currentType = type;
        initRowElements();
    }


    @Override
    public void click(Coord point){
        point = cameraConfiguration.transform(point);
        for(ScrollableRow scrollableRow: scrollableRows){
            if (!scrollableRow.contain(point)) continue;
            PersonScrolledRow personScrolledRow = (PersonScrolledRow)scrollableRow;
            Person person = personScrolledRow.getPerson();
            getParent().getParent().addSpecialWindow("person info window", new PersonInfoWindow(person, getParent().getParent()));
        }
    }
}
