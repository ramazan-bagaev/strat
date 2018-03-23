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

    private People people;
    private Type currentType;


    public enum Type{
        All, High, Middle, Low
    }


    public PeopleElementGroup(Coord pos, Coord size, People people, Type type, Window parent) {
        super(pos, size, size.add(new Coord(-20, -20)), parent);
        this.people = people;
        currentType = type;
        init();
        addButtonChangeType();
    }

    public PeopleElementGroup(Coord pos, Coord size, People people, Window parent) {
        super(pos, size, size.add(new Coord(-20, -20)), parent);
        this.people = people;
        currentType = Type.All;
        init();
        addButtonChangeType();
    }

    public void addButtonChangeType(){
        double x = scrollWindowSize.x;
        Button all = new Button(new Coord(0, size.y-20), new Coord(x/4, 20), this, getParent(),"all") {
            @Override
            public void click(Coord point) {
                changeType(Type.All);
            }
        };
        addWindowElement(all);
        Button high = new Button(new Coord(x/4, size.y-20), new Coord(x/4, 20), this, getParent(),"high") {
            @Override
            public void click(Coord point) {
                changeType(Type.High);
            }
        };
        addWindowElement(high);
        Button middle = new Button(new Coord(2*x/4, size.y-20), new Coord(x/4, 20), this, getParent(),"middle") {
            @Override
            public void click(Coord point) {
                changeType(Type.Middle);
            }
        };
        addWindowElement(middle);
        Button low = new Button(new Coord(3*x/4, size.y-20), new Coord(x/4, 20), this, getParent(),"low") {
            @Override
            public void click(Coord point) {
                changeType(Type.Low);
            }
        };
        addWindowElement(low);

    }

    public void init(){
        cameraConfiguration.setMaxY(10);
        cameraConfiguration.setWorldCameraPos(getShift());
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
        init();
    }


    @Override
    public void click(Coord point){
        point = cameraConfiguration.transform(point);
        point.print("click here");
        for(ScrollableRow scrollableRow: scrollableRows){
            if (!scrollableRow.contain(point)) continue;
            PersonScrolledRow personScrolledRow = (PersonScrolledRow)scrollableRow;
            Person person = personScrolledRow.getPerson();
            getParent().getParent().addSpecialWindow("person info window", new PersonInfoWindow(person, getParent().getParent()));
        }
    }
}
